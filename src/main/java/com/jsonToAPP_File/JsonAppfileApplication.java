package com.jsonToAPP_File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class JsonAppfileApplication {
    private static final Logger log = LoggerFactory.getLogger(JsonAppfileApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JsonAppfileApplication.class, args);
        log.info("Application Started!!!!");
    }
}