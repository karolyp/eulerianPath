package com.kpakozdi;

import java.util.List;

public class EulerTrailGraph {
    private final int[][] G;

    public EulerTrailGraph(int[][] G) {
        validateGraph(G);
        this.G = G;
    }

    private void validateGraph(int[][] G) {
        if (!GraphUtil.isConnected(G)) {
            throw new IllegalArgumentException("A gráf nem összefüggő.");
        }
        int oddVertices = GraphUtil.getOddDegreeVertices(G).size();
        if (oddVertices != 0 && oddVertices != 2) {
            throw new IllegalArgumentException("Nincs sem nyitott, sem zárt Euler-vonal a gráfban.");
        }
    }

    public void findEulerTrail() {
        List<Integer> oddDegreeVertices = GraphUtil.getOddDegreeVertices(G);
        int u;

        if (oddDegreeVertices.size() == 0) {
            System.out.println("A gráfban van zárt Euler-vonal.");
            u = 0;
        } else {
            System.out.println("A gráfban van nyitott Euler-vonal.");
            u = oddDegreeVertices.get(0);
        }

        int vertices;
        while ((vertices = GraphUtil.getNumberOfEdges(G)) > 0) {
            int nextVertex = getNextVertex(G, u);
            System.out.print(u + "" + nextVertex + (vertices > 1 ? " -> " : System.lineSeparator()));
            G[u][nextVertex] = G[nextVertex][u] = 0;
            u = nextVertex;
        }
    }

    int getNextVertex(int[][] G, int u) {
        int firstBridge = -1;
        for (int v = 0; v < G.length; v++) {
            if (G[u][v] > 0) {
                // ha találtunk egy nem-híd élt, térjünk vissza a másik csúcsával
                if (!GraphUtil.isBridge(G, u, v)) {
                    return v;
                }
                // egyébként ha hidat találunk, mentsük el a másik csúcsot
                if (firstBridge == -1) {
                    firstBridge = v;
                }
            }
        }
        // ez itt egy híd lesz
        return firstBridge;
    }
}
