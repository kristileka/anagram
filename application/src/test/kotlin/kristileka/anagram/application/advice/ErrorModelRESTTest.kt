package kristileka.anagram.application.advice

import kristileka.anagram.application.models.messaging.Message
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ErrorModelRESTTest {

    @Test
    fun `test error model properties`() {
        val message = Message.WORD_INSERTED
        val errorModelREST = ErrorModelREST(message)
        assertEquals(message, errorModelREST.message)
        assertEquals(errorModelREST.message, "Word was registered successfully")
    }
}
