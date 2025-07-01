package tests.ReadingFilesTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.InputMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JsonFileCheck {
    File json = new File("src/test/resources/file3.json");
    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Тест на проверку значений всех полей JSON файла")
    @Test
    void jsonFileCheckTest() throws Exception {
        InputMessage actual = objectMapper.readValue(json, InputMessage.class);
        Assertions.assertEquals("Host", actual.key);
        Assertions.assertEquals("test-mobile.ru", actual.value);
        Assertions.assertEquals("97834104-e73d-49fa-b8b2-4484ce1187cd", actual.info._postman_id);
        Assertions.assertEquals("bcxl-integration-api", actual.info.name);
        Assertions.assertEquals("https://schema.getpostman.com/json/collection/v2.1.0/collection.json", actual.info.schema);
    }
}

