package com.raven.datechooser;

import java.time.LocalDate;

public class DateBetween {
  public static final String DATES_FLIPPED_ERROR = "The given dates are flipped!\nThe `from` date can't be after the `to` date.";

  private final LocalDate from;
  private final LocalDate to;

  public DateBetween(LocalDate from, LocalDate to) {
    if (from.isAfter(to)) {
      LocalDate tmp = from;
      from = to;
      to = tmp;
    }

    this.from = from;
    this.to = to;
  }

  public LocalDate getFrom() {
    return from;
  }

  public LocalDate getTo() {
    return to;
  }
}
