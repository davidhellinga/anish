package animal

import main.kotlin.animal.Cat
import main.kotlin.animal.Gender
import main.kotlin.animal.Reservor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class CatTest {

    private val name = "TestName"
    private val gender = Gender.Male
    private val badHabits = "TestHabits"
    private val reservorName = "John"

    @Test
    fun `cat constructor creates cat with input data`() {
        val cat = Cat(name, gender, badHabits)
        assertEquals(cat.name, name)
        assertEquals(cat.gender, gender)
        assertEquals(cat.badHabits, badHabits)
    }

    @Test
    fun `price is set to correct value for automatic pricing`() {
        val cat = Cat(name, gender, badHabits)
        var temp = (350.0 - 20.0 * badHabits.length)
        if (temp < 35.0) temp = 35.0
        assertEquals(cat.price, temp)
    }

    @Test
    fun `price is set to correct value for manual pricing`() {
        val cat = Cat(name, gender, badHabits, 50.0)
        assertEquals(cat.price, 50.0)
    }

    @Test
    fun `cat is correctly reserved`() {
        val cat = Cat(name, gender, badHabits)
        val now = LocalDateTime.now()
        cat.reserve(reservorName, now)
        assertEquals(cat.reservedBy, Reservor(reservorName, now))
    }
}