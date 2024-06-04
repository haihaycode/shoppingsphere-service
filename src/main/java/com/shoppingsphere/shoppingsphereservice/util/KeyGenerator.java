package com.shoppingsphere.shoppingsphereservice.util;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {

    private static String key;

    public static String getKey() {
        if (key == null) {
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[64];
            secureRandom.nextBytes(randomBytes);
            key = Base64.getEncoder().encodeToString(randomBytes);
        }
        return key;
    }
}