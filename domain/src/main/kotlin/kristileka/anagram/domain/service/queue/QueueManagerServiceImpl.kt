package kristileka.anagram.domain.service.queue

import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import org.springframework.stereotype.Service

@Service
class QueueManagerServiceImpl(
    val statelessWordRepository: StatelessWordRepository,
    val statefulWordRepository: StatefulWordRepository,
) : QueueManagerService {
    override fun handleQueue() {
        val statelessWords = statelessWordRepository.getCurrentQueue().distinctBy { it.value }
        val wordsInState = statefulWordRepository.findWordsByValueIn(
            statelessWords.map {
                it.value
            },
        )
        val wordsForState = statelessWords.filter { it.value !in wordsInState }
        statefulWordRepository.saveAll(
            wordsForState,
        )
        statelessWordRepository.cleanQueue()
    }
}
