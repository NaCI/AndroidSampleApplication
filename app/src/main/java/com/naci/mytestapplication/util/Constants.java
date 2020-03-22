package com.naci.mytestapplication.util;

public class Constants {

    public static final byte[] SECRET_KEY_BYTE = new byte[]{
            74, 64, 70, 53, 65, 63, 72, 65, 74, 65, 79
    };

    public static String getSecretKey() {
        return new String(SECRET_KEY_BYTE);
    }
}
