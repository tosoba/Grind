package algo.dp

private fun minDistance(word1: String, word2: String): Int {
    val longestSubsequence = longestCommonSubsequence(word1, word2)
    return (word1.length - longestSubsequence) + (word2.length - longestSubsequence)
}


fun main() {

}