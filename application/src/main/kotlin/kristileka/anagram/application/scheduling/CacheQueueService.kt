package kristileka.anagram.application.scheduling

import kristileka.anagram.domain.service.queue.QueueManagerService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CacheQueueService(val queueManagerService: QueueManagerService) {
    @Scheduled(fixedDelay = 1 * 5 * 1000)
    fun checkCacheQueue() {
        queueManagerService.handleQueue()
    }
}
