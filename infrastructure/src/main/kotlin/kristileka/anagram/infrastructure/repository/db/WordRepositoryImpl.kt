package kristileka.anagram.infrastructure.repository.db

import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.db.WordRepository
import kristileka.anagram.infrastructure.entity.WordEntity.Companion.fromDomain
import kristileka.anagram.infrastructure.entity.WordEntity.Companion.toDomain
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class WordRepositoryImpl(
    var postgresWordRepository: PostgresWordRepository
) : WordRepository {
    override fun save(word: Word): Boolean {
        postgresWordRepository.save(
            word.fromDomain()
        )
        return true
    }

    override fun saveAll(words: List<Word>): List<Word> {
        val wordsEntity = words.map { it.fromDomain() }
        return postgresWordRepository.saveAll(wordsEntity).map {
            it.toDomain()
        }
    }

    override fun findWordById(id: Long): Word? {
        return postgresWordRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findWordByValue(value: String): Word? {
        return postgresWordRepository.findByValue(value)?.toDomain()
    }

    override fun filterByPredicate(predicate: String): List<Word> {
        return postgresWordRepository.findByPredicate(predicate).map {
            it.toDomain()
        }
    }

}