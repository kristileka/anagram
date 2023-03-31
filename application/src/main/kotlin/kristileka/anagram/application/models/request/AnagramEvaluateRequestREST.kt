package kristileka.anagram.application.models.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AnagramEvaluateRequestREST(
    @field:NotNull(message = "firstWord is required on the body.")
    val firstWord: String,

    @field:NotNull(message = "secondWord is required on the body.")
    val secondWord: String
)
