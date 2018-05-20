package cvrp;
import java.util.List;
import util.CustomList;

/**
   Interface defining the behavior of a CVRP heuristic

 */

public interface HeuristicCVRP{

	/** apply the heuristic to the CVRP problem given as a matrix
	 *
	 * @param params params from VRPinstance
	 * @param solution an empty list that will be filled with the solution for each vehicule.
	 * @return the total distance of each vehicule.
	 */
    double computeSolution(double[][] matrix, int[] demands, int capacity, List<Tour> solution);

}
