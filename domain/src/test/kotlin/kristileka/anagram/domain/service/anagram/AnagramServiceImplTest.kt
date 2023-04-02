package kristileka.anagram.domain.service.anagram

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import kristileka.anagram.domain.dto.AnagramCouldNotBeFound
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.dto.WordAlreadyRegistered
import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AnagramServiceImplTest {
    private val backgroundScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @MockK
    val statefulWordRepository: StatefulWordRepository = mockk()

    @MockK
    var statelessWordRepository: StatelessWordRepository = mockk()

    private lateinit var anagramService: AnagramService

    @BeforeEach
    fun init() {
        anagramService = AnagramServiceImpl(backgroundScope, statefulWordRepository, statelessWordRepository)
    }

    @Test
    fun `evaluate anagram doesnt match`() = runBlocking {
        val word1 = "word"
        val word2 = "wrod1"
        val result = anagramService.evaluateAnagram(word1, word2)
        assertEquals(result.word, word1)
        assertEquals(result.potentialAnagram, word2)
        assertFalse(result.result)
    }

    @Test
    fun `evaluate anagram match`() = runBlocking {
        val word1 = "word"
        val word2 = "wrod"
        val result = anagramService.evaluateAnagram(word1, word2)
        assertEquals(result.word, word1)
        assertEquals(result.potentialAnagram, word2)
        assertTrue(result.result)
    }

    @Test
    fun `evaluate listAnagrams`() = runBlocking {
        val words = listOf("word", "dowr", "asdd", "word2")
        val results = anagramService.evaluateAnagram(words)
        assertEquals(words.first(), results.second)
        assertTrue(results.first.first().result)
        assertTrue(results.first[1].result)
        assertFalse(results.first[2].result)
        assertFalse(results.first[3].result)
    }

    @Test
    fun `evaluate listAnagrams dynamically`() = runBlocking {
        val words = mapOf("word" to true, "dowr" to true, "asdd" to false, "word2" to false)

        val results = anagramService.evaluateAnagram(
            words.map {
                it.key
            },
        )
        results.first.forEach {
            assertEquals(it.result, words[it.word]!!)
        }
    }

    @Test
    fun `insert throws error`() {
        every {
            statefulWordRepository.findWordByValue("test")
        } returns Word("test", "etts")
        assertThrows(
            WordAlreadyRegistered::class.java,
        ) { anagramService.insertWord("test") }
    }

    @Test
    fun `insert is not done correctly`() {
        every {
            statefulWordRepository.findWordByValue("test")
        } returns null
        every {
            statefulWordRepository.save(any())
        } returns null
        val result = anagramService.insertWord("test")
        assertFalse(result)
    }

    @Test
    fun `insert is done correctly`() {
        val word = Word("test", "etts")
        every {
            statefulWordRepository.findWordByValue("test")
        } returns null
        every {
            statefulWordRepository.save(any())
        } returns word
        val result = anagramService.insertWord("test")
        assertTrue(result)
    }

    @Test
    fun `search for anagram will return empty list`() {
        every {
            statefulWordRepository.filterByPredicate("ads")
        } returns listOf()
        assertThrows(
            AnagramCouldNotBeFound::class.java,
        ) { anagramService.searchForAnagram("sad") }
    }

    @Test
    fun `search for anagram will return list with elements`() {
        val words = listOf(Word("test", "Test"))
        every {
            statefulWordRepository.filterByPredicate("ads")
        } returns words
        val result = anagramService.searchForAnagram("sad")
        assertTrue(result.isNotEmpty())
        assertEquals(words.first().value, result.first())
    }
}
