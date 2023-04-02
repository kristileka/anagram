package kristileka.anagram.application.rest

import kristileka.anagram.application.models.messaging.Message
import kristileka.anagram.application.models.response.OperationSuccessfulREST
import kristileka.anagram.domain.service.anagram.AnagramService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("Manage content of stateful words.")
@RequestMapping("/api/v1/content")
class ContentController(
    private val anagramService: AnagramService,
) {

    @PutMapping("insert/{word}")
    fun insertWord(
        @PathVariable("word") word: String,
    ): ResponseEntity<OperationSuccessfulREST> {
        return ResponseEntity.ok(
            OperationSuccessfulREST(
                anagramService.insertWord(word),
                Message.WORD_INSERTED,
            ),
        )
    }
}
