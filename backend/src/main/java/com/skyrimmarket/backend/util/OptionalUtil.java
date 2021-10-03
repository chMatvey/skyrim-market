package com.skyrimmarket.backend.util;

import java.util.Optional;

public class OptionalUtil {

    public static boolean isEmpty(Optional<?> optional) {
        return !optional.isPresent();
    }
}
