package pt.jpmfe

import zio.*

object CandidateApp:

  def register: Candidate => RIO[CandidateRepo, String] = CandidateRepo.save

  def find: String => RIO[CandidateRepo, Option[Candidate]] =
    CandidateRepo.find

  def all(): RIO[CandidateRepo, List[Candidate]] = CandidateRepo.all()
