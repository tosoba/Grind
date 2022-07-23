package td.graph;

import lombok.AllArgsConstructor;

import java.util.*;

public class GraphIsConnected {

  @AllArgsConstructor
  // Definition for a Graph
  private static final class Graph {
    // List of lists to represent an adjacency list
    List<List<Integer>> adjList;

    // Total number of nodes in the graph
    int n;
  }

  public static boolean isConnected(Graph graph) {
    if (graph == null || graph.n == 0 || graph.adjList.isEmpty()) return false;

    var adj = new HashMap<Integer, List<Integer>>();
    graph.adjList.forEach(
        edge -> {
          var src = Math.min(edge.get(0), edge.get(1));
          var dest = Math.max(edge.get(0), edge.get(1));
          if (adj.containsKey(src)) adj.get(src).add(dest);
          else {
            var dests = new ArrayList<Integer>();
            dests.add(dest);
            adj.put(src, dests);
          }
        });

    var visited = new HashSet<Integer>();
    var nodes = new LinkedList<Integer>();
    nodes.add(adj.keySet().iterator().next());
    while (!nodes.isEmpty()) {
      var node = nodes.poll();
      if (visited.contains(node)) continue;

      visited.add(node);

      if (!adj.containsKey(node)) continue;

      adj.get(node)
          .forEach(
              next -> {
                if (visited.contains(next)) return;
                nodes.add(next);
              });
    }

    return visited.size() == graph.n;
  }

  public static void main(String[] args) {
    System.out.println(
        isConnected(
            new Graph(
                List.of(List.of(0, 1), List.of(1, 2), List.of(2, 3), List.of(3, 5), List.of(4, 1)),
                6)));
    System.out.println(
        isConnected(
            new Graph(
                List.of(
                    List.of(0, 1),
                    List.of(1, 2),
                    List.of(2, 3),
                    List.of(3, 5),
                    List.of(4, 6),
                    List.of(4, 8),
                    List.of(7, 8)),
                9)));
  }
}
