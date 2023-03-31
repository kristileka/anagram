package kristileka.anagram.domain.extensions

object Extensions {
    fun String.getPredicate(): String {
        return this.map { it.lowercase() }.sorted().joinToString("")
    }
}