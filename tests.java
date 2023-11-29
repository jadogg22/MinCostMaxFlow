import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class tests {


    @Test
    public void testAddition() {
        assertEquals(4, 2 + 2);
        System.err.println("Test Addition Passed");
    }

    @Test
    public void testSubtraction() {
        assertEquals(0, 2 - 2);
        System.err.println("Test Subtraction Passed");
    }
    @Test
    public void testGraph() {
        String fileName = "match1.txt";
      
        fileName = "InputFIles/" + fileName;

        Graph graph = new Graph(fileName);

        assertEquals(graph.getVertexCt(), 8);

        assertEquals(graph.getCappacity(0, 1), 2);
        assertEquals(graph.getCappacity(0, 2), 1);
        assertEquals(graph.getCappacity(4, 7), 2);
        assertEquals(graph.getCappacity(6, 7), 1);

        assertEquals(graph.getEdgeCost(0, 1), 0);
        assertEquals(graph.getEdgeCost(3, 4), 4);
        assertEquals(graph.getEdgeCost(4, 7), 0);
        assertEquals(graph.getEdgeCost(1, 6), 5);
    }

    public String[] buildPath(String numbers) {
            String[] path = numbers.split(" ");
            return path;
    }

    @Test
    public void testCalculateCost() {
        String fileName = "match1.txt";
        String filename2 = "match8.txt";
      
        fileName = "InputFIles/" + fileName;
        filename2 = "InputFIles/" + filename2;

        Graph graph = new Graph(fileName);
        Graph graph2 = new Graph(filename2);

        assertEquals(graph.calculateCost(buildPath("3 4")), "4");
        assertEquals(graph2.calculateCost(buildPath("0 11 9 4 6 5 7 2 10 13")), "9");
    }

}
