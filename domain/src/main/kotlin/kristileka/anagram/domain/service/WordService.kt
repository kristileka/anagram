package kristileka.anagram.domain.service

import kristileka.anagram.domain.dto.EvaluationResult
import kristileka.anagram.domain.dto.Word

interface WordService {
    fun evaluateAnagram(vararg values: String): EvaluationResult
    fun evaluateAnagram(words: List<String>, takeFirst: Boolean = false): Pair<List<EvaluationResult>, String>
    fun insertWord(value: String): Word
    fun uprateWord(value: String): Word
    fun downRateWord(value: String): Word
    fun searchForAnagram(value: String): List<Word>
}