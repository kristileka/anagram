package kristileka.anagram.infrastructure.repository

import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.WordRepository
import org.springframework.stereotype.Service

@Service
class PostgresWordRepository : WordRepository {
    override fun save(word: Word): Word {
        TODO("Not yet implemented")
    }

    override fun findWordById(id: Long): Word {
        TODO("Not yet implemented")
    }

    override fun findWordByValue(value: String): Word {
        TODO("Not yet implemented")
    }

    override fun filterByPredicate(predicate: String): List<Word> {
        TODO("Not yet implemented")
    }

    override fun upRateWord(word: String): Word {
        TODO("Not yet implemented")
    }

    override fun downRateWord(word: String): String {
        TODO("Not yet implemented")
    }
}