package kristileka.anagram.application.models.response

data class AnagramSearchOperationResponseREST(
    val storedAnagrams: List<String>,
    val word: String,
)
