package kristileka.anagram.domain.repository.db

import kristileka.anagram.domain.dto.Word

interface StatefulWordRepository {
    fun save(word: Word): Word?
    fun saveAll(words: List<Word>): List<Word>
    fun findWordByValue(value: String): Word?
    fun filterByPredicate(predicate: String): List<Word>
    fun findWordsByValueIn(values: List<String>): List<String>
}
