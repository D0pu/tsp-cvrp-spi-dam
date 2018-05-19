package tsp;
import java.util.List;
import java.util.ArrayList;

/**
*
* This heuristic iteratively appends a customer
* to the current solution until a tour is obtained
*
*/

public class InsertHeuristicTSP implements HeuristicTSP {

	private double computeSolutionFromCity(double[][] matrix, List<Integer> solution, Integer start) {
		double value = 0.0;

		int currentNode = start;
		int closestNode = start;
		double minDistanceClosestNode;

		solution.add(currentNode);

		while (solution.size() < matrix.length) {
			minDistanceClosestNode = Double.MAX_VALUE;
			for (int i = 0; i < matrix[currentNode].length; i++) {
				if (!solution.contains(i)) {
					if (minDistanceClosestNode > matrix[currentNode][i]) {
						closestNode = i;
						minDistanceClosestNode = matrix[currentNode][i];
					}
				}
			}
			solution.add(closestNode);
			value += minDistanceClosestNode;
			currentNode = closestNode;
		}
		if (solution.size() > 1) {
			value += matrix[solution.get(solution.size()-1)][solution.get(0)];
			solution.add(solution.get(0));
		}

		return value;
	}

	public double computeSolution(double[][] matrix, List<Integer> solution) {
		double value = Double.MAX_VALUE;

		List<Integer> tmpSolution = new ArrayList<Integer>();


		for (int i = 0; i < matrix.length; i++) {
			double currentValue = computeSolutionFromCity(matrix, tmpSolution, i);
			if (currentValue < value) {
				value = currentValue;
				solution = tmpSolution;
			}
			tmpSolution = new ArrayList<Integer>();
		}


		return value;
	}

}
