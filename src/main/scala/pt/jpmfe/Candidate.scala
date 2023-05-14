package pt.jpmfe

case class Candidate(
    name: String,
    studies: Set[Study] = Set.empty,
    jobs: Set[JobExperience] = Set.empty,
    projects: Set[Project] = Set.empty,
    skills: Map[Skill, Set[Experience]] = Map.empty
):
  require(!name.isBlank)

  val degree: Option[Degree] = studies.map(_.degree).maxOption

  private def hasExperience(experience: Experience): Boolean =
    experience match
      case e: JobExperience => jobs.contains(e)
      case e: Project       => projects.contains(e)

  def addSkill(skill: Skill, experience: Experience): Option[Candidate] =
    Option.when(hasExperience(experience)) {
      val skillExperiences: Set[Experience] = skills.getOrElse(skill, Set.empty)
      copy(skills =
        skills.removed(skill) ++ Map(skill -> skillExperiences.incl(experience))
      )
    }

type Experience = JobExperience | Project
