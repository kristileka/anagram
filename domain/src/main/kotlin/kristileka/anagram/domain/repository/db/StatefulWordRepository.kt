package kristileka.anagram.domain.repository.db

import kristileka.anagram.domain.dto.Word

interface StatefulWordRepository {
    fun save(word: Word): Boolean
    fun saveAll(words: List<Word>): List<Word>
    fun findWordById(id: Long): Word?
    fun findWordByValue(value: String): Word?
    fun filterByPredicate(predicate: String): List<Word>
}