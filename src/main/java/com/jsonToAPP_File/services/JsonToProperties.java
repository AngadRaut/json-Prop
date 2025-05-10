package com.jsonToAPP_File.services;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class JsonToProperties {
    public String convertJsonToProperties(Map<String, Object> json) throws IOException {
        Properties props = new Properties();
        StringWriter stringWriter = new StringWriter();

        List<Map.Entry<String, Object>> entries = new ArrayList<>(json.entrySet());
        List<String> keys = new ArrayList<>();

        for (int i = 0; i < entries.size(); i++) {
            String parent;
            // String parent = keys.size() > i ? keys.get(i) : "";
            if (keys.size() > i) {
                parent = keys.get(i);

            } else {
                parent = "";
            }
            Map.Entry<String, Object> entry = entries.get(i);
            if (parent.isEmpty()) {
                parent = entry.getKey();
            } else {
                parent = parent + "." + entry.getKey();
            }
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String, Object> childMap = (Map<String, Object>) value;
                for (Map.Entry<String, Object> childEntry : childMap.entrySet()) {
                    entries.add(childEntry);
                    keys.add(parent);
                }
            } else {
                props.setProperty(parent, String.valueOf(value));
            }
        }
        try (FileWriter fileWriter = new FileWriter("src/main/resources/application.properties",true)) {
            props.store(fileWriter, "Converted from JSON");
        }

        props.store(stringWriter, null);
        return stringWriter.toString();
    }
}