package kristileka.anagram.infrastructure.repository.db

import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import kristileka.anagram.infrastructure.entity.StatefulWordEntity.Companion.fromDomain
import kristileka.anagram.infrastructure.entity.StatefulWordEntity.Companion.toDomain
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StatefulWordRepositoryImpl(
    var postgresRepository: PostgresRepository
) : StatefulWordRepository {
    override fun save(word: Word): Boolean {
        postgresRepository.save(
            word.fromDomain()
        )
        return true
    }

    override fun saveAll(words: List<Word>): List<Word> {
        val wordsEntity = words.map { it.fromDomain() }
        return postgresRepository.saveAll(wordsEntity).map {
            it.toDomain()
        }
    }

    override fun findWordById(id: Long): Word? {
        return postgresRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findWordByValue(value: String): Word? {
        return postgresRepository.findByValue(value)?.toDomain()
    }

    override fun filterByPredicate(predicate: String): List<Word> {
        return postgresRepository.findByPredicate(predicate).map {
            it.toDomain()
        }
    }

}