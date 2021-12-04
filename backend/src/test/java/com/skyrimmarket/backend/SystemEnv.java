package com.skyrimmarket.backend;

import org.junit.jupiter.api.Test;

public class SystemEnv {
    @Test
    void getEnv() {
        System.out.println(
                System.getenv("SKYRIM_DB_URL")
        );
    }
}
