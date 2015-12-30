package coc;

public class Mage extends Army {
	public int[] times = {0, 480, 20, 30, 40};
	public int[] costWater = {0, 100, 100, 100, 100};
	public int[] sizes = {0, 1, 1, 1, 1};
	
	public Mage(String kind, int level) {
		super(kind, level);
		this.time = times[level];
		this.size = sizes[level];
	}
}
