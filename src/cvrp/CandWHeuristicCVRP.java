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

public class CandWHeuristicCVRP implements HeuristicCVRP {
	public double computeSolution(double[][] matrix, int[] demands, int capacity, List<Tour> solution){
    double value = 0;

    // for(int i = 0; i < demands.length; i++){
    //   Tour tour = new Tour();
    //   tour.add(i);
    //   solution.add(tour);
    // }

    Tour tour = new Tour();
    tour.add(0, matrix);
    tour.add(1, matrix);
    System.out.println(tour);
    System.out.println(matrix[0][1]);
    System.out.println(matrix[1][0]);

    return value;
  }
}
