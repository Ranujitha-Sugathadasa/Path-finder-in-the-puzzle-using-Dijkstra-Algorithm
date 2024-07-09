//Name = Ranujitha Sugathadasa
//Student ID = 20221296



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static char[][] map;
    static Node startNode;
    static Node finishNode;

    public static void main(String[] args) {

        map = mazereader("benchmark_series/puzzle_10.txt");
        printMaze(map);

        List<Node> shortestPath = findShortestPath();
        long starttime = System.currentTimeMillis();
        if (shortestPath != null) {
            System.out.println("Shortest path found");
            int pastx = 0;
            int pasty = 0;
            String direction = "null";
            for (int i = shortestPath.size() - 1; i >= 0; i--) {
                Node node = shortestPath.get(i);
                if (node.getx() > pastx) {
                    direction = "Right";
                } else if (node.getx() < pastx) {
                    direction = "Left";
                } else if (node.gety() > pasty) {
                    direction = "Down";
                } else if (node.gety() < pasty) {
                    direction = "Up";
                }
                pastx = node.getx();
                pasty = node.gety();
                if (i == shortestPath.size() - 1) {
                    System.out.println("Start at " + "(" + (node.getx() + 1) + ", " + (node.gety() + 1) + ")");
                } else {
                    System.out.println((shortestPath.size() - i) + " Move " + direction + " to " + "(" + (node.getx() + 1) + ", " + (node.gety() + 1) + ")");

                }
            }
        }else{
            System.out.println("No path found");
        }
        //measure th finish time and print the time taken
        long finishtime = System.currentTimeMillis();
        System.out.println("Time taken: " + (finishtime- starttime) + "ms");

        System.out.println("Done !");
    }

    //Method to read the maze from the file
    public static char[][] mazereader(String filePath) {
        char[][] map = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            int rows = 0;
            int cols = 0;
            //Determine the number of rows and the maximum number of columns in the maze
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rows++;
                cols = Math.max(cols, line.length());
            }
            bufferedReader.close();

            //Initialize the map array with the determined dimensions
            map = new char[rows][cols];

            bufferedReader = new BufferedReader(new FileReader(filePath));
            for (int y = 0; y < rows; y++) {
                line = bufferedReader.readLine();
                for (int x = 0; x < line.length(); x++) {
                    char character = line.charAt(x);
                    //Populate the map array with characters from the file
                    map[y][x] = character;
                    //Record the start and finish nodes
                    if (character == 'S') {
                        startNode = new Node(x, y, null);
                    } else if (character == 'F') {
                        finishNode = new Node(x, y, null);
                    }
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("The File doesn't exist");
        } catch (IOException e) {
            System.out.println("Error while reading the file");
        }
        return map;
    }

    //Method to print the maze to the console
    public static void printMaze(char[][] map) {
        for (char[] charRow : map) {
            for (char character : charRow) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    //Method to find the shortest path in the maze
    public static List<Node> findShortestPath() {
        int rows = map.length;
        int cols = map[0].length;

        //initializing 2d array to hold distances from the start node
        int[][] distances = new int[rows][cols];
        for (int[] row : distances) {
            //Initialize distances with maximum value to represent infinity
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distances[startNode.gety()][startNode.getx()] = 0;

        //Priority queue for Dijsktra's algorithm
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node.gety()][node.getx()]));
        queue.add(startNode);

        //Directions : right, left, down, up
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        //Dijkstra's algorithm
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int currentX = current.getx();
            int currentY = current.gety();

            //Break if finish node is reached
            if (currentX == finishNode.getx() && currentY == finishNode.gety()) {
                finishNode = current;
                break;
            }

            //Explore neighboring nodes
            for (int[] direction : directions) {
                int newx = currentX + direction[0];
                int newy = currentY + direction[1];
                int newDistance = distances[currentY][currentX];

                //Move along the direction until a wall of finish node is reached
                while (locationIsValid(newx, newy) && map[newy][newx] != '0') {
                    if (map[newy][newx] == 'F') {
                        newDistance += 1;
                        newx += direction[0];
                        newy += direction[1];
                        break;
                    } else {
                        newDistance += 1;
                        newx += direction[0];
                        newy += direction[1];
                    }

                }
                newx -= direction[0];
                newy -= direction[1];

                Node newNode = new Node(newx, newy, null);

                //Update the distance and enqueue the node if a shorter  path is found
                if (newDistance < distances[newy][newx]) {
                    distances[newy][newx] = newDistance;
                    newNode.setPrevNode(current);
                    queue.add(newNode);
                }
            }
        }

        //Reconstruct the shortest path
        List<Node> shortestPath = new ArrayList<>();
        Node current = finishNode;
        while (current != null) {
            shortestPath.add(current);
            current = current.getPrevNode();
        }
        return shortestPath;
    }

    //Method to check if a location is valid (within bounds of the maze)
    private static boolean locationIsValid(int x, int y) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length;
    }
}


