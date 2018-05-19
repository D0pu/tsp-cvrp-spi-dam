package tsp;
import java.util.*;


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
		List<Arc> arcSolution = new ArrayList<Arc>();
		double dist = 0;
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

		while (sortedArcs.size() > 0) {
			Arc currentArc = sortedArcs.get(0);

			Iterator<Arc> i = sortedArcs.iterator();
			while (i.hasNext()) {
				Arc a = i.next();
				if (currentArc.getSource() == a.getSource()) {
					i.remove();
					System.out.println(a);
				} else if (currentArc.getTarget() == a.getTarget()) {
					i.remove();
					System.out.println(a);
				} else if (currentArc.getTarget() == a.getTarget()) {
					i.remove();
				} else if (currentArc.getTarget() == a.getSource() && currentArc.getSource() == a.getTarget()) {
					i.remove();
					System.out.println(a);
				}
			}
			arcSolution.add(currentArc);
			value += currentArc.getValue();
		}
		System.out.println(arcSolution);

		Arc currentArcSolution = arcSolution.get(0);
		arcSolution.remove(0);
		while (arcSolution.size() > 0) {
			Iterator<Arc> i = arcSolution.iterator();
			while (i.hasNext()) {
				Arc a = i.next();
				if (currentArcSolution.getTarget() == a.getSource()) {
					solution.add(currentArcSolution.getSource());
					currentArcSolution = a;
					i.remove();
					continue;
				}
			}
		}
		solution.add(currentArcSolution.getSource());
		solution.add(currentArcSolution.getTarget());

		System.out.println(solution);

		return value;
	}

}
