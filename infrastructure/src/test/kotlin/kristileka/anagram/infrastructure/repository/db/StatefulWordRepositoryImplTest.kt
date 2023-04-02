package kristileka.anagram.infrastructure.repository.db

import kristileka.anagram.domain.common.DomainMapper
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import kristileka.anagram.infrastructure.entity.StatefulWordEntity
import kristileka.anagram.infrastructure.mapper.StatefulWordMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito


class StatefulWordRepositoryImplTest {


    @Mock
    private val postgresRepository: PostgresRepository = Mockito.mock(PostgresRepository::class.java)

    @Mock

    private val domainMapper: DomainMapper<StatefulWordEntity> = Mockito.mock(StatefulWordMapper::class.java)
    private lateinit var statefulWordRepository: StatefulWordRepository

    @BeforeEach
    fun init() {
        statefulWordRepository = StatefulWordRepositoryImpl(postgresRepository, domainMapper)
    }

    @Test
    fun save() {
    }

    @Test
    fun saveAll() {
    }

    @Test
    fun findWordById() {
    }

    @Test
    fun findWordByValue() {
    }

    @Test
    fun filterByPredicate() {
    }

    @Test
    fun findWordsByValueIn() {
    }
}