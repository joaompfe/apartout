package pt.jpmfe

import zio.*
import zio.http.*

import scala.util.Try

object Main extends ZIOAppDefault {
  override val run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] =
    Server
      .serve(HttpApp())
      .provide(Server.defaultWithPort(8000), MemCandidateRepo.layer)
}

object HttpApp {
  def apply(): App[CandidateRepo] =
    Http.collectZIO[Request] {
      case Method.GET -> !! / "hi"                => ZIO.succeed(Response.text("Hi!"))
      case req @ Method.POST -> !! / "candidates" =>
        for
          name <- ZIO.fromOption(req.queryArg("name")).mapError(_ => BadRequest)
          c     = Candidate(name, studies = Set.empty)
          id   <- CandidateApp.register(c).mapError(_ => InternalServerError)
        yield Response.text(id)
    }

  extension (req: Request)
    private def queryArg(arg: String): Option[String] =
      req.url.queryParams.get(arg).flatMap(n => Try(n.head).toOption)

  private lazy val BadRequest          = Response.status(Status.BadRequest)
  private lazy val InternalServerError =
    Response.status(Status.InternalServerError)
}
