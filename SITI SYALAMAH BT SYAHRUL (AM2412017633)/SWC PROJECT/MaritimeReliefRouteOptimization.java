
/**
 *
 * Name:SITI SYALAMAH BT SYAHRUL (AM2112017633)
 * BACKTRAKING
 */

import java.util.*;

public class MaritimeReliefRouteOptimization{
    
        // Distance Matrix (Adjacency Matrix)
    static int[][] distanceMatrix = {
        {0, 15, 25, 35},
        {15, 0, 30, 28},
        {25, 30, 0, 20},
        {35, 28, 20, 0}
        };
    
    // Location names
    static String[] locations = {"Port A", "Port B", "Relief Center C", "Relief Center D"};
    
    private static int minCost = Integer.MAX_VALUE;
    
    private static String optimalPathIndices = "";
    
    public static String backtrackingTSP(int[][] dist) {
        
        minCost = Integer.MAX_VALUE;
        optimalPathIndices = "";
        
        int n = dist.length;
        boolean[] visited = new boolean[n];
        
        StringBuilder currentPath = new StringBuilder("0");
        
        visited[0] = true;
        
        tspBacktracking (0, dist, visited, n, 1, 0, currentPath);
        
        if (!optimalPathIndices.isEmpty()) {
            String[] indices = optimalPathIndices.split(" -> ");
            StringBuilder finalPath = new StringBuilder();
            
            for (String indexStr : indices) {
                int index = Integer.parseInt(indexStr.trim());
                if (finalPath.length() > 0) finalPath.append(" -> ");
                finalPath.append(locations[index]);
            }
            
            finalPath.append(" -> ").append(locations[0]);
             
            double minCostNm = minCost;
            int roundedNm = (int) Math.round(minCostNm);
            return String.format("Total Distance: %d nm\nRoute: %s", roundedNm, finalPath.toString());
            
            
        } else {
             return "Error: No complete route found.";
        }
        
    }// end of backtrackingTSP
    
    private static int tspBacktracking(int pos, int[][] dist, boolean[] visited,int n, int count, int cost, StringBuilder path){
        // Base Case: All cities visited
        if (count == n) {
             if (dist[pos][0] != 0) {
                 int totalCost = cost + dist[pos][0];
                 
                 if (totalCost < minCost) {
                     
                    minCost = totalCost;
                    optimalPathIndices = path.toString();
                 }
                 return totalCost;
             }
             return Integer.MAX_VALUE;
        }
        // Recursive Step
        for (int nextCity = 0; nextCity < n; nextCity++) {
            if (!visited[nextCity] && dist[pos][nextCity] != 0) {
                
                int newCost = cost + dist[pos][nextCity];
                // Pruning check
                if (newCost < minCost) {
                    
                    // --- Forward Step ---
                    visited[nextCity] = true; 
                    int originalPathLength = path.length();
                    path.append(" -> ").append(nextCity);
                    
                    
                    // --- Recursive Call ---
                    tspBacktracking(nextCity, dist, visited, n, count + 1, newCost, path);
                    
                    
                    // --- Backtracking Step ---
                    visited[nextCity] = false;
                    path.setLength(originalPathLength);
                     
                    
                }
                
            }
        }
        return Integer.MAX_VALUE; 
    }// end of tspBacktracking
    //--syalamah--
    public static void main(String[] args) {
        
        System.out.println(backtrackingTSP(distanceMatrix));
        
        
    }
   
}