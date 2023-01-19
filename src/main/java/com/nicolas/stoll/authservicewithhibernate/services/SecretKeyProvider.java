package com.nicolas.stoll.authservicewithhibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
public class SecretKeyProvider {
    private static final String FILENAME = "app.config";

    public static String getKey() {
        Properties props = new Properties();

        try(FileInputStream fis = new FileInputStream(FILENAME)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props.getProperty("app.key");
    }
}

