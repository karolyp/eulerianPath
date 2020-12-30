# eulerianTrail

This program aims to find Eulerian trails and cycles in a connected graph. The underlying algorithm is the
so-called [Fleury's algorithm](https://en.wikipedia.org/wiki/Eulerian_path#Fleury's_algorithm).

## The algorithm

As the algorithm states, Eulerian trail can only exists if and only if the number of odd degrees in the graph is **0**
or **2**.

1. Validate if Eulerian trail can exist
1. If so, choose a starting point as follows:
    1. If the number of odd vertices is 2, pick one of them (here the first odd one)
    1. If there are no odd vertices pick any arbitrary vertex (here the first one)
1. *u = starting point*
1. While there are edges in the graph, do:
    1. Find edges going out from *u*
        1. Always go for non-[bridge](https://en.wikipedia.org/wiki/Bridge_(graph_theory)) edges
        1. If there are no non-bridge edges left, choose a bridge
    1. Store the other vertex of the edge (*nextVertex*)
    1. Remove the edge from graph
    1. *nextVertex = u*

## Usage

You can pass a command line argument pointing to an adjacency matrix that contains the graph.

## Disclaimer
This source code is only for demonstration purposes for a university class without any performance considerations or optimizations. 