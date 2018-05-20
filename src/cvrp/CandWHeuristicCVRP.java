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
  public class Pair<A, B> {
    private final A el1;
    private final B el2;

    public Pair(A _el1, B _el2) {
      this.el1 = _el1;
      this.el2 = _el2;
    }

    public A getFirst() {
      return el1;
    }

    public B getSecond() {
      return el2;
    }
  }

	public double computeSolution(double[][] matrix, int[] demands, int capacity, List<Tour> solution){
    double value = 0;
    List<Tour> tmpStorage = new ArrayList<Tour>();
    List<Pair<Tour, Tour>> fusionableCouples = new ArrayList<Pair<Tour, Tour>>();

    createTours(tmpStorage, demands, matrix);
    while(createCouples(fusionableCouples, tmpStorage, capacity) > 0){
      Pair<Tour, Tour> pairToFusion = getMaximumProfit(fusionableCouples, matrix);
      Tour t1 = pairToFusion.getFirst();
      Tour t2 = pairToFusion.getSecond();
      t1.fusion(t2, matrix);
      tmpStorage.remove(t2);
    }

    for(int i = 0; i < tmpStorage.size(); i++){
      Tour t = tmpStorage.get(i);
      t.addStartAndEnd(matrix);
      value += t.getDistance();
      solution.add(t);
    }

    System.out.println(solution);

    return value;
  }

  //Creates initial tours, not giving the starting point
  private void createTours(List<Tour> list, int demands[], double[][] matrix){
    for(int i = 1; i < demands.length; i++){
      Tour tour = new Tour();
      tour.add(i, demands[i], matrix);
      list.add(tour);
    }
  }

  //creates all Tour couples not exceeding capacity
  private int createCouples(List<Pair<Tour, Tour>> couples, List<Tour> tours, int capacity){
    couples.clear();
    for(int i = 0; i < tours.size(); i++){
      for(int j = i + 1; j < tours.size(); j++){
        Tour t1 = tours.get(i);
        Tour t2 = tours.get(j);
        if(t1.getNeededCapacity() + t2.getNeededCapacity() <= capacity){
          Pair<Tour, Tour> pair = new Pair<Tour, Tour>(t1, t2);
          couples.add(pair);
        }
      }
    }

    return couples.size();
  }

  private Pair<Tour, Tour> getMaximumProfit(List<Pair<Tour, Tour>> couples, double[][] matrix){
    double maxProfit = 0;
    Pair<Tour, Tour> pairToFusion = null;

    for(int i = 0; i < couples.size(); i++){
      Pair<Tour, Tour> currentCouple = couples.get(i);
      Tour t1 = currentCouple.getFirst();
      Tour t2 = currentCouple.getSecond();
      double currentProfit = t1.getProfitFusion(t2, matrix);
      if(currentProfit >= maxProfit){
        pairToFusion = currentCouple;
        maxProfit = currentProfit;
      }
    }

    return pairToFusion;
  }
}
