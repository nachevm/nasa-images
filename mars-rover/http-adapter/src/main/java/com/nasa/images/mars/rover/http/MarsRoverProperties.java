package com.nasa.images.mars.rover.http;

import java.io.InputStream;
import java.util.Properties;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;

@Data
public class MarsRoverProperties {

  @NonNull
  private final String baseUrl;

  @NonNull
  private final String apiKey;

  @SneakyThrows
  public MarsRoverProperties() {
    try (InputStream input = MarsRoverProperties.class.getClassLoader().getResourceAsStream("mars-rover.properties")) {
      Properties properties = new Properties();
      properties.load(input);
      baseUrl = properties.getProperty("mars-rover.baseUrl");
      apiKey = properties.getProperty("mars-rover.apiKey");
    }
  }
}
