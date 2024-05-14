package com.raven.datechooser;

import java.time.LocalDate;

import com.raven.datechooser.error.DatesFlippedException;

public class DateBetween {
  public static final String DATES_FLIPPED_ERROR = "The given dates are flipped!\nThe `from` date can't be after the `to` date.";

  private final LocalDate from;
  private final LocalDate to;

  public DateBetween(LocalDate from, LocalDate to) throws DatesFlippedException {
    if (from.isAfter(to))
      throw new DatesFlippedException(DateBetween.DATES_FLIPPED_ERROR);

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
