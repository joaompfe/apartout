package pt.jpmfe

import zio.*

class MemCandidateRepo(cs: Ref[Map[String, Candidate]]) extends CandidateRepo:

  override def save(c: Candidate): Task[String] =
    for
      id <- Random.nextUUID.map(_.toString)
      _  <- cs.update(_ + (id -> c))
    yield id

  override def find(id: String): Task[Option[Candidate]] =
    cs.get.map(_.get(id))

  override def all(): Task[List[Candidate]] = cs.get.map(_.values.toList)

object MemCandidateRepo:
  def layer: ZLayer[Any, Nothing, MemCandidateRepo] = // ULayer
    ZLayer.fromZIO(
      Ref.make(Map.empty[String, Candidate]).map(MemCandidateRepo(_))
    )
