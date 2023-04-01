package kristileka.anagram.infrastructure.repository.cache

import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import org.springframework.stereotype.Service

@Service
class StatelessWordRepositoryImpl : StatelessWordRepository {
    override fun putOnQueue(words: List<Word>) {
        TODO("Not yet implemented")
    }

    override fun getCurrentQueue(): List<Word> {
        TODO("Not yet implemented")
    }

    override fun cleanQueue(): Boolean {
        TODO("Not yet implemented")
    }
}