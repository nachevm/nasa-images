package com.nasa.images.mars.rover.http;

import static java.util.stream.Collectors.toMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.images.cache.api.Cache;
import com.nasa.images.mars.rover.api.MarsRoverDateFilter;
import com.nasa.images.mars.rover.api.MarsRoverDateRangeFilter;
import com.nasa.images.mars.rover.api.MarsRoverService;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class MarsRoverHttpService implements MarsRoverService {

  private final Cache<List<String>> cache;
  private final MarsRoverProperties properties;
  private final ObjectMapper objectMapper;

  @Override
  public TreeMap<LocalDate, List<String>> getPhotos(MarsRoverDateRangeFilter filter) {
    return filter.startDate().datesUntil(filter.endDate())
        .collect(toMap(date -> date, date -> getPhotos(MarsRoverMapper.toDateFilter(filter, date)), (a, b) -> a, TreeMap::new));
  }

  @Override
  public List<String> getPhotos(MarsRoverDateFilter filter) {
    return cache.get(filter).orElseGet(() -> cache.put(filter, getPhotosFromNasaApi(filter)));
  }

  @SneakyThrows
  private List<String> getPhotosFromNasaApi(MarsRoverDateFilter filter) {
    String url = properties.getBaseUrl() + filter.rover() + "/photos?earth_date=" + filter.date() + "&camera=" + filter.camera() + "&api_key=" + properties.getApiKey();
    return MarsRoverMapper.toPhotos(objectMapper.readValue(new URL(url), MarsRoverResponse.class), filter.imagesLimit());
  }
}
