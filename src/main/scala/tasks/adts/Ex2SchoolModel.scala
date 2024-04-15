package tasks.adts
import u03.Sequences.*
import u03.Optionals.*
import u02.AlgebraicDataTypes.Person
import scala.collection.immutable.Stream.Cons

/*  Exercise 2: 
 *  Implement the below trait, and write a meaningful test.
 *  Suggestion: 
 *  - reuse Sequences and Optionals as imported above
 *  - Course is a simple case classes with just the name
 *  - Teacher is a case class with name and sequence of courses
 *  - School is a case class with (sequences of) teachers and courses
 *  - add/set methods below create the new school 
 */

object SchoolModel:

  trait SchoolModule:
    type School
    type Teacher
    type Course
    def teacher(name: String): Teacher
    def course(name: String): Course
    def schoolImpl(): School
    extension (school: School)
      def addTeacher(name: String): School
      def addCourse(name: String): School
      def teacherByName(name: String): Optional[Teacher]
      def courseByName(name: String): Optional[Course]
      def nameOfTeacher(teacher: Teacher): String
      def nameOfCourse(course: Course): String
      def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]

  object SchoolModuleImpl extends SchoolModule:

    private case class SchoolImpl(teachers: Sequence[Teacher], courses: Sequence[Course])
    private case class TeacherImpl(name: String, courses: Sequence[Course])
    private case class CourseImpl(name: String)
    opaque type School = SchoolImpl
    opaque type Teacher = TeacherImpl
    opaque type Course = CourseImpl
    def schoolImpl(): School = SchoolImpl(Sequence.Nil(), Sequence.Nil())
    private def schoolImpl(teachers: Sequence[Teacher])(courses: Sequence[Course]) = SchoolImpl(teachers, courses)
    def teacher(name: String): Teacher = TeacherImpl(name, Sequence.Nil())
    def course(name: String): Course = CourseImpl(name)

    extension (school: School) override def addCourse(name: String): School = 
      schoolImpl(school.teachers)(Sequence.Cons(CourseImpl(name), school.courses))

    extension (school: School) override def teacherByName(name: String): Optional[Teacher] = 
      Sequence.findFirst(school.teachers)(_.name == name)

    extension (school: School) override def addTeacher(name: String): School = 
      schoolImpl(Sequence.Cons(TeacherImpl(name, Sequence.Nil()), school.teachers))(school.courses)

    extension (school: School) override def coursesOfATeacher(teacher: Teacher): Sequence[Course] =
      Sequence.findFirst(school.teachers)(_.name == teacher.name) match
        case Optional.Just(a) => a.courses
        case Optional.Empty() => Sequence.Nil()

    extension (school: School) override def setTeacherToCourse(teacher: Teacher, course: Course): School =
      schoolImpl(Sequence.map[Teacher, Teacher](school.teachers)(t => t match
        case x if x.name == teacher.name => TeacherImpl(t.name, Sequence.Cons(course, t.courses))
        case _ => t)
      )(school.courses)
      
      
    extension (school: School) override def nameOfTeacher(teacher: Teacher): String = teacher.name

    extension (school: School) override def nameOfCourse(course: Course): String = course.name

    extension (school: School) override def courseByName(name: String): Optional[Course] = 
      Sequence.findFirst(school.courses)(_.name == name)
    
@main def trySchool =
  import SchoolModel.SchoolModuleImpl.*
  var unibo: School = schoolImpl()
  val newUnibo = unibo addTeacher "Viroli" setTeacherToCourse(teacher("Viroli"), course("PPS"))
  println(unibo addTeacher "Viroli" setTeacherToCourse(teacher("Viroli"), course("PPS")) coursesOfATeacher teacher("Viroli"))

