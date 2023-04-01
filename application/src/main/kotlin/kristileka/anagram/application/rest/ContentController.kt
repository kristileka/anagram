package kristileka.anagram.application.rest

import kristileka.anagram.application.models.messaging.Message
import kristileka.anagram.application.models.response.OperationSuccessfulREST
import kristileka.anagram.domain.service.anagram.AnagramService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/content")
class ContentController(
    private val wordService: AnagramService,
) {
    @PutMapping("insert/{word}")
    fun evaluateMultiple(
        @PathVariable("word") word: String
    ): ResponseEntity<OperationSuccessfulREST> {
        return ResponseEntity.ok(
            OperationSuccessfulREST(
                wordService.insertWord(word), Message.WORD_INSERTED
            )
        )
    }
}
