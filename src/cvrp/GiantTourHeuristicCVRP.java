package cvrp;

import java.util.*;
import java.util.List;
import java.util.ArrayList;
import util.CustomList;
import tsp.*;

/**
*
* This heuristic iteratively appends a customer
* to the current solution until a tour is obtained
*
*/

public class GiantTourHeuristicCVRP implements HeuristicCVRP {
	public double computeSolution(double[][] matrix, int[] demands, int capacity, List<Tour> solution){
		List<Integer> soluceTSP = new ArrayList<Integer>();
		InsertHeuristicTSP TSP = new InsertHeuristicTSP();
		double val = TSP.computeSolutionFromCity(matrix, soluceTSP, 0);

		Graph graph = new Graph(soluceTSP.size()-1);

		double length = 0;
		int demand = 0;
		for (int x = 1; x < soluceTSP.size()-1; x++) {
			int i = soluceTSP.get(x);
			length = matrix[0][i]*2;
			demand = demands[i];
			graph.addEdge(x-1, x, length);
			for (int y = x+1; y < soluceTSP.size()-1; y++) {
				int jMoinsUn = soluceTSP.get(y-1);
				int j = soluceTSP.get(y);
				length -= matrix[jMoinsUn][0];
				length += matrix[i][j];
				length += matrix[j][0];
				demand += demands[j];
				if (demand > capacity) {
					continue;
				}
				graph.addEdge(x-1, y, length );
			}
		}
		graph.BellmanFord();

		long[] path = graph.prev;
		int lastIndex = (int) path.length-1;
		int currentIndex = (int) path[lastIndex];
		Tour tour;
		while (currentIndex != -1) {
			tour = new Tour();
			for (int i = currentIndex+1; i <= lastIndex; i++) {
				int node = soluceTSP.get(i);
				tour.add(node, demands[node], matrix);
			}
			lastIndex = currentIndex;
			currentIndex = (int) path[lastIndex];
			solution.add(tour);
		}

		double value = 0;
    for(Tour t : solution){
      t.addStartAndEnd(matrix);
      value += t.getDistance();
    }

    System.out.println(solution);
    System.out.println("Total length: " + value);
    return value;
  }
}
