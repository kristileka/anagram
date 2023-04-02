package kristileka.anagram.application.rest

import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kristileka.anagram.application.models.request.AnagramEvaluateListRequestREST
import kristileka.anagram.application.models.request.AnagramEvaluateRequestREST
import kristileka.anagram.domain.dto.EvaluationResult
import kristileka.anagram.domain.service.anagram.AnagramService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class OperationControllerTest {
    @MockK
    private val anagramService: AnagramService = mockk()
    private lateinit var operationController: OperationController

    @BeforeEach
    fun init() {
        operationController = OperationController(anagramService)
    }

    @Test
    fun `evaluate in with parameters`() = runBlocking {
        val evaluationResult = EvaluationResult("test", "test", true)
        coEvery {
            anagramService.evaluateAnagram("test", "test")
        } returns evaluationResult
        val result = operationController.evaluateInLine("test", "test")
        assertEquals(result.statusCode, HttpStatus.OK)
        assertNotNull(result.body)
        assertEquals(result.body!!.result, evaluationResult.result)
        assertEquals(result.body!!.firstWord, evaluationResult.word)
        assertEquals(result.body!!.secondWord, evaluationResult.potentialAnagram)
    }

    @Test
    fun `evaluate with object body `() = runBlocking {
        val request = AnagramEvaluateRequestREST("test", "test")
        val evaluationResult = EvaluationResult("test", "test", true)
        coEvery {
            anagramService.evaluateAnagram("test", "test")
        } returns evaluationResult
        val result = operationController.evaluate(request)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertNotNull(result.body)
        assertEquals(result.body!!.result, evaluationResult.result)
        assertEquals(result.body!!.firstWord, evaluationResult.word)
        assertEquals(result.body!!.secondWord, evaluationResult.potentialAnagram)
    }

    @Test
    fun `evaluate multiple anagrams`() = runBlocking {
        val request = AnagramEvaluateListRequestREST(listOf("test", "test1"))
        val evaluationResult = listOf(
            EvaluationResult("test", "test1", true),
            EvaluationResult("test", "test1", true),
        )
        coEvery {
            anagramService.evaluateAnagram(any<List<String>>())
        } returns Pair(evaluationResult, "test")
        val result = operationController.evaluateMultiple(request)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertNotNull(result.body)
        assertEquals(
            result.body!!.selectedWord,
            "test",
        )
        result.body!!.results.forEach {
            assertTrue(it.isAnagram)
            assertTrue(it.word == "test" || it.word == "test1")
        }
    }

    @Test
    fun `search for anagram`() {
        every {
            anagramService.searchForAnagram("test")
        } returns listOf("test", "test1")
        val result = operationController.searchForAnagram("test")
        assertEquals(result.statusCode, HttpStatus.OK)
        assertNotNull(result.body)
        assertEquals(result.body!!.word, "test")
        result.body!!.storedAnagrams.forEach {
            assertTrue(
                it == "test" || it == "test1",
            )
        }
    }
}
