
package com.mycompany.presupuestoferias.ejercicios;


import java.util.*;

public class Grafos {
    private int V;
    private LinkedList<Integer>[] adjList;

    public Grafos() {
    }

    public void initialize(int vertices, int edges, int startVertex, int[][] edgesArray) {
        V = vertices;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < edges; i++) {
            int source = edgesArray[i][0];
            int destination = edgesArray[i][1];
            addEdge(source, destination);
        }

        bfs(startVertex);
        dfs(startVertex);
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }

    public void bfs(int startVertex) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.print("Recorrido BFS: ");
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (int neighbor : adjList[currentVertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
        mostrarRepresentacionMatriz();
        mostrarListaAdyacencia();
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[V];
        System.out.print("Recorrido DFS: ");
        dfsUtil(startVertex, visited);
        System.out.println();
    }

    private void dfsUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbor : adjList[vertex]) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    private void mostrarRepresentacionMatriz() {
        System.out.println("\nRepresentación del grafo en forma de matriz:");

        int[][] matriz = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adjList[i]) {
                matriz[i][neighbor] = 1;
                matriz[neighbor][i] = 1;
            }
        }

        System.out.println("+--------+---------------------------------------------+");
        System.out.print("| Vértice |");
        for (int i = 0; i < V; i++) {
            System.out.print("   " + i + "   ");
        }
        System.out.println(" |");
        System.out.println("+--------+---------------------------------------------+");
        for (int i = 0; i < V; i++) {
            System.out.print("|   " + i + "     |");
            for (int j = 0; j < V; j++) {
                System.out.print("   " + matriz[i][j] + "   ");
            }
            System.out.println("  |");
        }
        System.out.println("+--------+---------------------------------------------+");
    }

    private void mostrarListaAdyacencia() {
        System.out.println("\nRepresentación del grafo en forma de lista de adyacencia:");

        for (int i = 0; i < V; i++) {
            System.out.print(i + "-> ");
            for (int neighbor : adjList[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}



