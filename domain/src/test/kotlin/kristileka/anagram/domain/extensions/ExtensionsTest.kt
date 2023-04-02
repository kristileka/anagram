package kristileka.anagram.domain.extensions

import kristileka.anagram.domain.extensions.Extensions.getPredicate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExtensionsTest {

    @Test
    fun `Test predicate function works correctly`() {
        val testWord = "dsa"
        val predicateExpected = "ads"

        assertEquals(predicateExpected, testWord.getPredicate())
    }
}