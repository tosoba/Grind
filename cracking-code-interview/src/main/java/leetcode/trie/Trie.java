package leetcode.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Trie {
  private final Map<Character, TrieNode> trie = new HashMap<>();

  public Trie() {}

  public void insert(String word) {
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
  }

  public boolean search(String word) {
    var firstLetter = word.charAt(0);
    if (!trie.containsKey(firstLetter)) return false;

    var node = trie.get(firstLetter);
    return node.search(word, 1, false);
  }

  public boolean startsWith(String prefix) {
    var firstLetter = prefix.charAt(0);
    if (!trie.containsKey(firstLetter)) return false;

    var node = trie.get(firstLetter);
    return node.search(prefix, 1, true);
  }

  private static final class TrieNode {
    char letter;
    HashMap<Character, TrieNode> children;
    boolean isLast;

    public boolean search(String word, int startIndex, boolean prefixOnly) {
      if (startIndex >= word.length()) return prefixOnly || isLast;
      var letter = word.charAt(startIndex);
      if (!children.containsKey(letter)) return false;
      return children.get(letter).search(word, startIndex + 1, prefixOnly);
    }

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
}
