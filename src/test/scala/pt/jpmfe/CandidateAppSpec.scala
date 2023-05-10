package pt.jpmfe

import Degree.{Bachelor, Masters, PhD}

import collection.mutable.Stack
import org.scalatest.*
import flatspec.*
import matchers.*

import zio.*

import scala.None

class CandidateAppSpec extends AnyFlatSpec with should.Matchers {

  // TODO use zio-test

  it should "return an UUID" in {
    Unsafe.unsafe { implicit unsafe =>
      val repo = MemCandidateRepo(Ref.unsafe.make(Map.empty[String, Candidate]))
      runWithEnv(ZEnvironment(repo)) {
        val c = Candidate(name = "John", studies = Set.empty)
        for id <- CandidateApp.register(c)
        yield id should beValidUUID
      }
    }
  }

  private val beValidUUID: Matcher[String] = (s: String) =>
    MatchResult(
      s.length == 32 + 4,
      s + " was not a valid UUID",
      s + " was a valid UUID"
    )

  private def runWithEnv[E](
      env: ZEnvironment[E]
  )(f: ZIO[E, Throwable, Any]) =
    Unsafe.unsafe { implicit unsafe =>
      Runtime.default
        .withEnvironment[E](env)
        .unsafe
        .run(f)
        .getOrThrowFiberFailure()
    }
}
