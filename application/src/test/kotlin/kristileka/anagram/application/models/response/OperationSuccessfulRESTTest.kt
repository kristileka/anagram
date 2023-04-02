package kristileka.anagram.application.models.response

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OperationSuccessfulRESTTest {

    @Test
    fun `validate field properties OperationSuccessfulREST`() {
        val status = true
        val message = "Test Message"
        val operationSuccessfulREST = OperationSuccessfulREST(status, message)
        assertEquals(operationSuccessfulREST.status, status)
        assertEquals(operationSuccessfulREST.message, message)
    }
}
