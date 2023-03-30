package kristileka.anagram.domain.service

import kristileka.anagram.domain.dto.Word

interface WordService {
    fun evaluateAnagram(value1: String, value2: String): Boolean
    fun insertWord(value: String): Word
    fun uprateWord(value: String): Word
    fun downRateWord(value: String): Word
    fun searchForAnagram(value: String): List<Word>
}