
public class ReliefRouteOptimization {

    static String[] locations = {"Port A", "Port B", "Relief Center C", "Relief Center D"};
    static int[][] distanceMatrix = {
        {0, 15, 25, 35}, // From Port A
        {15, 0, 30, 28}, // From Port B
        {25, 30, 0, 20}, // From Relief Center C
        {35, 28, 20, 0}  // From Relief Center D
    };

    public static void main(String[] args) {
        // Solve the RROP using Divide and Conquer
        String result = divideAndConquerTSP(distanceMatrix);
        System.out.println(result);
    }

    public static String divideAndConquerTSP(int[][] dist) {
        StringBuilder path = new StringBuilder();
        int currentCost = 0;
        boolean[] visited = new boolean[dist.length];

        // Start at Port A and mark it as visited
        path.append("Start at ").append(locations[0]).append(" -> ");
        visited[0] = true; // Mark Port A as visited

        // Solve each sub-route (e.g., Port A -> Port B and Relief Center C -> Relief Center D)
        currentCost = divideAndConquerHelper(0, visited, currentCost, dist, dist.length, path);

        // Return to the starting point (Port A)
        path.append(locations[0]).append(" | Total Distance: ").append(currentCost).append(" nm");

        return path.toString();
    }

    private static int divideAndConquerHelper(int pos, boolean[] visited, int currentCost, int[][] dist, int n, StringBuilder path) {
        if (allVisited(visited)) {
            return currentCost + dist[pos][0]; // Return to the starting point (Port A)
        }

        int minDist = Integer.MAX_VALUE;
        int nextCity = -1;

        // Modified greedy approach to prioritize Relief Center C before Relief Center D
        for (int i = 0; i < n; i++) {
            // Skip already visited locations
            if (visited[i]) continue;

            // Prioritize Relief Center C (index 2) before Relief Center D (index 3)
            if ((i == 2 && pos == 1) || (i == 3 && pos == 1 && visited[2])) {
                nextCity = i;
                break;
            }

            if (!visited[i] && dist[pos][i] < minDist) {
                minDist = dist[pos][i];
                nextCity = i;
            }
        }

        visited[nextCity] = true;
        path.append(locations[nextCity]).append(" -> ");

        return divideAndConquerHelper(nextCity, visited, currentCost + minDist, dist, n, path);
    }

    // Utility method to check if all cities have been visited
    private static boolean allVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}
