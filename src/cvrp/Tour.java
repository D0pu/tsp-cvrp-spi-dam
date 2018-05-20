package cvrp;
import util.CustomList;

class Tour {
	private CustomList<Integer> route;
  private double distance;
	private int neededCapacity;

	public Tour() {
		this.route =  new CustomList<Integer>();
		this.distance = 0;
		this.neededCapacity = 0;
	}

	public double getDistance() {
		return this.distance;
	}

	public double getNeededCapacity() {
		return this.neededCapacity;
	}

	public CustomList<Integer> getRoute() {
		return this.route;
	}

	public double getProfitFusion(Tour tour, double[][] matrix) {
    if(this.route.getLast() == null){
      return 0;
    }
    if(tour.route.getFirst() == null){
      return 0;
    }
    int lastDest = this.route.getLast();
    int firstDest = tour.route.getFirst();
    double extraDistance = matrix[lastDest][0] + matrix[0][firstDest];
    double alternativeDistance = matrix[lastDest][firstDest];

		return extraDistance - alternativeDistance;
	}

	public void fusion(Tour tour, double[][] matrix) {
		int lastDest = this.route.getLast();
    int firstDest = tour.route.getFirst();
    this.distance += matrix[lastDest][firstDest] + tour.getDistance();
		this.neededCapacity += tour.getNeededCapacity();
		this.route.append(tour.getRoute());
	}

	public void add(Integer target, int demand, double[][] matrix){
		int lastDest;
		if(this.route.getLast() != null){
			lastDest = this.route.getLast();
			distance += matrix[lastDest][target];
		}

		this.neededCapacity += demand;
		this.route.add(target);
	}

	public void addStartAndEnd(double[][] matrix){
		int firstDest = this.route.getFirst();
    int lastDest = this.route.getLast();

		distance += matrix[0][firstDest] + matrix[lastDest][0];
		this.route.addHead(0);
		this.route.add(0);
  }

	public String toString() {
		return route.toString() + " distance: " + distance + " neededCapacity: " + neededCapacity + "\n";
	}

}
