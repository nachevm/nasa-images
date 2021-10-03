package com.nasa.images.cache.file;

import static java.util.stream.Collectors.toSet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.images.cache.api.Cache;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import lombok.SneakyThrows;

public class FileCache<T> implements Cache<T> {

  private final File file;
  private final int itemLimit;
  private final ObjectMapper objectMapper;
  private final Map<String, T> map;

  @SneakyThrows
  public FileCache(File file, int itemLimit, ObjectMapper objectMapper) {
    this.file = file;
    this.itemLimit = itemLimit;
    this.objectMapper = objectMapper;
    map = !file.exists() ? new LinkedHashMap<>() : objectMapper.readValue(file, new TypeReference<LinkedHashMap<String, T>>() {
    });
  }

  @Override
  public Optional<T> get(Object key) {
    return Optional.ofNullable(map.get(key.toString()));
  }

  @Override
  @SneakyThrows
  public T put(Object key, T value) {
    map.put(key.toString(), value);
    if (map.size() > itemLimit) {
      map.keySet().stream().limit(map.size() - itemLimit).collect(toSet()).forEach(map::remove);
    }
    objectMapper.writeValue(file, map);
    return value;
  }
}
