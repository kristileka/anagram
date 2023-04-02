package kristileka.anagram.infrastructure.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class StatefulWordEntityTest {

    @Test
    fun `test properties`() {
        val addedValue = "test1"
        val addedPredicate = "test2"
        val addedLetterCount = 5
        val addedId = 1L
        val statefulWordEntity = StatefulWordEntity().apply {
            this.value = addedValue
            this.predicate = addedPredicate
            this.letterCount = addedLetterCount
            this.id = addedId
        }
        assertEquals(statefulWordEntity.value, addedValue)
        assertEquals(statefulWordEntity.predicate, addedPredicate)
        assertEquals(statefulWordEntity.letterCount, addedLetterCount)
        assertEquals(statefulWordEntity.id, addedId)
        assertNotNull(statefulWordEntity.createdAt)
        assert(statefulWordEntity.createdAt!!.isBefore(LocalDateTime.now()))
    }
}