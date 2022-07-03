package leetcode.rand_arithmetic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    if (strs.length == 0) return Collections.emptyList();
    if (strs.length == 1) return List.of(List.of(strs[0]));

    var strCounts = new HashMap<String, Counts>();
    for (var str : strs) {
      if (strCounts.containsKey(str)) {
        var c = strCounts.get(str);
        ++c.enc;
        continue;
      }
      var counts = new HashMap<Integer, Integer>();
      str.chars().forEach(c -> counts.merge(c, 1, Integer::sum));
      strCounts.put(str, new Counts(counts));
    }

    return strCounts.entrySet().stream()
        .collect(
            Collectors.groupingBy(
                Map.Entry::getValue,
                Collectors.mapping(
                    e ->
                        Stream.generate(e::getKey)
                            .limit(e.getValue().enc)
                            .collect(Collectors.toList()),
                    Collectors.toList())))
        .values()
        .stream()
        .map(l -> l.stream().flatMap(Collection::stream).collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  private static class Counts {
    Map<Integer, Integer> counts;
    int enc = 1;

    public Counts(Map<Integer, Integer> counts) {
      this.counts = counts;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Counts counts1 = (Counts) o;
      return Objects.equals(counts, counts1.counts);
    }

    @Override
    public int hashCode() {
      return Objects.hash(counts);
    }
  }

  public static void main(String[] args) {
    System.out.println(
        new GroupAnagrams().groupAnagrams(new String[] {"tea", "", "eat", "", "tea", ""}));
    System.out.println(new GroupAnagrams().groupAnagrams(new String[] {"", ""}));
    System.out.println(new GroupAnagrams().groupAnagrams(new String[] {"a"}));
    System.out.println(
        new GroupAnagrams().groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
  }
}
