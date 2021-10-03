package com.nasa.images.mars.rover.http;

import static java.util.stream.Collectors.toList;

import com.nasa.images.mars.rover.api.MarsRoverDateFilter;
import com.nasa.images.mars.rover.api.MarsRoverDateRangeFilter;
import java.time.LocalDate;
import java.util.List;
import lombok.NonNull;

interface MarsRoverMapper {

  static MarsRoverDateFilter toDateFilter(@NonNull MarsRoverDateRangeFilter filter, @NonNull LocalDate date) {
    return new MarsRoverDateFilter().date(date).rover(filter.rover()).camera(filter.camera()).imagesLimit(filter.imagesLimit());
  }

  static List<String> toPhotos(@NonNull MarsRoverResponse response, int limit) {
    return response.getPhotos().stream().map(MarsRoverPhoto::getImgSrc).limit(limit).collect(toList());
  }
}
