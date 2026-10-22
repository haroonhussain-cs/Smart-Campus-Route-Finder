
import java.util.*;

public class SmartCampusRouteFinder {

    // Represents a connection between two campus locations
    static class Edge {
        int destination;
        int distance;

        Edge(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    // Represents a location in the priority queue
    static class Node implements Comparable<Node> {
        int location;
        int distance;

        Node(int location, int distance) {
            this.location = location;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private final List<List<Edge>> graph;
    private final String[] locations;

    // Initialize campus locations and graph
    public SmartCampusRouteFinder(String[] locations) {
        this.locations = locations;
        graph = new ArrayList<>();

        for (int i = 0; i < locations.length; i++) {
            graph.add(new ArrayList<>());
        }

        addCampusPaths();
    }

    // Add a bidirectional path between two locations
    private void addEdge(int source, int destination, int distance) {
        graph.get(source).add(new Edge(destination, distance));
        graph.get(destination).add(new Edge(source, distance));
    }

    // Sample campus map with distances in metres
    private void addCampusPaths() {
        addEdge(0, 1, 200); // Main Gate - Admin Block
        addEdge(0, 2, 300); // Main Gate - Library
        addEdge(1, 2, 100); // Admin Block - Library
        addEdge(1, 3, 250); // Admin Block - Computer Lab
        addEdge(2, 3, 150); // Library - Computer Lab
        addEdge(2, 4, 200); // Library - Cafeteria
        addEdge(3, 4, 100); // Computer Lab - Cafeteria
        addEdge(3, 5, 300); // Computer Lab - Auditorium
        addEdge(4, 5, 200); // Cafeteria - Auditorium
    }

    // Display all available locations
    private void displayLocations() {
        System.out.println("\n===== CAMPUS LOCATIONS =====");

        for (int i = 0; i < locations.length; i++) {
            System.out.println(i + ". " + locations[i]);
        }
    }

    // Find the shortest path using Dijkstra's algorithm
    private void findShortestPath(int source, int destination) {
        int n = locations.length;
        int[] distances = new int[n];
        int[] previous = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        distances[source] = 0;
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.location;

            if (visited[u]) {
                continue;
            }

            visited[u] = true;

            if (u == destination) {
                break;
            }

            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int newDistance = distances[u] + edge.distance;

                if (!visited[v] && newDistance < distances[v]) {
                    distances[v] = newDistance;
                    previous[v] = u;
                    pq.offer(new Node(v, newDistance));
                }
            }
        }

        if (distances[destination] == Integer.MAX_VALUE) {
            System.out.println("\nNo route found between these locations.");
            return;
        }

        // Reconstruct the shortest route
        List<Integer> path = new ArrayList<>();

        for (int at = destination; at != -1; at = previous[at]) {
            path.add(at);
        }

        Collections.reverse(path);

        System.out.println("\n===== SHORTEST ROUTE =====");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(locations[path.get(i)]);

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nTotal distance: "
                + distances[destination] + " metres");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] campusLocations = {
            "Main Gate",
            "Admin Block",
            "Library",
            "Computer Lab",
            "Cafeteria",
            "Auditorium"
        };

        SmartCampusRouteFinder campus =
                new SmartCampusRouteFinder(campusLocations);

        System.out.println("==================================");
        System.out.println("   SMART CAMPUS ROUTE FINDER");
        System.out.println("==================================");

        while (true) {
            campus.displayLocations();

            System.out.println("\n1. Find Shortest Route");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            if (choice == 2) {
                System.out.println("Thank you for using the system!");
                break;
            }

            if (choice != 1) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            System.out.print("Enter starting location number: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
                continue;
            }
            int source = sc.nextInt();

            System.out.print("Enter destination location number: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
                continue;
            }
            int destination = sc.nextInt();

            if (source < 0 || source >= campusLocations.length
                    || destination < 0
                    || destination >= campusLocations.length) {
                System.out.println("Invalid location number.");
                continue;
            }

            campus.findShortestPath(source, destination);
        }

        sc.close();
    }
}
