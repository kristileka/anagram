package kristileka.anagram.domain.common

import kristileka.anagram.domain.dto.Word

interface DomainMapper<T> {
    fun fromDomain(word: Word): T
    fun toDomain(entity: T): Word?
}
