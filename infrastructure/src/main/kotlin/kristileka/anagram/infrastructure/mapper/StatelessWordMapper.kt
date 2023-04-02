package kristileka.anagram.infrastructure.mapper

import kristileka.anagram.domain.common.DomainMapper
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.infrastructure.entity.StatelessWordEntity
import org.springframework.stereotype.Service

@Service
class StatelessWordMapper : DomainMapper<StatelessWordEntity> {
    override fun fromDomain(word: Word): StatelessWordEntity {
        return StatelessWordEntity().apply {
            this.value = word.value
            this.predicate = word.predicate
        }
    }

    override fun toDomain(entity: StatelessWordEntity): Word? {
        return Word(
            entity.value!!,
            entity.predicate!!,
        )
    }
}
