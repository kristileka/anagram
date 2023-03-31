package kristileka.anagram.domain.service

import kristileka.anagram.domain.dto.EvaluationResult
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.extensions.Extensions.getPredicate
import kristileka.anagram.domain.repository.WordRepository

class DomainWordService(
    var wordRepository: WordRepository
) : WordService {
    override fun evaluateAnagram(vararg values: String): EvaluationResult {
        return EvaluationResult(values[0], values[1], values.map {
            it.getPredicate()
        }.distinct().size == 1)
    }

    override fun evaluateAnagram(words: List<String>, takeFirst: Boolean): Pair<List<EvaluationResult>, String> {
        return evaluateAnagramResulted(words = words)
    }

    fun evaluateAnagramResulted(
        words: List<String>,
    ): Pair<List<EvaluationResult>, String> {
        val wordsToPredicate = words.map { it to it.getPredicate() }
        val atMostPredicate = wordsToPredicate.groupingBy { it.second }.eachCount()
            .maxByOrNull { it.value }?.key
        val selectedAnagram = wordsToPredicate.first { it.second == atMostPredicate }.first
        return Pair(wordsToPredicate.map {
            EvaluationResult(
                it.first,
                selectedAnagram,
                it.second == atMostPredicate
            )
        }, selectedAnagram)
    }

    override fun insertWord(value: String): Word {
        TODO("Not yet implemented")
    }

    override fun uprateWord(value: String): Word {
        TODO("Not yet implemented")
    }

    override fun downRateWord(value: String): Word {
        TODO("Not yet implemented")
    }

    override fun searchForAnagram(value: String): List<Word> {
        TODO("Not yet implemented")
    }
}