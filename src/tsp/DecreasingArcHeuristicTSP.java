package tsp;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * This heuristic sorts the arcs by increasing value and
 * considers each arc in turn for insertion
 * An arc is inserted if and only if it does not create a subtour.
 * The method stops when a tour is obtained.
 */
public class DecreasingArcHeuristicTSP implements HeuristicTSP {

	/** TODO coder cette méthode */
	public double computeSolution(double[][] matrix, List<Integer> solution) {
		List<Arc> sortedArcs = new ArrayList<Arc>();
		double value = 0.0;

		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				if(i != j)
					sortedArcs.add(new Arc(i, j, matrix[i][j]));
			}
		}

		sortedArcs.sort(new Comparator<Arc>() {
        @Override
        public int compare(Arc arc2, Arc arc1)
        {
					if (arc2.getValue() > arc1.getValue()) {
						return 1;
					} else if (arc2.getValue() < arc1.getValue()) {
						return -1;
					} else {
						return 0;
					}
        }
    });
		System.out.println(sortedArcs);
		return value;
	}

}
