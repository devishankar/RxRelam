/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Utilities for IO operations.
 */
public final class IOUtils {

    private static final String TAG = "IOUtils";
    private static final int BUFFER_SIZE = 1024 * 4;

    private IOUtils() {
        // Hidden empty constructor
    }

    /**
     * Reads and returns the rest of the given input stream as a string, closing
     * the input stream afterwards.
     *
     * @param is the input stream.
     * @return the rest of the given input stream.
     */
    public static String toString(@NonNull InputStream is) throws IOException {
        return new String(toByteArray(is), StandardCharsets.UTF_8);
    }

    /**
     * Reads and returns the rest of the given input stream as a byte array,
     * closing the input stream afterwards.
     *
     * @param is the input stream.
     * @return the rest of the given input stream.
     */
    public static byte[] toByteArray(@NonNull InputStream is) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            final byte[] b = new byte[BUFFER_SIZE];
            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            return output.toByteArray();
        }
    }

    /**
     * Closes the given Closeable quietly.
     *
     * @param closeable the given closeable
     */
    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                Log.i(TAG, ex.getLocalizedMessage());
            }
        }
    }
}
