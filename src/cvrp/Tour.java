package cvrp;
import util.CustomList;

class Tour {
	CustomList<Integer> route;
  double distance;

	public Tour() {
		this.route =  new CustomList<Integer>();
		this.distance = 0;
	}

	public double getDistance() {
		return this.distance;
	}

	public double getProfitFusion(Tour tour, double[][] matrix) {
		return 0;
	}

	public void fusion(Tour tour, double[][] matrix) {

	}

	public void add(Integer target, double[][] matrix){
		int lastDest;
		if(this.route.getLast() != null){
			lastDest = this.route.getLast();
			distance += matrix[lastDest][target];
		}
		//System.out.println(lastDest);
		this.route.add(target);
	}

	public String toString() {
		return route.toString() + "\ntotal distance: " + distance + "\n";
	}

}
