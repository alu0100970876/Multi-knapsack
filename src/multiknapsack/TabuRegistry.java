package multiknapsack;

public class TabuRegistry {
	static int tabuTenure;
	int stepsLeft;
	
	TabuRegistry() {
		this.stepsLeft = tabuTenure;
	}
	
	public void refresh() {
		stepsLeft--;
	}
	
	public boolean noStepsLeft() {
		return stepsLeft < 1;
	}
}