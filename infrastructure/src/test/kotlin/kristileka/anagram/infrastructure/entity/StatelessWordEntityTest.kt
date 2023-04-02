package kristileka.anagram.infrastructure.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StatelessWordEntityTest {
    @Test
    fun `test validate properties StatelessWordEntity `() {
        val addedValue = "test1"
        val addedPredicate = "test2"
        val addedId = "test"
        val statefulWordEntity = StatelessWordEntity().apply {
            this.value = addedValue
            this.predicate = addedPredicate
            this.id = addedId
        }
        Assertions.assertEquals(statefulWordEntity.value, addedValue)
        Assertions.assertEquals(statefulWordEntity.predicate, addedPredicate)
        Assertions.assertEquals(statefulWordEntity.id, addedId)
    }
}
