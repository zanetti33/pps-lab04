package tasks.adts
import u03.Sequences.*
import u03.Optionals.*
import u02.AlgebraicDataTypes.Person
import u03.Sequences.Sequence.filter
import u03.Sequences.Sequence.findFirst

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
      def nameOfCourse(teacher: Teacher): String
      def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]

  object SchoolModuleImpl extends SchoolModule:

    private case class SchoolImpl(teachers: Sequence[Teacher], courses: Sequence[Course])
    private case class TeacherImpl(name: String, courses: Sequence[Course])
    private case class CoruseImpl(name: String)
    opaque type School = SchoolImpl
    opaque type Teacher = TeacherImpl
    opaque type Course = CoruseImpl
    def schoolImpl(): School = SchoolImpl(Sequence.Nil(), Sequence.Nil())
    private def schoolImpl(teacher: Sequence[Teacher])(courses: Sequence[Course]) = SchoolImpl(teacher, courses)
    def teacher(name: String): Teacher = TeacherImpl(name, Sequence.Nil())
    def course(name: String): Course = CoruseImpl(name)

    extension (school: School) override def addCourse(name: String): School = ???

    extension (school: School) override def teacherByName(name: String): Optional[Teacher] = ???

    extension (school: School) override def addTeacher(name: String): School = schoolImpl
      (school.teachers match
        case Sequence.Cons(head, tail) => Sequence.Cons(teacher(name), Sequence.Cons(head, tail))
        case Sequence.Nil() => Sequence.Cons(teacher(name), Sequence.Nil()))
      (school.courses)

    extension (school: School) override def coursesOfATeacher(teacher: Teacher): Sequence[Course] = ???

    extension (school: School) override def setTeacherToCourse(teacher: Teacher, course: Course): School = ???

    extension (school: School) override def nameOfTeacher(teacher: Teacher): String = teacher.name

    extension (school: School) override def nameOfCourse(teacher: Teacher): String = ???

    extension (school: School) override def courseByName(name: String): Optional[Course] = ???