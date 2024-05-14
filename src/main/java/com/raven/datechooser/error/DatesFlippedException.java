package com.raven.datechooser.error;

public class DatesFlippedException extends RuntimeException {
  public DatesFlippedException(String errorMessage) {
    super(errorMessage);
  }
}
