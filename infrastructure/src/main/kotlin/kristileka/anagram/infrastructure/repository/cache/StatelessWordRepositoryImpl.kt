package kristileka.anagram.infrastructure.repository.cache

import kristileka.anagram.domain.common.DomainMapper
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import kristileka.anagram.infrastructure.entity.StatelessWordEntity
import org.springframework.stereotype.Service

@Service
class StatelessWordRepositoryImpl(
    val redisRepository: RedisRepository,
    val domainMapper: DomainMapper<StatelessWordEntity>,
) : StatelessWordRepository {
    override fun putOnQueue(words: List<Word>) {
        redisRepository.saveAll(
            words.map {
                domainMapper.fromDomain(it)
            },
        )
    }

    override fun getCurrentQueue(): List<Word> {
        return redisRepository.findAll().mapNotNull {
            domainMapper.toDomain(it)
        }
    }

    override fun cleanQueue() {
        return redisRepository.deleteAll()
    }
}
