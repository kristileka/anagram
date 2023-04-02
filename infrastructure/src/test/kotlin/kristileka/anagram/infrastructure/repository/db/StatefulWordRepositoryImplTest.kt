package kristileka.anagram.infrastructure.repository.db

import io.mockk.every
import io.mockk.mockk
import kristileka.anagram.domain.common.DomainMapper
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import kristileka.anagram.infrastructure.entity.StatefulWordEntity
import kristileka.anagram.infrastructure.mapper.StatefulWordMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.data.repository.findByIdOrNull
import java.rmi.AlreadyBoundException
import java.util.*


class StatefulWordRepositoryImplTest {


    @Mock
    private val postgresRepository: PostgresRepository = mockk()

    @Mock

    private val domainMapper: DomainMapper<StatefulWordEntity> = mockk()
    private lateinit var statefulWordRepository: StatefulWordRepository

    @BeforeEach
    fun init() {
        statefulWordRepository = StatefulWordRepositoryImpl(postgresRepository, domainMapper)
    }

    @Test
    fun `save returns null when mapper fails`() {
        val word = Word("sad", "asd")
        val statefulWordEntity = StatefulWordEntity()
        every {
            postgresRepository.save(any())
        } returns statefulWordEntity

        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity
        every {
            domainMapper.toDomain(any())
        } returns null
        val result = statefulWordRepository.save(word)
        assert(result == null)
    }

    @Test
    fun `save is successful when postgresRepository and domianMapper work`() {
        val word = Word("sad", "asd")
        val statefulWordEntity = StatefulWordEntity()
        every {
            postgresRepository.save(any())
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity
        every {
            domainMapper.toDomain(any())
        } returns word
        val result = statefulWordRepository.save(word)
        assert(result == word)
    }

    @Test
    fun `save all will return the list of stored elements, if they fail they arent returned`() {
        val words = listOf(Word("sad", "asd"))
        val statefulWordEntity = listOf(StatefulWordEntity())
        every {
            postgresRepository.saveAll(any<List<StatefulWordEntity>>())
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity.first()
        every {
            domainMapper.toDomain(any())
        } returns null
        val result = statefulWordRepository.saveAll(words)
        assert(result.isEmpty())
    }

    @Test
    fun `save all will return the list of stored elements, if some succeed  some will return`() {
        val words = listOf(Word("sad", "asd"), Word("sad", "asd"))
        val statefulWordEntity = listOf(StatefulWordEntity())
        every {
            postgresRepository.saveAll(any<List<StatefulWordEntity>>())
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity.first()
        every {
            domainMapper.toDomain(any())
        } returns words.first()
        val result = statefulWordRepository.saveAll(words)
        assert(result.isNotEmpty())
        assert(result.size == 1)
    }

    @Test
    fun `save all will return the list of stored elements, if they succeed all they all are returned`() {
        val words = listOf(Word("sad", "asd"), Word("sad", "asd"))
        val statefulWordEntity = listOf(StatefulWordEntity(), StatefulWordEntity())
        every {
            postgresRepository.saveAll(any<List<StatefulWordEntity>>())
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity.first()
        every {
            domainMapper.toDomain(any())
        } returns words.first()
        val result = statefulWordRepository.saveAll(words)
        assert(result.isNotEmpty())
        assert(result.size == statefulWordEntity.size)
    }


    @Test
    fun `find word by value fails`() {
        val statefulWordEntity = StatefulWordEntity()
        every {
            postgresRepository.findByValue(any())
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity
        every {
            domainMapper.toDomain(any())
        } returns null
        val result = statefulWordRepository.findWordByValue("asd")
        assert(result == null)
    }

    @Test
    fun `find word by value succeeds`() {
        val word = Word("asd", "asd")
        val statefulWordEntity = StatefulWordEntity()
        every {
            postgresRepository.findByValue("asd")
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity
        every {
            domainMapper.toDomain(any())
        } returns word
        val result = statefulWordRepository.findWordByValue("asd")
        assert(result == word)
    }


    @Test
    fun filterByPredicate() {
        val words = listOf(Word("asd", "asd"))
        val statefulWordEntity = listOf(StatefulWordEntity())
        every {
            postgresRepository.findAllByPredicate("asd")
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity.first()
        every {
            domainMapper.toDomain(any())
        } returns words.first()
        val result = statefulWordRepository.filterByPredicate("asd")
        assert(result.isNotEmpty())
        assert(result.size == statefulWordEntity.size)
    }

    @Test
    fun `find words will not returns the null values`() {
        val words = listOf("test", "test1")
        val statefulWordEntity = listOf(StatefulWordEntity())
        every {
            postgresRepository.findAllByValueIn(any())
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity.first()
        every {
            domainMapper.toDomain(any())
        } returns Word("test", "test")
        val result = statefulWordRepository.findWordsByValueIn(words)
        assert(result.isEmpty())
    }

    @Test
    fun `find words will return only the real statefulWords`() {
        val words = listOf("test", "test1")
        val statefulWordEntity = listOf(StatefulWordEntity().apply {
            this.value = "asd"
        })
        every {
            postgresRepository.findAllByValueIn(any())
        } returns statefulWordEntity
        every {
            domainMapper.fromDomain(any())
        } returns statefulWordEntity.first()
        every {
            domainMapper.toDomain(any())
        } returns Word("test", "test")
        val result = statefulWordRepository.findWordsByValueIn(words)
        assert(result.isNotEmpty())
        assert(result.size == statefulWordEntity.size)
    }
}