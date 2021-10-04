package com.nasa.images;

import com.nasa.images.mars.rover.api.MarsRover;
import com.nasa.images.mars.rover.api.MarsRoverCamera;
import com.nasa.images.mars.rover.api.MarsRoverDateRangeFilter;
import java.time.LocalDate;
import java.util.List;
import java.util.function.IntConsumer;

interface MarsRoverCommandMapper {

  static MarsRoverDateRangeFilter toFilter(List<String> args) {
    MarsRoverDateRangeFilter filter = new MarsRoverDateRangeFilter();
    doIfExistsInList("-rover", args, index -> filter.rover(MarsRover.valueOf(args.get(index + 1).toUpperCase())));
    doIfExistsInList("-camera", args, index -> filter.camera(MarsRoverCamera.valueOf(args.get(index + 1).toUpperCase())));
    doIfExistsInList("-days", args, index -> filter.startDate(LocalDate.now().minusDays(Integer.parseInt(args.get(index + 1)))));
    doIfExistsInList("-imagesLimit", args, index -> filter.imagesLimit(Integer.parseInt(args.get(index + 1))));
    return filter;
  }

  static <T> void doIfExistsInList(T element, List<T> list, IntConsumer consumer) {
    int index = list.indexOf(element);
    if (index > -1) {
      try {
        consumer.accept(index);
      } catch (Exception e) {
        throw new UnknownArgumentException("Invalid value for argument: " + element, e);
      }
    }
  }
}
