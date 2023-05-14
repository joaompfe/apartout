package pt.jpmfe

import Degree.{Bachelor, Masters, PhD}

import collection.mutable.Stack
import org.scalatest.*
import flatspec.*
import matchers.*
import pt.jpmfe.util.DateRange

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

  "associate a skill with a job experience that the Candidate does not have" should "fail" in {
    val skill = Skill("Backend Development")
    val job   = JobExperience(
      role = Role("Soft Eng"),
      org = Organization("Google"),
      span = DateRange.from("2022-01-01") to "2023-01-01"
    )
    Candidate(name = "John", jobs = Set.empty).addSkill(skill, job) should be(
      None
    )
  }
}
