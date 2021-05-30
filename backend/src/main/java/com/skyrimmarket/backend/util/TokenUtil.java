package com.skyrimmarket.backend.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TokenUtil {
    public static String generateRandomToken() {
        byte[] array = new byte[25];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
