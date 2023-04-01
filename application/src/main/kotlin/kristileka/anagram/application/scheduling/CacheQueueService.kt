package kristileka.anagram.application.scheduling

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CacheQueueService(

) {
    @Scheduled(fixedDelay = 5 * 1000)
    fun checkCacheQueue() {

    }
}