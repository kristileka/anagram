package kristileka.anagram.domain.service.queue

import io.mockk.every
import io.mockk.mockk
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

class QueueManagerServiceImplTest {

    @Mock
    private val statefulWordRepository: StatefulWordRepository = mockk()

    @Mock
    private val statelessWordRepository: StatelessWordRepository = mockk()

    private lateinit var queueManager: QueueManagerService

    @BeforeEach
    fun init() {
        queueManager = QueueManagerServiceImpl(statelessWordRepository, statefulWordRepository)
    }

    @Test
    fun `test handle queue`() {
        every {
            statelessWordRepository.getCurrentQueue()
        } returns listOf()
        every {
            statefulWordRepository.findWordsByValueIn(any())
        } returns listOf()
        every {
            statefulWordRepository.saveAll(any<List<Word>>())
        } returns listOf()
        every {
            statelessWordRepository.cleanQueue()
        } returns Unit
        assertDoesNotThrow {
            queueManager.handleQueue()
        }
    }
}
