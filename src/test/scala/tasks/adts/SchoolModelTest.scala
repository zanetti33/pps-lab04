package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.SchoolModule
import u02.AlgebraicDataTypes.Person
import u03.Optionals.Optional
import org.junit.jupiter.api.BeforeEach

class SchoolModelTest {

    // Choice of implementation to test
  val schoolModelADT: SchoolModule = SchoolModuleImpl
  import schoolModelADT.*

  @BeforeEach def initializeSchool() =
    val school = school()

  // From now, everything is independent of specific implementation of Complex

  @Test def teachersCanBeAdded() =
    assertEquals(Optional.Empty, school teacherByName "Viroli")
    

}
