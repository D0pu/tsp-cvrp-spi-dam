package cvrp;
import java.util.List;

public class MainCVRP {


	private static char stringtoCode(String s){
		if(s.equals("-h1") || s.equals("--heuristic1")) return 'h';
		if(s.equals("-h2") || s.equals("--heuristic2")) return 'H';
		return '0';
	}


	/** run the test
	 *
	 * Syntax : MainCVRP -{h1,h2} instanceName
	 *
	 * h1 : Clarke and Wright heuristic
	 * h2 : Giant Tour heuristic
	 *
	 * Parameter timeLimit is only used when the first parameter is e
	 *
	 * */
	public static void main(String args[]) {
    if(args.length < 2){
      System.out.println("Argument Error\nSyntax : TestTSP -{h1,h2} instanceName");
      return;
    }

		TestCVRP tt = new TestCVRP();
		tt.loadFile(args[1]);

		List<Double> listRes; // list of results


		switch(stringtoCode(args[0])){
		case 'h' : // heuristic
			listRes = tt.testHeuristic(new CandWHeuristicCVRP());

			break;
		case 'H' : // heuristic
			listRes = tt.testHeuristic(new GiantTourHeuristicCVRP());
			break;
		default :  // error
			System.out.println("Argument Error\nSyntax : TestTSP -{h1,h2,e,l} instanceName timeLimit");
		}
	}
}
