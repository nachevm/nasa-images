package com.nasa.images.mars.rover.api;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import lombok.NonNull;

public interface MarsRoverService {

  TreeMap<LocalDate, List<String>> getPhotos(@NonNull MarsRoverDateRangeFilter filter);

  List<String> getPhotos(@NonNull MarsRoverDateFilter filter);
}
