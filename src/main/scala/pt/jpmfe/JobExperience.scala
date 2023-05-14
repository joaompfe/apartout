package pt.jpmfe

import pt.jpmfe.util.DateRange

case class JobExperience(span: DateRange, role: Role, org: Organization)

case class Role(description: String) extends AnyVal

case class Organization(name: String) extends AnyVal
