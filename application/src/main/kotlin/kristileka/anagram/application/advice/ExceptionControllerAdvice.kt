package kristileka.anagram.application.advice

import kristileka.anagram.domain.dto.AnagramException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, Any?>? {
        val errors: MutableMap<String, MutableMap<String, String?>> = HashMap()
        ex.bindingResult.allErrors.forEach { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors.getOrPut("validation_error") { mutableMapOf() }[fieldName] = errorMessage
        }
        return errors
    }

    @ExceptionHandler(AnagramException::class)
    fun handleValidationExceptions(ex: AnagramException): ResponseEntity<ErrorModelREST> {
        return ResponseEntity.status(ex.httpStatus).body(
            ErrorModelREST(
                ex.message,
            ),
        )
    }
}
