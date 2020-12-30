package com.kpakozdi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class GraphUtil {
    public static List<Integer> getOddDegreeVertices(int[][] G) {
        List<Integer> oddVertices = new ArrayList<>();
        int N = G.length;
        for (int v = 0; v < N; v++) {
            int edgeOut = 0;
            for (int j = 0; j < N; j++) {
                edgeOut += G[v][j];
            }
            if (edgeOut % 2 == 1) {
                oddVertices.add(v);
            }
        }
        return oddVertices;
    }

    public static boolean isBridge(int[][] G, int u, int v) {
        G[u][v] = G[v][u] = 0;
        boolean isBridge = !isReachable(G, u, v);
        G[u][v] = G[v][u] = 1;
        return isBridge;
    }

    public static int getNumberOfReachableNodes(int[][] G, int source) {
        boolean[] visited = new boolean[G.length];
        int visitedNodes = 0;
        Stack<Integer> S = new Stack<>();
        S.push(source);
        while (!S.empty()) {
            Integer v = S.pop();
            if (!visited[v]) {
                visited[v] = true;
                visitedNodes++;
                for (int w = 0; w < G.length; w++) {
                    if (G[v][w] > 0) {
                        S.push(w);
                    }
                }
            }
        }
        return visitedNodes;
    }

    public static boolean isReachable(int[][] G, int source, int target) {
        boolean[] visited = new boolean[G.length];
        Stack<Integer> S = new Stack<>();
        S.push(source);
        while (!S.empty()) {
            Integer v = S.pop();
            if (!visited[v]) {
                visited[v] = true;
                if (v == target) {
                    return true;
                }
                for (int w = 0; w < G.length; w++) {
                    if (G[v][w] > 0) {
                        S.push(w);
                    }
                }
            }
        }
        return false;
    }

    public static int getNumberOfEdges(int[][] G) {
        int numberOfVertices = 0;
        for (int j = 0; j < G.length; j++) {
            for (int i = 0; i <= j; i++) {
                numberOfVertices += G[i][j];
            }
        }
        return numberOfVertices;
    }

    public static boolean isConnected(int[][] G) {
        int N = G.length;
        for (int v = 0; v < N; v++) {
            if (getNumberOfReachableNodes(G, v) != N) {
                return false;
            }
        }
        return true;
    }

    public static int[][] readAdjacencyMatrixFromFile(File f) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        List<String> lines = br.lines().collect(Collectors.toList());
        int[][] adjacencyMatrix = new int[lines.size()][lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rowItems = line.split(" ");
            for (int j = 0; j < rowItems.length; j++) {
                String item = rowItems[j];
                adjacencyMatrix[i][j] = Integer.parseInt(item);
            }
        }
        return adjacencyMatrix;
    }

}
