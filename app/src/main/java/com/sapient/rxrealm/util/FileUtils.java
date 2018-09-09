/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sapient.rxrealm.MyApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.annotations.Nullable;

/**
 * This utility extracts files and directories of a standard zip file to
 * a destination directory.
 **/
public final class FileUtils {

    private static final String TAG = "FileUtils";
    private static final String ZIP_EXTENSION = ".zip";
    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;
    private static final int TWICE = 2;
    private static final short LEFT_SHIFT_BY = 4;
    private static final int DECIMAL_RADIX = 10;

    private FileUtils() {
        // Hidden empty constructor
    }

    /**
     * Zip list of files, runs on the same thread.
     *
     * @param filePath list of files
     * @return Observable to subscribe
     */
    public static Maybe<Boolean> zipFiles(@NonNull String... filePath) {
        return zipFiles(filePath, filePath[0] + ZIP_EXTENSION);
    }

    /**
     * Zip list of files, runs on the same thread.
     *
     * @param sourceFiles     list of files
     * @param destZipFilePath file name for the zip file.
     * @return Observable to subscribe
     */
    public static Maybe<Boolean> zipFiles(@NonNull String[] sourceFiles,
                                          @NonNull String destZipFilePath) {
        return Maybe.defer(new ZipFiles(sourceFiles, destZipFilePath));
    }

    /**
     * SDK-432
     * Utility method to read files from the Assets folder.
     *
     * @param filePath file path in assets
     * @return Content of the file in Assets
     */
    @android.support.annotation.Nullable
    @Nullable
    public static String readFromAssets(@NonNull String filePath, boolean gZipped, boolean encoded,
                                        boolean jsonEscaped) {
        String assetsContent = readFromAssets(filePath, gZipped, encoded);
        if (jsonEscaped && assetsContent != null) {
            assetsContent = assetsContent.replaceAll("\\\\\"", "\"");
        }
        return assetsContent;
    }

    /**
     * SDK-432
     * Utility method to read files from the Assets folder.
     *
     * @param filePath file path in assets
     * @return Content of the file in Assets
     */
    @android.support.annotation.Nullable
    @Nullable
    private static String readFromAssets(@NonNull String filePath, boolean gZipped,
                                         boolean encoded) {
        String assetsContent;
        if (gZipped) {
            assetsContent = readFromAssetsGzipped(filePath);
        } else {
            assetsContent = readFromAssets(filePath);
        }

        if (encoded && assetsContent != null) {
            assetsContent = decodeHex(assetsContent);
        }
        return assetsContent;
    }

    /**
     * Converts encoded Hex string to plain string.
     *
     * @param hex Hex encoded String.
     * @return decoded string.
     */
    private static String decodeHex(@NonNull String hex) {
        byte[] result = new byte[hex.length() / TWICE];
        for (int i = 0; i < result.length; i++) {
            int d1 = decodeHexDigit(hex.charAt(i * TWICE)) << LEFT_SHIFT_BY;
            int d2 = decodeHexDigit(hex.charAt(i * TWICE + 1));
            result[i] = (byte) (d1 + d2);
        }
        return new String(result);
    }

    private static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + DECIMAL_RADIX;
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + DECIMAL_RADIX;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    /**
     * Convert gzip content to plain content from the stream.
     * If gzip is directly converted to plain string, it throws magic number exception.
     *
     * @param filePath path for file inside assets.
     * @return Unpacked string
     */
    @Nullable
    private static String readFromAssetsGzipped(@NonNull String filePath) {
        InputStream fileStream = null;
        GZIPInputStream gZipStream = null;
        InputStreamReader gZipReader = null;
        BufferedReader reader = null;
        try {
            fileStream = MyApplication.getContext().getAssets().open(filePath);
            gZipStream = new GZIPInputStream(fileStream);
            gZipReader = new InputStreamReader(gZipStream);
            reader = new BufferedReader(gZipReader);
            StringBuilder stringBuilder = new StringBuilder();
            String read;
            while ((read = reader.readLine()) != null) {
                stringBuilder.append(read);
            }
            return stringBuilder.toString();
        } catch (IOException ex) {
            Log.i(TAG, ex.getLocalizedMessage());
        } finally {
            IOUtils.closeQuietly(fileStream);
            IOUtils.closeQuietly(gZipStream);
            IOUtils.closeQuietly(gZipReader);
            IOUtils.closeQuietly(reader);
        }
        return null;
    }

    /**
     * SDK-432
     * Utility method to read files from the Assets folder.
     *
     * @param filePath file path in assets
     * @return Content of the file in Assets
     */
    @Nullable
    private static String readFromAssets(String filePath) {
        InputStream fileContents = null;
        try {
            fileContents = MyApplication.getContext().getAssets().open(filePath);
            return IOUtils.toString(fileContents);
        } catch (IOException ex) {
            Log.i(TAG, ex.getLocalizedMessage());
        } finally {
            IOUtils.closeQuietly(fileContents);
        }
        return null;
    }

    @NonNull
    public static byte[] readFromFile(@NonNull String path) {
        return readFromFile(new File(path));
    }

    @NonNull
    private static byte[] readFromFile(@NonNull File file) {
        InputStream fileContents = null;
        try {
            fileContents = new FileInputStream(file);
            return IOUtils.toByteArray(fileContents);
        } catch (IOException ex) {
            Log.i(TAG, ex.getLocalizedMessage());
        } finally {
            IOUtils.closeQuietly(fileContents);
        }
        return new byte[0];
    }

    private static class ZipFiles implements Callable<MaybeSource<Boolean>> {

        private String[] mFiles;
        private String mZipFilePath;

        ZipFiles(@NonNull String[] files, @NonNull String zipFilePath) {
            mFiles = files;
            mZipFilePath = zipFilePath;
        }

        private static boolean addToZipEntry(@NonNull String fileName,
                                             @NonNull ZipOutputStream zos) {
            File file = new File(fileName);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(fileName.substring(fileName.lastIndexOf('/') + 1));
                zipEntry.setTime(new Date().getTime());
                zos.putNextEntry(zipEntry);
                byte[] bytes = new byte[BUFFER_SIZE];
                int length;
                while ((length = fis.read(bytes)) != -1) {
                    zos.write(bytes, 0, length);
                }
                zos.closeEntry();
                return true;
            } catch (FileNotFoundException ex) {
                Log.e(TAG, "The file does not exist" + ex.getLocalizedMessage());
                return false;
            } catch (IOException ex) {
                Log.e(TAG, "I/O error: " + ex.getLocalizedMessage());
                return false;
            } finally {
                IOUtils.closeQuietly(fis);
            }
        }

        @Override
        public synchronized MaybeSource<Boolean> call() {
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(new File(mZipFilePath));
                zos = new ZipOutputStream(fos);
                zos.setComment("Compressed on " + new Date().toString());
                zos.setLevel(Deflater.BEST_COMPRESSION);

                boolean isSuccess = true;
                for (String file : mFiles) {
                    isSuccess = addToZipEntry(file, zos) && isSuccess;
                }
                return Maybe.just(isSuccess);
            } catch (IOException ex) {
                Log.e(TAG, "The file does not exist" + ex.getLocalizedMessage());
                return Maybe.just(false);
            } finally {
                IOUtils.closeQuietly(zos);
                IOUtils.closeQuietly(fos);
            }
        }
    }
}
