package pt.jpmfe.util

import java.time.{Duration, LocalDate}

case class DateRange(startInclusive: LocalDate, endExclusive: LocalDate):
  require(startInclusive.isBefore(endExclusive))

  lazy val diff: Duration =
    Duration.between(startInclusive.atStartOfDay(), endExclusive.atStartOfDay())

object DateRange:
  def from(date: String): PartialDateRange =
    PartialDateRange(LocalDate.parse(date))

case class PartialDateRange(startInclusive: LocalDate):
  def to(date: String): DateRange = DateRange(
    startInclusive,
    endExclusive = LocalDate.parse(date)
  )
