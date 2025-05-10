package com.jsonToAPP_File.controller;

import com.jsonToAPP_File.services.JsonToProperties;
import com.jsonToAPP_File.services.PropertiesToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/convert")
public class ConversionController {
    private PropertiesToJson propertiesToJson;
    private JsonToProperties jsonToProperties;

    public ConversionController() {
    }

    @Autowired
    public ConversionController(PropertiesToJson propertiesToJson, JsonToProperties jsonToProperties) {
        this.propertiesToJson = propertiesToJson;
        this.jsonToProperties = jsonToProperties;
    }

    @PostMapping(value = "/json-to-properties", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String convertJsonToProperties(@RequestBody Map<String,Object> json) throws IOException {
        return this.jsonToProperties.convertJsonToProperties(json);
    }

    @PostMapping(value = "/properties-to-json", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String convertPropertiesToJson(@RequestBody String propertiesContent) throws IOException {
        return this.propertiesToJson.convertPropertiesToJson(propertiesContent);
    }
}