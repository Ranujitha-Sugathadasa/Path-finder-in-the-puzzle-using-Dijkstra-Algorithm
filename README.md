# Path-finding-in-the-puzzle-using-Dijkstra-Algorithm

This project implements a custom solution to find the shortest path through a maze using a combination of Dijkstra's Algorithm and Breadth-First Search (BFS). The primary data structure used is a 2D array, where each cell corresponds to a graph node, storing its coordinates, distance from the start, and the previous node in the path. This setup ensures efficient distance lookup and updates. A Node class encapsulates these properties, facilitating easy handling. Nodes are managed using a PriorityQueue, which prioritizes nodes by their distance from the start, enabling efficient exploration. Dijkstra’s Algorithm is employed to calculate minimum distances, utilizing a 2D array initialized with infinite values except for the start node, and a PriorityQueue to determine the next node to explore. BFS complements this by ensuring the shortest path in unweighted graphs, making the combined approach both efficient and straightforward.

Download all the file from the git 
After that open using net beans or intrlliJ
Run the programme
