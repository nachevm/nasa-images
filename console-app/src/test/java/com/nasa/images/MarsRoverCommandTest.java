package com.nasa.images;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.nasa.images.mars.rover.api.MarsRoverDateRangeFilter;
import com.nasa.images.mars.rover.api.MarsRoverService;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MarsRoverCommandTest {

  @InjectMocks
  private MarsRoverCommand marsRoverCommand;
  @Mock
  private MarsRoverService marsRoverService;

  @Test
  void execute_reversesMapToDesc() {
    TreeMap<LocalDate, List<String>> map = new TreeMap<>();
    map.put(LocalDate.of(2021, 1, 1), List.of("1"));
    map.put(LocalDate.of(2021, 1, 2), List.of("2"));
    doReturn(map).when(marsRoverService).getPhotos(any(MarsRoverDateRangeFilter.class));

    assertEquals(map.descendingMap(), marsRoverCommand.execute(new String[]{}));
  }
}
