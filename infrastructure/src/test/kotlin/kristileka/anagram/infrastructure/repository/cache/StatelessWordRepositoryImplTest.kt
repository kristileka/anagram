package kristileka.anagram.infrastructure.repository.cache

import io.mockk.every
import io.mockk.mockk
import kristileka.anagram.domain.common.DomainMapper
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import kristileka.anagram.infrastructure.entity.StatefulWordEntity
import kristileka.anagram.infrastructure.entity.StatelessWordEntity
import kristileka.anagram.infrastructure.repository.db.PostgresRepository
import kristileka.anagram.infrastructure.repository.db.StatefulWordRepositoryImpl
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock

class StatelessWordRepositoryImplTest {

    @Mock
    private val redisRepository: RedisRepository = mockk()

    @Mock

    private val domainMapper: DomainMapper<StatelessWordEntity> = mockk()
    private lateinit var statelessWordRepository: StatelessWordRepositoryImpl

    @BeforeEach
    fun init() {
        statelessWordRepository = StatelessWordRepositoryImpl(redisRepository, domainMapper)
    }

    @Test
    fun `put list of elements on queue doesnt throw`() {
        val words = listOf(Word("sad", "asd"))
        val statelessWordsEntity = listOf(StatelessWordEntity())
        every {
            redisRepository.saveAll(any<List<StatelessWordEntity>>())
        } returns statelessWordsEntity

        every {
            domainMapper.fromDomain(any())
        } returns statelessWordsEntity.first()

        assertDoesNotThrow {
            statelessWordRepository.putOnQueue(words)
        }
    }

    @Test
    fun `get current queue from the list`() {
        val words = listOf(Word("sad", "asd"))
        val statelessWordsEntity = listOf(StatelessWordEntity())
        every {
            redisRepository.findAll()
        } returns statelessWordsEntity

        every {
            domainMapper.toDomain(any())
        } returns words.first()

        val result = statelessWordRepository.getCurrentQueue()
        assert(result.isNotEmpty())
        assert(result.size == statelessWordsEntity.size)
    }

    @Test
    fun `assert that clean queue is unit that doesnt throw`() {
        every {
            redisRepository.deleteAll()
        } returns Unit
        assertDoesNotThrow {
            statelessWordRepository.cleanQueue()
        }
    }
}