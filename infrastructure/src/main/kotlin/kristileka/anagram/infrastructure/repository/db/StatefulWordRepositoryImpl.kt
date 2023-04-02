package kristileka.anagram.infrastructure.repository.db

import kristileka.anagram.domain.common.DomainMapper
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import kristileka.anagram.infrastructure.entity.StatefulWordEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StatefulWordRepositoryImpl(
    val postgresRepository: PostgresRepository,
    val domainMapper: DomainMapper<StatefulWordEntity>,
) : StatefulWordRepository {
    override fun save(word: Word): Word? {
        return domainMapper.toDomain(
            postgresRepository.save(
                domainMapper.fromDomain(word),
            )
        )
    }

    override fun saveAll(words: List<Word>): List<Word> {
        val wordsEntity = words.map { domainMapper.fromDomain(it) }
        return postgresRepository.saveAll(wordsEntity).mapNotNull {
            domainMapper.toDomain(it)
        }
    }


    override fun findWordByValue(value: String): Word? {
        val word = postgresRepository.findByValue(value) ?: return null
        return domainMapper.toDomain(word)
    }

    override fun filterByPredicate(predicate: String): List<Word> {
        return postgresRepository.findAllByPredicate(predicate).mapNotNull {
            domainMapper.toDomain(it)
        }
    }

    override fun findWordsByValueIn(values: List<String>): List<String> {
        return postgresRepository.findAllByValueIn(values).mapNotNull {
            it.value
        }
    }
}
