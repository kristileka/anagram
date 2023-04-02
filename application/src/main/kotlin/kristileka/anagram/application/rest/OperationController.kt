package kristileka.anagram.application.rest

import jakarta.validation.Valid
import kristileka.anagram.application.models.messaging.Message
import kristileka.anagram.application.models.request.AnagramEvaluateListRequestREST
import kristileka.anagram.application.models.request.AnagramEvaluateRequestREST
import kristileka.anagram.application.models.response.AnagramEvaluateListResponseREST
import kristileka.anagram.application.models.response.AnagramSearchOperationResponseREST
import kristileka.anagram.application.models.response.AnagramSingleEvaluationResponseREST
import kristileka.anagram.application.models.response.OperationSuccessfulREST
import kristileka.anagram.domain.service.anagram.AnagramService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/evaluate")
class OperationController(
    private val wordService: AnagramService,
) {

    @GetMapping("singular/{first}/{second}")
    suspend fun evaluateInLine(
        @PathVariable("first") firstWord: String,
        @PathVariable("second") secondWord: String,
    ): ResponseEntity<AnagramSingleEvaluationResponseREST> {
        return ResponseEntity.ok(
            AnagramSingleEvaluationResponseREST(
                wordService.evaluateAnagram(firstWord, secondWord),
            ),
        )
    }

    @PostMapping
    suspend fun evaluate(
        @Valid @RequestBody
        request: AnagramEvaluateRequestREST,
    ): ResponseEntity<AnagramSingleEvaluationResponseREST> {
        return ResponseEntity.ok(
            AnagramSingleEvaluationResponseREST(
                wordService.evaluateAnagram(
                    request.firstWord,
                    request.secondWord,
                ),
            ),
        )
    }

    @PostMapping("multiple")
    suspend fun evaluateMultiple(
        @Valid @RequestBody
        request: AnagramEvaluateListRequestREST,
    ): ResponseEntity<AnagramEvaluateListResponseREST> {
        return ResponseEntity.ok(
            AnagramEvaluateListResponseREST(
                wordService.evaluateAnagram(request.words),
            ),
        )
    }

    @PutMapping("insert/{word}")
    fun evaluateMultiple(
        @PathVariable("word") word: String,
    ): ResponseEntity<OperationSuccessfulREST> {
        return ResponseEntity.ok(
            OperationSuccessfulREST(
                wordService.insertWord(word),
                Message.WORD_INSERTED,
            ),
        )
    }

    @GetMapping("search/{word}")
    fun searchForAnagram(
        @PathVariable("word") word: String,
    ): ResponseEntity<AnagramSearchOperationResponseREST> {
        return ResponseEntity.ok(
            AnagramSearchOperationResponseREST(
                wordService.searchForAnagram(word),
                word,
            ),
        )
    }
}
