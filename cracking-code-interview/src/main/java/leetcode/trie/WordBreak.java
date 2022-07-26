package leetcode.trie;

import java.util.*;

public class WordBreak {
  public boolean wordBreak(String s, List<String> wordDict) {
    var trie = new HashMap<Character, TrieNode>();
    wordDict.forEach(
        word -> {
          var firstLetter = word.charAt(0);
          if (trie.containsKey(firstLetter)) {
            if (word.length() > 1) {
              trie.get(firstLetter).insert(word, 1);
            } else {
              trie.get(firstLetter).isLast = true;
            }
          } else {
            var node = new TrieNode(firstLetter, new HashMap<>(), word.length() == 1);
            trie.put(firstLetter, node);
            if (word.length() > 1) {
              node.insert(word, 1);
            }
          }
        });

    return wordBreak(s, trie, 0, new HashMap<>());
  }

  private boolean wordBreak(
      String s, Map<Character, TrieNode> trie, int startIndex, Map<Integer, Boolean> results) {
    TrieNode node = null;
    var nextIndices = new HashSet<Integer>();
    for (var i = startIndex; i < s.length(); ++i) {
      var nextLetter = s.charAt(i);
      if (node == null) {
        node = trie.get(nextLetter);
        if (node == null) break;
      } else if (node.children.containsKey(nextLetter)) {
        node = node.children.get(nextLetter);
      } else {
        break;
      }

      if (node.isLast) {
        nextIndices.add(i + 1);
      }
    }

    if (nextIndices.isEmpty()) {
      results.put(startIndex, false);
      return false;
    } else if (nextIndices.contains(s.length())) {
      results.put(startIndex, true);
      return true;
    } else if (results.keySet().containsAll(nextIndices)) {
      var res =
          results.entrySet().stream()
              .filter(result -> nextIndices.contains(result.getKey()))
              .anyMatch(Map.Entry::getValue);
      results.put(startIndex, res);
      return res;
    }

    return nextIndices.stream()
        .anyMatch(i -> results.containsKey(i) ? results.get(i) : wordBreak(s, trie, i, results));
  }

  private static final class TrieNode {
    char letter;
    HashMap<Character, TrieNode> children;
    boolean isLast;

    public void insert(String word, int startIndex) {
      if (startIndex >= word.length()) return;

      var letter = word.charAt(startIndex);
      if (startIndex == word.length() - 1) {
        if (children.containsKey(letter)) {
          children.get(letter).isLast = true;
        } else {
          children.put(letter, new TrieNode(letter, new HashMap<>(), true));
        }
      } else {
        if (children.containsKey(letter)) {
          children.get(letter).insert(word, startIndex + 1);
        } else {
          var node = new TrieNode(letter, new HashMap<>(), false);
          children.put(letter, node);
          node.insert(word, startIndex + 1);
        }
      }
    }

    public TrieNode(char letter, HashMap<Character, TrieNode> children, boolean isLast) {
      this.letter = letter;
      this.children = children;
      this.isLast = isLast;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      TrieNode trieNode = (TrieNode) o;
      return letter == trieNode.letter
          && isLast == trieNode.isLast
          && Objects.equals(children, trieNode.children);
    }

    @Override
    public int hashCode() {
      return Objects.hash(letter, children, isLast);
    }
  }

  public static void main(String[] args) {
    System.out.println(new WordBreak().wordBreak("leetcode", List.of("leet", "code")));
    System.out.println(new WordBreak().wordBreak("applepenapple", List.of("apple", "pen")));
    System.out.println(
        new WordBreak().wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    System.out.println(
        new WordBreak()
            .wordBreak(
                "acaaaaabbbdbcccdcdaadcdccacbcccabbbbcdaaaaaadb",
                List.of(
                    "abbcbda", "cbdaaa", "b", "dadaaad", "dccbbbc", "dccadd", "ccbdbc", "bbca",
                    "bacbcdd", "a", "bacb", "cbc", "adc", "c", "cbdbcad", "cdbab", "db", "abbcdbd",
                    "bcb", "bbdab", "aa", "bcadb", "bacbcb", "ca", "dbdabdb", "ccd", "acbb", "bdc",
                    "acbccd", "d", "cccdcda", "dcbd", "cbccacd", "ac", "cca", "aaddc", "dccac",
                    "ccdc", "bbbbcda", "ba", "adbcadb", "dca", "abd", "bdbb", "ddadbad", "badb",
                    "ab", "aaaaa", "acba", "abbb")));
    System.out.println(
        new WordBreak()
            .wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                List.of(
                    "a",
                    "aa",
                    "aaa",
                    "aaaa",
                    "aaaaa",
                    "aaaaaa",
                    "aaaaaaa",
                    "aaaaaaaa",
                    "aaaaaaaaa",
                    "aaaaaaaaaa")));
  }
}
