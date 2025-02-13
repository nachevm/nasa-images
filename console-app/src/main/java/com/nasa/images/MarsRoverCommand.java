package com.nasa.images;

import com.nasa.images.mars.rover.api.MarsRoverService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MarsRoverCommand implements Command<Map<LocalDate, List<String>>> {

  private final MarsRoverService marsRoverService;

  @Override
  public Map<LocalDate, List<String>> execute(String[] args) {
    return marsRoverService.getPhotos(MarsRoverCommandMapper.toFilter(List.of(args))).descendingMap();
  }

  @Override
  public String getName() {
    return "mars-rover";
  }
}
