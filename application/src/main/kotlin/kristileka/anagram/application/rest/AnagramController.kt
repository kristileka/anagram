package kristileka.anagram.application.rest

import jakarta.validation.Valid
import kristileka.anagram.application.models.request.AnagramEvaluateListRequestREST
import kristileka.anagram.application.models.request.AnagramEvaluateRequestREST
import kristileka.anagram.application.models.response.AnagramEvaluateListResponseREST
import kristileka.anagram.application.models.response.AnagramResultSingularResponseREST
import kristileka.anagram.domain.service.WordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/evaluate")
class AnagramController(
    private val wordService: WordService,
) {

    @GetMapping("/{first}/{second}")
    fun evaluateInLine(
        @PathVariable("first") firstWord: String, @PathVariable("second") secondWord: String
    ): ResponseEntity<AnagramEvaluateListResponseREST> {
        return ResponseEntity.ok(
            AnagramEvaluateListResponseREST(
                wordService.evaluateAnagram(firstWord, secondWord)
            )
        )
    }

    @PostMapping
    fun evaluate(
        @Valid @RequestBody request: AnagramEvaluateRequestREST
    ): ResponseEntity<AnagramEvaluateListResponseREST> {
        return ResponseEntity.ok(
            AnagramEvaluateListResponseREST(
                wordService.evaluateAnagram(
                    request.firstWord, request.secondWord
                )
            )
        )
    }

    @PostMapping("multiple")
    fun evaluateMultiple(
        @Valid @RequestBody request: AnagramEvaluateListRequestREST
    ): ResponseEntity<AnagramEvaluateListResponseREST> {
        return ResponseEntity.ok(
            AnagramEvaluateListResponseREST(
                wordService.evaluateAnagram(request.words)
            )
        )
    }
}
