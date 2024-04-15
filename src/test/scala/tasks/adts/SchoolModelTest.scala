package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import u02.AlgebraicDataTypes.Person
import u03.Optionals.Optional
import u03.Sequences.*
import u03.Optionals.*
import org.junit.jupiter.api.BeforeEach
import u03.Optionals.Optional.orElse
import scala.collection.View.Empty

class SchoolModelTest {

    // Choice of implementation to test
    val schoolModule: SchoolModule = SchoolModuleImpl
    import schoolModule.*

    var unibo: School = schoolImpl()

    @BeforeEach def resetSchool() =
        unibo = schoolImpl()
  
    @Test def schoolIsInitiallyEmpty() =
        assertEquals(Optional.Empty(), unibo teacherByName "Viroli")
  
    @Test def coursesCanBeAdded() =
        assertEquals(Optional.Just(course("PPS")), unibo addCourse "PPS" courseByName "PPS")

    @Test def teachersCanBeAdded() =
        assertEquals(Optional.Just(teacher("Viroli")), unibo addTeacher "Viroli" teacherByName "Viroli")
    
    @Test def coursesCanBeSetToTeachers() =
        assertEquals(Sequence.Cons(course("PPS"), Sequence.Nil()), unibo addTeacher "Viroli" setTeacherToCourse(teacher("Viroli"), course("PPS")) coursesOfATeacher teacher("Viroli"))
}
