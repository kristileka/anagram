package kristileka.anagram.application.advice

import io.mockk.every
import io.mockk.mockk
import kristileka.anagram.domain.dto.AnagramCouldNotBeFound
import kristileka.anagram.domain.dto.WordAlreadyRegistered
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import java.lang.reflect.Executable

class ExceptionControllerAdviceTest {

    private val exceptionControllerAdvice = ExceptionControllerAdvice()

    @Test
    fun `test advice for MethodArgumentNotValidException`() {
        val executable = mockk<Executable>()
        val bindingResult = mockk<BindingResult>()
        val error1 = mockk<FieldError>()
        every {
            error1.field
        } returns "fieldName1"
        every {
            error1.defaultMessage
        } returns "error message 1"
        every {
            bindingResult.allErrors
        } returns listOf(error1)
        val exception = MethodArgumentNotValidException(executable, bindingResult)

        val result = exceptionControllerAdvice.handleValidationExceptions(exception)
        val expectedMap = mapOf(
            "validation_error" to mapOf(
                "fieldName1" to "error message 1",
            ),
        )
        assertEquals(expectedMap, result)
    }

    @Test
    fun `test advice for WordAlreadyRegistered`() {
        val exception1 = WordAlreadyRegistered()
        val result = exceptionControllerAdvice.handleValidationExceptions(exception1)
        assertEquals(result.body?.message, exception1.message)
    }

    @Test
    fun `test advice for AnagramCouldNotBeFound`() {
        val exception1 = AnagramCouldNotBeFound()
        val result = exceptionControllerAdvice.handleValidationExceptions(exception1)
        assertEquals(result.body?.message, exception1.message)
    }
}
