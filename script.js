
"use strict";

// Campus locations
const locations = [
    "Main Gate",
    "Admin Block",
    "Library",
    "Computer Lab",
    "Cafeteria",
    "Auditorium"
];

// Graph represented using an adjacency list
const graph = Array.from(
    { length: locations.length },
    () => []
);

// Add a bidirectional path
function addEdge(source, destination, distance) {
    graph[source].push({ destination, distance });
    graph[destination].push({ destination: source, distance });
}

// Sample campus paths (distances in metres)
addEdge(0, 1, 200);
addEdge(0, 2, 300);
addEdge(1, 2, 100);
addEdge(1, 3, 250);
addEdge(2, 3, 150);
addEdge(2, 4, 200);
addEdge(3, 4, 100);
addEdge(3, 5, 300);
addEdge(4, 5, 200);

// Priority queue implementation
class PriorityQueue {
    constructor() {
        this.items = [];
    }

    enqueue(item) {
        this.items.push(item);

        // Sort by shortest distance
        this.items.sort((a, b) => a.distance - b.distance);
    }

    dequeue() {
        return this.items.shift();
    }

    isEmpty() {
        return this.items.length === 0;
    }
}

// Dijkstra's shortest-path algorithm
function dijkstra(source, destination) {
    const n = locations.length;

    const distances = Array(n).fill(Infinity);
    const previous = Array(n).fill(-1);
    const visited = Array(n).fill(false);

    const pq = new PriorityQueue();

    distances[source] = 0;
    pq.enqueue({
        location: source,
        distance: 0
    });

    while (!pq.isEmpty()) {
        const current = pq.dequeue();
        const u = current.location;

        if (visited[u]) {
            continue;
        }

        visited[u] = true;

        if (u === destination) {
            break;
        }

        for (const edge of graph[u]) {
            const v = edge.destination;
            const newDistance = distances[u] + edge.distance;

            if (!visited[v] && newDistance < distances[v]) {
                distances[v] = newDistance;
                previous[v] = u;

                pq.enqueue({
                    location: v,
                    distance: newDistance
                });
            }
        }
    }

    // No route exists
    if (distances[destination] === Infinity) {
        return null;
    }

    // Reconstruct the route
    const path = [];
    let current = destination;

    while (current !== -1) {
        path.push(current);
        current = previous[current];
    }

    path.reverse();

    return {
        path,
        distance: distances[destination]
    };
}

// HTML elements
const sourceSelect = document.getElementById("source");
const destinationSelect = document.getElementById("destination");
const findBtn = document.getElementById("findBtn");
const swapBtn = document.getElementById("swapBtn");
const resetBtn = document.getElementById("resetBtn");

const resultSection = document.getElementById("result");
const distanceElement = document.getElementById("distance");
const stopCountElement = document.getElementById("stopCount");
const routeList = document.getElementById("routeList");
const errorMessage = document.getElementById("errorMessage");

// Find and display the shortest route
function findRoute() {
    const source = Number(sourceSelect.value);
    const destination = Number(destinationSelect.value);

    errorMessage.textContent = "";
    resultSection.hidden = true;

    if (source === destination) {
        errorMessage.textContent =
            "Please select two different locations.";
        return;
    }

    const result = dijkstra(source, destination);

    if (!result) {
        errorMessage.textContent =
            "No route is available between these locations.";
        return;
    }

    // Display total distance and number of locations
    distanceElement.textContent = `${result.distance} m`;
    stopCountElement.textContent = result.path.length;

    // Display each location in the route
    routeList.replaceChildren();

    result.path.forEach((locationIndex, index) => {
        const step = document.createElement("div");
        step.className = "route-step";

        const markerColumn = document.createElement("div");
        markerColumn.className = "route-marker-column";

        const marker = document.createElement("div");
        marker.className = "route-marker";

        if (index === 0) {
            marker.classList.add("start");
            marker.textContent = "S";
        } else if (index === result.path.length - 1) {
            marker.classList.add("end");
            marker.textContent = "D";
        } else {
            marker.textContent = String(index);
        }

        markerColumn.appendChild(marker);

        const line = document.createElement("div");
        line.className = "route-line";
        markerColumn.appendChild(line);

        const info = document.createElement("div");
        info.className = "route-info";

        const name = document.createElement("strong");
        name.textContent = locations[locationIndex];

        const description = document.createElement("small");

        if (index === 0) {
            description.textContent = "Starting location";
        } else if (index === result.path.length - 1) {
            description.textContent = "Destination reached";
        } else {
            description.textContent = "Continue along the route";
        }

        info.append(name, description);
        step.append(markerColumn, info);
        routeList.appendChild(step);
    });

    resultSection.hidden = false;
    resultSection.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });
}

// Swap start and destination
swapBtn.addEventListener("click", () => {
    const oldSource = sourceSelect.value;

    sourceSelect.value = destinationSelect.value;
    destinationSelect.value = oldSource;

    errorMessage.textContent = "";
});

// Find route
findBtn.addEventListener("click", findRoute);

// Reset the result
resetBtn.addEventListener("click", () => {
    resultSection.hidden = true;
    errorMessage.textContent = "";
    sourceSelect.focus();

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
});
