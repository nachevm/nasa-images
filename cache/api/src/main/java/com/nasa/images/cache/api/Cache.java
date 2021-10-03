package com.nasa.images.cache.api;

import java.util.Optional;
import lombok.NonNull;

public interface Cache<T> {

  Optional<T> get(@NonNull Object key);

  T put(@NonNull Object key, T value);
}
