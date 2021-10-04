package com.nasa.images;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class UnknownArgumentException extends RuntimeException {

  private static final long serialVersionUID = 8584090650944612848L;

  UnknownArgumentException(String s, Exception e) {
    super(s, e);
  }
}
