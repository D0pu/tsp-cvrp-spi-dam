package cvrp;
import java.util.List;
import java.util.ArrayList;
import util.CustomList;

/**
*
* This heuristic iteratively appends a customer
* to the current solution until a tour is obtained
*
*/

public class GiantTourHeuristicCVRP implements HeuristicCVRP {
	public double computeSolution(double[][] matrix, int[] demands, int capacity, List<Tour> solution){
    System.out.println("Hello world Giant Tour");
    return 0.0;
  }
}
