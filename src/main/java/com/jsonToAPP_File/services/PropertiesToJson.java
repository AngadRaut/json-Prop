package com.jsonToAPP_File.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

@Service
public class PropertiesToJson {

    public String convertPropertiesToJson(String propertiesContent) throws IOException {
        // read the prop file key value pair
        Properties properties = new Properties();
        properties.load(new StringReader(propertiesContent));

        // create json object
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();

        // iterate through the prop string array
        for (String key : properties.stringPropertyNames()){
            String[] str = key.split("\\.");

            ObjectNode current = root;
            // Traverse through the nested keys except the last part
            for (int i = 0; i < str.length - 1; i++) {
                current = current.with(str[i]); // Navigate or create nested objects
            }

            // Add the final value to the last nested key
            current.put(str[str.length - 1], properties.getProperty(key));
        }
        String resultJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);

      /*  try (FileWriter fileWriter = new FileWriter("src/main/resources/config.json")){
            fileWriter.write(resultJson);
        }*/
        return resultJson;
    }
}