package pt.jpmfe

import pt.jpmfe.util.DateRange

case class Project(
    description: String,
    span: DateRange,
    org: Option[Organization]
)
