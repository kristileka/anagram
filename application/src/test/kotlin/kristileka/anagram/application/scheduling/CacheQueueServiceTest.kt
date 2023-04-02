package kristileka.anagram.application.scheduling

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kristileka.anagram.domain.service.queue.QueueManagerService
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CacheQueueServiceTest {

    @MockK
    private val queueManagerService: QueueManagerService = mockk()
    private lateinit var cacheQueueService: CacheQueueService

    @BeforeEach
    fun init() {
        cacheQueueService = CacheQueueService(queueManagerService)
    }

    @Test
    fun `check for cache queue`() {
        every { queueManagerService.handleQueue() } returns Unit
        assertDoesNotThrow {
            cacheQueueService.checkCacheQueue()
        }
    }
}
