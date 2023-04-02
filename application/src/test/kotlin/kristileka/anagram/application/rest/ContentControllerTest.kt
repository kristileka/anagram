package kristileka.anagram.application.rest

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kristileka.anagram.application.models.messaging.Message
import kristileka.anagram.domain.service.anagram.AnagramService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ContentControllerTest {
    @MockK
    private val anagramService: AnagramService = mockk()
    private lateinit var contentController: ContentController

    @BeforeEach
    fun init() {
        contentController = ContentController(anagramService)
    }

    @Test
    fun `insert word controller`() {
        every { anagramService.insertWord(any()) } returns true
        val result = contentController.insertWord("test")
        assertEquals(result.statusCode, HttpStatus.OK)
        assertNotNull(result.body)
        assertTrue(result.body!!.status)
        assertEquals(result.body!!.message, Message.WORD_INSERTED)
    }
}
