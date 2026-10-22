
# Smart Campus Route Finder

## 1. Project Overview

The Smart Campus Route Finder is a web-based application designed to help students, faculty members, and visitors find the shortest path between different locations within a college campus.

The application represents the campus as a graph, where locations are vertices and the paths connecting them are edges. An adjacency list stores the connections between locations, while Dijkstra's algorithm calculates the shortest route based on the distance between locations.

The system provides a simple and user-friendly interface that displays the recommended route, total distance, and step-by-step directions.

## 2. Objectives

- Find the shortest path between two campus locations.
- Represent campus buildings and paths using a graph.
- Implement Dijkstra's shortest-path algorithm.
- Demonstrate the use of adjacency lists and priority queues.
- Provide a simple and responsive user interface.
- Reduce the time and effort required to navigate a campus.

## 3. Features

- Select a starting location and destination.
- Calculate the shortest route automatically.
- Display the total travel distance in metres.
- Show locations in the order they should be visited.
- Swap the starting location and destination.
- Display route directions with visual markers.
- Validate location selections.
- Reset the results and plan another route.
- Responsive design for desktop and mobile devices.

## 4. Technologies Used

### Frontend
- HTML5 — webpage structure
- CSS3 — styling and responsive layout
- JavaScript — application logic and shortest-path calculation

### Data Structures and Algorithms
- Graph — represents the campus network
- Adjacency List — stores connections between locations
- Priority Queue — processes locations by shortest distance
- Dijkstra's Algorithm — finds the shortest path

## 5. Campus Locations

The application currently includes the following sample locations:

1. Main Gate
2. Admin Block
3. Library
4. Computer Lab
5. Cafeteria
6. Auditorium

The distances between these locations are predefined sample values measured in metres. They can be modified to represent an actual college campus.

## 6. Project Structure

```text
SmartCampusRouteFinder/
│
├── index.html
├── style.css
├── script.js
└── README.md
```

### File Descriptions

- `index.html` — contains the webpage layout, location selection fields, buttons, and result section.
- `style.css` — defines the colours, layout, cards, route markers, and responsive design.
- `script.js` — stores the campus graph, implements Dijkstra's algorithm, and manages user interactions.
- `README.md` — documents the project and explains how to run it.

## 7. Algorithm Used

Dijkstra's algorithm is used to find the shortest route between a selected source and destination.

### Steps

1. Initialize the distance to the starting location as zero.
2. Set the distances to all other locations to infinity.
3. Insert the starting location into the priority queue.
4. Remove the location with the smallest current distance.
5. Examine its connected locations.
6. Update their distances if a shorter path is found.
7. Store the previous location to reconstruct the route.
8. Continue until the destination is reached or no locations remain to process.
9. Reconstruct and display the shortest route and total distance.

**Note:** The JavaScript implementation uses a simple array-based priority queue with sorting. A heap-based priority queue would improve performance for larger campus maps.

## 8. How to Run the Project

### Requirements

- A modern web browser, such as Google Chrome, Microsoft Edge, or Firefox.
- A code editor, such as Visual Studio Code (optional).

### Steps

1. Create a folder named `SmartCampusRouteFinder`.
2. Create the files `index.html`, `style.css`, and `script.js`.
3. Copy the corresponding source code into each file.
4. Save all files in the same folder.
5. Open `index.html` in your web browser.
6. Select the starting location and destination.
7. Click **Find Shortest Route**.
8. View the calculated distance and route directions.

No server installation or external JavaScript library is required.

## 9. Example

Suppose the user selects:

- Starting Location: Main Gate
- Destination: Auditorium

The application calculates the shortest route:

Main Gate → Library → Computer Lab → Cafeteria → Auditorium

Total Distance: 750 metres

This result is based on the sample connections and distances configured in the application.

## 10. Expected Output

The application displays:

- The selected starting location and destination.
- The shortest route between them.
- The total distance in metres.
- The ordered list of locations along the route.
- Visual indicators for the starting point and destination.

## 11. Applications

- College and university campus navigation.
- Locating academic buildings and laboratories.
- Finding routes to libraries, offices, and cafeterias.
- Demonstrating graph algorithms in computer science education.
- Learning fundamental data structures through a practical project.

## 12. Future Enhancements

- Add an interactive visual campus map.
- Include more buildings and walking paths.
- Allow users to enter or update distances.
- Add estimated walking time.
- Support accessible routes for users with mobility requirements.
- Integrate real campus maps and GPS-based navigation.
- Implement a heap-based priority queue for better efficiency.

## 13. Limitations

- The application uses predefined sample locations and distances.
- It does not currently use GPS or live map data.
- The displayed route is calculated using the configured graph.
- Actual walking distances may differ from the sample values.
- The application does not automatically detect blocked paths or temporary campus restrictions.

## 14. Conclusion

The Smart Campus Route Finder demonstrates how graphs, adjacency lists, priority queues, and Dijkstra's algorithm can solve a practical shortest-path problem.

By combining these concepts with HTML, CSS, and JavaScript, the project provides an interactive way to explore shortest-path calculations and campus navigation. It also offers a foundation for future development into a more advanced campus navigation system.

## 15. Author

**Project Name:** Smart Campus Route Finder

**Project Type:** Academic Mini Project

**Domain:** Data Structures and Algorithms

**Programming Technologies:** JavaScript, HTML, CSS
