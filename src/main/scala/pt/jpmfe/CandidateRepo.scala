package pt.jpmfe

import zio.*

trait CandidateRepo:

  def save(c: Candidate): Task[String]

  def find(id: String): Task[Option[Candidate]]

  def all(): Task[List[Candidate]]

object CandidateRepo:
  def save(c: Candidate): RIO[CandidateRepo, String] =
    ZIO.serviceWithZIO[CandidateRepo](_.save(c))

  def find(id: String): RIO[CandidateRepo, Option[Candidate]] =
    ZIO.serviceWithZIO[CandidateRepo](_.find(id))

  def all(): RIO[CandidateRepo, List[Candidate]] =
    ZIO.serviceWithZIO[CandidateRepo](_.all())
