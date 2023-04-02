package kristileka.anagram.infrastructure.mapper

import kristileka.anagram.domain.common.DomainMapper
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.infrastructure.entity.StatefulWordEntity
import org.springframework.stereotype.Service

@Service()
class StatefulWordMapper : DomainMapper<StatefulWordEntity> {
    override fun fromDomain(word: Word): StatefulWordEntity {
        return StatefulWordEntity().apply {
            this.value = word.value
            this.predicate = word.predicate
            this.letterCount = word.value.length
        }
    }

    override fun toDomain(entity: StatefulWordEntity): Word? {
        return Word(
            entity.value!!,
            entity.predicate!!,
        )
    }
}
