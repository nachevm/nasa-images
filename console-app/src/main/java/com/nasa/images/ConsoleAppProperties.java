package com.nasa.images;

import java.io.InputStream;
import java.util.Properties;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;

@Data
class ConsoleAppProperties {

  @NonNull
  private final String marsRoverCacheFile;

  private final int marsRoverCacheItemsLimit;

  @SneakyThrows
  ConsoleAppProperties() {
    try (InputStream input = ConsoleAppProperties.class.getClassLoader().getResourceAsStream("console-app.properties")) {
      Properties properties = new Properties();
      properties.load(input);
      marsRoverCacheFile = properties.getProperty("mars-rover.cache.file");
      marsRoverCacheItemsLimit = Integer.parseInt(properties.getProperty("mars-rover.cache.itemsLimit"));
    }
  }
}
