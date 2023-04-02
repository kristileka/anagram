package kristileka.anagram.infrastructure.mapper

import kristileka.anagram.domain.dto.Word
import kristileka.anagram.infrastructure.entity.StatefulWordEntity
import org.junit.jupiter.api.Test

class StatefulWordMapperTest {

    private val domainMapper = StatefulWordMapper()

    @Test
    fun `test from domain function of stateful word`() {
        val word = Word("test1", "test")
        val result = domainMapper.fromDomain(word)
        assert(
            result.value == word.value,
        )
        assert(
            result.predicate == word.predicate,
        )
    }

    @Test
    fun `test to domain function of stateful word`() {
        val statefulWord = StatefulWordEntity().apply {
            this.value = "test1"
            this.predicate = "test"
        }
        val result = domainMapper.toDomain(statefulWord)
        assert(
            result?.value == statefulWord.value,
        )
        assert(
            result?.predicate == statefulWord.predicate,
        )
    }
}
