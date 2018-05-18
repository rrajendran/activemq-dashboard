package com.capella.mvc.example.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;

import static org.slf4j.LoggerFactory.getLogger;

public final class JsonHelper {
    public static final Logger LOGGER = getLogger(JsonHelper.class);
    private static ObjectMapper objectMapper = IPTObjectMapper.getInstance();

    private JsonHelper() {
    }

    public static InputStream readJson(String fileName) {
        return JsonHelper.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static <T> T jsonToObject(InputStream inputStream, Class<T> className) throws IOException, JsonMappingException {
        return objectMapper.readValue(inputStream, className);
    }

    public static <T> T jsonToObject(String json, Class<T> className) throws IOException, JsonMappingException {
        return objectMapper.readValue(json.getBytes(), className);
    }

    public static String toString(Object object) throws IOException, JsonGenerationException, JsonMappingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        objectMapper.writeValue(baos, object);
        return new String(baos.toByteArray());
    }

    /*public static Object getJsonPath(String json, String path) {
        String object = null;
        if (!StringUtils.isEmpty(json) && !StringUtils.isEmpty(path)) {
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
            object = (String) JsonPath.read(document, path, new Predicate[0]);
        }

        return object;
    }*/
}