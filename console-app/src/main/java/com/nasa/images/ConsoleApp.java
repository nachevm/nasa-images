package com.nasa.images;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nasa.images.cache.file.FileCache;
import com.nasa.images.mars.rover.http.MarsRoverHttpService;
import com.nasa.images.mars.rover.http.MarsRoverProperties;
import java.io.File;
import java.util.List;

public class ConsoleApp {

  public static void main(String[] args) throws JsonProcessingException {
    ConsoleAppProperties properties = new ConsoleAppProperties();
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    List<Command<?>> commands = List.of(
        new MarsRoverCommand(
            new MarsRoverHttpService(
                new FileCache<>(new File(properties.getMarsRoverCacheFile()), properties.getMarsRoverCacheItemsLimit(), objectMapper),
                new MarsRoverProperties(),
                objectMapper
            )
        )
    );

    System.out.println(objectMapper.writeValueAsString(executeCommand(commands, args)));
  }

  private static Object executeCommand(List<Command<?>> commands, String[] args) {
    return commands.stream()
        .filter(c -> args.length > 0 ? c.getName().equalsIgnoreCase(args[0]) : c != null)
        .findFirst()
        .orElseThrow(UnknownArgumentException::new)
        .execute(args);
  }
}
