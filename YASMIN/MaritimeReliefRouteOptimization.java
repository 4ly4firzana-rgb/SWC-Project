import java.util.*;

public class MaritimeReliefRouteOptimization {

    // Distance Matrix (Adjacency Matrix)
    static int[][] distanceMatrix = { 
        {0, 15, 25, 35},   
        {15, 0, 30, 28},   
        {25, 30, 0, 20},   
        {35, 28, 20, 0}    
    }; 

    // Location names 
    static String[] locations = {"Port A", "Port B", "Relief Center C", "Relief Center D"}; 


    // ----------------------------------------------------------
    // DYNAMIC PROGRAMMING TSP
    // ----------------------------------------------------------
    public static String dynamicProgrammingTSP(int[][] dist)  
    { 
        int n = dist.length;
        int VISITED_ALL = (1 << n) - 1;

        int[][] memo = new int[n][1 << n];
        String[][] paths = new String[n][1 << n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
            Arrays.fill(paths[i], "");
        }

        int minCost = dynamicProgrammingTSPHelper(0, 1, dist, memo, VISITED_ALL, paths);

        return "Dynamic Programming TSP Route: " + paths[0][1] +
               " | Total Distance: " + minCost + " nm";
    } 


    private static int dynamicProgrammingTSPHelper(int pos, int mask, int[][] dist,  
                                                   int[][] memo, int VISITED_ALL, 
                                                   String[][] paths)  
    { 
        int n = dist.length;

        // Base case: all visited
        if (mask == VISITED_ALL) {
            paths[pos][mask] = locations[pos] + " -> " + locations[0];
            return dist[pos][0];
        }

        // Memoization check
        if (memo[pos][mask] != -1) {
            return memo[pos][mask];
        }

        int minCost = Integer.MAX_VALUE;
        String bestPath = "";

        // Explore all cities not visited
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newMask = mask | (1 << city);

                int newCost = dist[pos][city] +
                    dynamicProgrammingTSPHelper(city, newMask, dist, memo, VISITED_ALL, paths);

                if (newCost < minCost) {
                    minCost = newCost;
                    bestPath = locations[pos] + " -> " + paths[city][newMask];
                }
            }
        }

        memo[pos][mask] = minCost;
        paths[pos][mask] = bestPath;

        return minCost;
    } 


    // ----------------------------------------------------------
    // REQUIRED PLACEHOLDER METHODS (Not implemented yet)
    // ----------------------------------------------------------

    public static String greedyTSP(int[][] dist) { 
        return "Greedy TSP: NOT YET IMPLEMENTED"; 
    }

    public static String backtrackingTSP(int[][] dist) { 
        return "Backtracking TSP: NOT YET IMPLEMENTED"; 
    }

    public static String divideAndConquerTSP(int[][] dist) { 
        return "Divide & Conquer TSP: NOT YET IMPLEMENTED"; 
    }

    public static void insertionSort(int[] arr) { 
        // Not implemented
    }

    public static String binarySearch(int[] arr, int target) { 
        return "Binary Search: NOT YET IMPLEMENTED"; 
    }


    // ----------------------------------------------------------
    // MAIN METHOD (Runs only DP to avoid errors)
    // ----------------------------------------------------------
    public static void main(String[] args) {
        System.out.println(dynamicProgrammingTSP(distanceMatrix));
    }
}
