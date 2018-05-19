package tsp;
import java.util.*;


/**
* This heuristic sorts the arcs by increasing value and
* considers each arc in turn for insertion
* An arc is inserted if and only if it does not create a subtour.
* The method stops when a tour is obtained.
*/
public class DecreasingArcHeuristicTSP implements HeuristicTSP {
    public double computeSolution(double[][] matrix, List<Integer> solution) {
        List<Arc> sortedArcs = new ArrayList<Arc>();
        List<Arc> arcSolution = new ArrayList<Arc>();
        int n = matrix.length;
        double dist = 0;
        double value = 0.0;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(i != j)
                sortedArcs.add(new Arc(i, j, matrix[i][j]));
            }
        }

        sortArcsByValue(sortedArcs);

        //Compute result
        while (sortedArcs.size() > 0) {
            Arc currentArc = sortedArcs.get(0);
            if(!createsCircuit(arcSolution, currentArc) || arcSolution.size() >= n - 1){
                removeStartingFromGoingTo(sortedArcs, currentArc.getSource(), currentArc.getTarget());
                //note : also remove currentArc from sorted Arcs
                arcSolution.add(currentArc);
                value += currentArc.getValue();
            }
            else{
                sortedArcs.remove(0);
            }
        }

        ///Reorder result
        int currentNode = 0;
        solution.add(currentNode);

        while(arcSolution.size() > 0){
            int i = searchArcBySource(arcSolution, currentNode);

            if(i == -1){
                System.out.println("Error : non related graph\n");
                return 0;
            }

            currentNode = arcSolution.get(i).getTarget();
            arcSolution.remove(i);
            solution.add(currentNode);
        }

        System.out.println(solution);

        return value;
    }

    private void sortArcsByValue(List<Arc> arcs){
        arcs.sort(new Comparator<Arc>() {
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
    }

    /*Returns true if and only if adding arc to arcs creates a circuit.
    precond : arcs does not have circuit.*/
    private Boolean createsCircuit(List<Arc> arcs, Arc arc){
        int target = arc.getTarget();
        int i = searchArcBySource(arcs, target);

        while(i != -1){
            if(arcs.get(i).getTarget() == arc.getSource()){
                return true;
            }

            i = searchArcBySource(arcs, arcs.get(i).getTarget());
        }

        return false;
    }

    private int searchArcBySource(List<Arc> arcs, int source){
        for(int i = 0; i < arcs.size(); i++){
            if(arcs.get(i).getSource() == source){
                return i;
            }
        }

        return -1;
    }

    //Removes all arcs with the same source or same target
    private void removeStartingFromGoingTo(List<Arc> arcs, int source, int target){
        Iterator<Arc> i = arcs.iterator();
        while (i.hasNext()) {
            Arc a = i.next();
            if (a.getSource() == source ||
            a.getTarget() == target) {
                i.remove();
            }
        }
    }
}
