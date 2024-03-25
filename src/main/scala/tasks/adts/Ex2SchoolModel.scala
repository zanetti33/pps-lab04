package tasks.adts
import u03.Sequences.*
import u03.Optionals.*
import u02.AlgebraicDataTypes.Person

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

      private case class SchoolImpl(teachers: List[Teacher], courses: List[Course])
      
      opaque type School = SchoolImpl

      extension (school: School) override def addCourse(name: String): School = ???

      extension (school: School) override def teacherByName(name: String): Optional[Teacher] = ???

      extension (school: School) override def addTeacher(name: String): School = ???

      extension (school: School) override def coursesOfATeacher(teacher: Teacher): Sequence[Course] = ???

      extension (school: School) override def setTeacherToCourse(teacher: Teacher, course: Course): School = ???

      extension (school: School) override def nameOfTeacher(teacher: Teacher): String = ???

      extension (school: School) override def nameOfCourse(teacher: Teacher): String = ???

      extension (school: School) override def courseByName(name: String): Optional[Course] = ???