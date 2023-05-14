package pt.jpmfe.util

import org.scalatest.*
import flatspec.*

import java.time.{Duration, LocalDate}

class DateRangeTest extends AnyFlatSpec {

  "instantiating a negative range" should "fail" in {
    assertThrows[IllegalArgumentException] {
      DateRange(
        startInclusive = LocalDate.parse("2023-01-01"),
        endExclusive = LocalDate.parse("2022-01-01")
      )
    }
  }

  "instantiating an empty range" should "fail" in {
    assertThrows[IllegalArgumentException] {
      DateRange(
        startInclusive = LocalDate.parse("2023-01-01"),
        endExclusive = LocalDate.parse("2023-01-01")
      )
    }
  }

  "diff between 2023-01-01 and 2023-01-14" should "be 14 days" in {
    DateRange(
      startInclusive = LocalDate.parse("2023-01-01"),
      endExclusive = LocalDate.parse("2023-01-14")
    ).diff == Duration.ofDays(14)
  }
}
