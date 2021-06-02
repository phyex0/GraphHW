//18050111043 Halit Burak Yeşildal

package com.company;

import java.io.File;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GFG.Graph graph = new GFG.Graph(82,82);
        HashMap<String,Integer> city = new HashMap<>();
        FileIO.matrixAdder(graph);
        FileIO.cityReader(city);

        printResult(graph,city);


    }

    public static void  printResult(GFG.Graph graph, HashMap<String, Integer> city){
        HashMap<Integer,String> reversedCity = reverseMap(city);
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Which city you want to check?");
            String cityName = scanner.nextLine();
            if(city.containsKey(cityName)){
                graph.BFS(city.get(cityName),reversedCity);
                break;
            }
            else
                System.out.println("Wrong input,Please try again!!");
        }


    }

    public static HashMap<Integer,String> reverseMap(HashMap<String,Integer> city){
        HashMap<Integer, String> reversedCity = new HashMap<>();
        for(String key : city.keySet())
            reversedCity.put(city.get(key),key);
        return reversedCity;
    }

}


class GFG {

    static class Graph {

        // Number of vertex
        int v;

        // Number of edges
        int e;

        // Adjacency matrix
        int[][] adj;

        // Function to fill the empty
        // adjacency matrix
        Graph(int v, int e) {
            this.v = v;
            this.e = e;

            adj = new int[v][v];
            for (int row = 0; row < v; row++)
                Arrays.fill(adj[row], 0);
        }

        // Function to add an edge to the graph
        void addEdge(int start, int e) {

            // Considering a bidirectional edge
            adj[start][e] = 1;
            adj[e][start] = 1;
        }

        // Function to perform BFS on the graph
        void BFS(int start,HashMap<Integer,String> city) {

            // Visited vector to so that
            // a vertex is not visited more than once
            // Initializing the vector to false as no
            // vertex is visited at the beginning
            boolean[] visited = new boolean[v];
            Arrays.fill(visited, false);
            List<Integer> q = new ArrayList<>();
            q.add(start);

            // Set source as visited
            visited[start] = true;

            int vis,size=0,count=0,level =0;
            while (!q.isEmpty()) {
                vis = q.get(0);

                //print the level
                if(count == size){
                    size =q.size();
                    System.out.println("Level: "+level++ );
                    count =0;
                }
                count++;

                // Print the current node
                //System.out.println("Size: "+q.size());
                System.out.println(city.get(vis));
                //System.out.println(vis + " ");
                q.remove(q.get(0));



                // For every adjacent vertex to
                // the current vertex
                for (int i = 0; i < v; i++) {

                    if (adj[vis][i] == 1 && (!visited[i])) {

                        // Push the adjacent node to
                        // the queue
                        q.add(i);

                        // Set
                        visited[i] = true;

                    }

                }


            }
        }
    }
}

class FileIO{

    //stores relationship of cities
    public static void matrixAdder(GFG.Graph graph){

        try {
            File f = new File("src/com/company/graph.txt");
            if(!f.exists())
                f.createNewFile();

            Scanner scanner = new Scanner(f);

            for(int i=0;i<81;i++){
                for(int j=0;j<81;j++){
                    if(scanner.nextInt() == 1)
                        graph.addEdge(i+1,j+1);
                }
            }



        }catch(Exception e){
            e.printStackTrace();
            System.out.println("File Error");
        }

    }

    //stores cities and plates.
    public static void cityReader(HashMap<String,Integer> hashMap){
        int i =1;
        try {
            File f = new File("src/com/company/cities.txt");
            if(!f.exists())
                f.createNewFile();

            Scanner scanner = new Scanner(f);
            while(scanner.hasNext())
                hashMap.put(scanner.nextLine(),i++);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("File Error");
        }

    }

}