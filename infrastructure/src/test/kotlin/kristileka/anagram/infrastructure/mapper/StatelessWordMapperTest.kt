package kristileka.anagram.infrastructure.mapper

import kristileka.anagram.domain.dto.Word
import kristileka.anagram.infrastructure.entity.StatelessWordEntity
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StatelessWordMapperTest {

    private val domainMapper = StatelessWordMapper()

    @Test
    fun fromDomain() {
        val word = Word("test1", "test")
        val result = domainMapper.fromDomain(word)
        assert(
            result.value == word.value
        )
        assert(
            result.predicate == word.predicate
        )

    }

    @Test
    fun toDomain() {
        val statelessWord = StatelessWordEntity().apply {
            this.value = "test1"
            this.predicate = "test"
        }
        val result = domainMapper.toDomain(statelessWord)
        assert(
            result?.value == statelessWord.value
        )
        assert(
            result?.predicate == statelessWord.predicate
        )
    }
}