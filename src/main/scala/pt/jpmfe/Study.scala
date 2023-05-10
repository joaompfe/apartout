package pt.jpmfe

case class Study(degree: Degree, course: Course, institution: Institution)

class Course(name: String) extends AnyVal

given Conversion[String, Course] with
  override def apply(name: String): Course = Course(name)

enum Degree:
  case Bachelor, Masters, PhD

given Ordering[Degree] with
  override def compare(x: Degree, y: Degree): Int = x.ordinal compare y.ordinal

class Institution(name: String) extends AnyVal

given Conversion[String, Institution] with
  override def apply(name: String): Institution = Institution(name)
