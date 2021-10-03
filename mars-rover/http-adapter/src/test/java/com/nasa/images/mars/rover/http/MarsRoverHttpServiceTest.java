package com.nasa.images.mars.rover.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.images.cache.api.Cache;
import com.nasa.images.mars.rover.api.MarsRoverDateFilter;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MarsRoverHttpServiceTest {

  @InjectMocks
  private MarsRoverHttpService marsRoverHttpService;
  @Mock
  private Cache<List<String>> cache;
  @Mock
  private MarsRoverProperties properties;
  @Mock
  private ObjectMapper objectMapper;

  @Test
  void getPhotos_usesCacheIfFoundAndDoesNotCallNasaApi() throws Exception {
    doReturn(Optional.of(List.of("1"))).when(cache).get(new MarsRoverDateFilter());

    assertEquals(List.of("1"), marsRoverHttpService.getPhotos(new MarsRoverDateFilter()));
    verify(objectMapper, times(0)).readValue(any(URL.class), eq(MarsRoverResponse.class));
    verify(cache, times(0)).put(any(), any());
  }

  @Test
  void getPhotos_usesNasaApiIfNotFoundInCacheAndPutsInCache() throws Exception {
    MarsRoverDateFilter filter = new MarsRoverDateFilter();
    doReturn("https://").when(properties).getBaseUrl();
    doReturn(Optional.empty()).when(cache).get(filter);
    when(cache.put(filter, List.of("1"))).thenAnswer(input -> input.getArguments()[1]);
    doReturn(MarsRoverResponse.builder().photos(List.of(MarsRoverPhoto.builder().imgSrc("1").build())).build())
        .when(objectMapper)
        .readValue(any(URL.class), eq(MarsRoverResponse.class));

    assertEquals(List.of("1"), marsRoverHttpService.getPhotos(filter));
    verify(cache).get(filter);
    verify(cache).put(filter, List.of("1"));
  }
}
