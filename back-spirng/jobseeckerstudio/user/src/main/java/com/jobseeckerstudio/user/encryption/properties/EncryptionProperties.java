package com.jobseeckerstudio.user.encryption.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EncryptionProperties {

    @Value("${encryption.secret}")
    private String key;

    public static String KEY;

    @PostConstruct
    public void init() {
        KEY = key;
    }
}
