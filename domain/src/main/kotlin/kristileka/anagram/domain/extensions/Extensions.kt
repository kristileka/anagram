package kristileka.anagram.domain.extensions

object Extensions {
    fun String.getPredicate(): String {
        return this.map(Char::lowercase).sorted().joinToString("")
    }
}
