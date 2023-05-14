package pt.jpmfe

case class Candidate(name: String, studies: Set[Study]):
  require(!name.isBlank)
  
  val degree: Option[Degree] = studies.map(_.degree).maxOption
