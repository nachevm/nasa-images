package com.nasa.images.mars.rover.api;

import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
public class MarsRoverDateRangeFilter {

  @NonNull
  private MarsRover rover = MarsRover.CURIOSITY;

  @NonNull
  private MarsRoverCamera camera = MarsRoverCamera.NAVCAM;

  @NonNull
  private LocalDate startDate = LocalDate.now().minusDays(10);

  @NonNull
  private LocalDate endDate = LocalDate.now();

  private int imagesLimit = 3;
}
