package tsp;

class Arc {
	private int source;
	private int target;
	private double value;

	public Arc(int source, int target, double value) {
		this.source = source;
		this.target = target;
		this.value = value;
	}

	public int getSource() {
		return source;
	}

	public int getTarget() {
		return target;
	}

	public double getValue() {
		return value;
	}

	public String toString() {
		return "(" + source + "," + target + ") : " + value;
	}
}
