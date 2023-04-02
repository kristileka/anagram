package kristileka.anagram.application.models.request

import jakarta.validation.constraints.Size

data class AnagramEvaluateListRequestREST(

    @field:Size(min = 2, message = "At least 2 words are required!")
    val words: List<String>,
)
