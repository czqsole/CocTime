package coc;

public class Archer extends Army{
	public int[] times = {0, 25, 20, 30, 40};
	public int[] costWater = {0, 100, 100, 100, 100};
	public int[] sizes = {0, 1, 1, 1, 1};
	
	public Archer(String kind, int level) {
		super(kind, level);
		this.time = times[level];
		this.size = sizes[level];
	}
}
