package pt.jpmfe

import Degree.{Bachelor, Masters, PhD}

import collection.mutable.Stack
import org.scalatest.*
import flatspec.*
import matchers.*

import scala.None

class CandidateSpec extends AnyFlatSpec with should.Matchers {

  "instantiating a Candidate" should "fail if the name is blank" in {
    assertThrows[IllegalArgumentException] {
      Candidate(name = " ", studies = Set.empty)
    }
  }

  "Candidate's degree" should "be the highest degree of his degrees" in {
    val c = Candidate(
      name = "John",
      studies = Set(
        Study(degree = Bachelor, course = "Medicine", institution = "School"),
        Study(degree = PhD, course = "Informatics", institution = "School"),
        Study(degree = Masters, course = "Music", institution = "School")
      )
    )

    c.degree should be(Some(PhD))
  }

  it should "be None if the candidate has no studies" in {
    val c = Candidate(name = "John", studies = Set.empty)

    c.degree should be(None)
  }
}
