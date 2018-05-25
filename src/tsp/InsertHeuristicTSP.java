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
	public double computeSolutionFromCity(double[][] matrix, List<Integer> solution, Integer start) {
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
		reOrderSolution(solution);

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

    System.out.println("Result: " + solution);
    System.out.println("Total length: " + value);

		return value;
	}

	private void reOrderSolution(List<Integer> solution){//Starting point has to be node 0
		List<Integer> tmp = new ArrayList<Integer>();
		int start = -1;

		for(int i = 0; i < solution.size(); i++){
			if(solution.get(i) == 0){
				start = i;
				break;
			}
		}

		if(start != -1){
			for(int i = 0; i < solution.size(); i++){
				tmp.add(solution.get((i + start) % solution.size()));
			}
			solution.clear();
			for(int i = 0; i < tmp.size(); i++){
				solution.add(i, tmp.get(i));
			}
		}
	}

}
