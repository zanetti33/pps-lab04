package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import u02.AlgebraicDataTypes.Person
import u03.Optionals.Optional
import org.junit.jupiter.api.BeforeEach
import u03.Optionals.Optional.orElse

class SchoolModelTest {

    // Choice of implementation to test
  val schoolModule: SchoolModule = SchoolModuleImpl
  import schoolModule.*

  var unibo: School = school()

  @BeforeEach def resetSchool() =
    unibo = school()

  // From now, everything is independent of specific implementation of Complex

  @Test def teachersCanBeAdded() =
    assertEquals(Optional.Empty, unibo teacherByName "Viroli")
    unibo addTeacher "Viroli"
    assertEquals(Optional.Just(Teacher), unibo nameOfTeacher (orElse(unibo teacherByName "Viroli")))
    

}
