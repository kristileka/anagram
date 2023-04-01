package kristileka.anagram.domain.dto

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

sealed class AnagramException(override val message: String, val httpStatus: HttpStatus) : RuntimeException(message)
class WordAlreadyRegistered() : AnagramException("Word is already registered!", HttpStatus.CONFLICT)
class AnagramCouldNotBeFound() : AnagramException("Anagram couldn't be found", HttpStatus.NOT_FOUND)
