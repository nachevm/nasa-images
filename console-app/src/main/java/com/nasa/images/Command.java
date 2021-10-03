package com.nasa.images;

import lombok.NonNull;

interface Command<T> {

  T execute(String[] args);

  @NonNull
  String getName();
}
