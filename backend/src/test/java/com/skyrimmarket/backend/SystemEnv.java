package com.skyrimmarket.backend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SystemEnv {
    @Disabled
    @Test
    void getEnv() {
        String skyrimDbUrl = System.getenv("SKYRIM_DB_URL");
        System.out.println(skyrimDbUrl);
        assertNotNull(skyrimDbUrl);
    }
}
