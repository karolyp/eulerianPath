package com.kpakozdi;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Kérem adjon meg egy file-t parancssori paraméterként, ami a szomszédsági mátrixot tartalmazza!");
            System.exit(1);
        }
        File f = new File(args[0]);
        try {
            int[][] G = GraphUtil.readAdjacencyMatrixFromFile(f);
            EulerTrailGraph eulerTrailGraph = new EulerTrailGraph(G);
            eulerTrailGraph.findEulerTrail();
        } catch (Exception e) {
            System.err.println("Hiba a végrehajtás során.");
            System.err.println(e.getMessage());
        }
    }
}
