package coc;

public class Pika extends Army{
	public int[] times = {0, 2700, 20, 30, 40};
	public int[] costWater = {0, 100, 100, 100, 100};
	public int[] sizes = {0, 1, 1, 1, 1};
	
	public Pika(String kind, int level) {
		super(kind, level);
		this.time = times[level];
		this.size = sizes[level];
	}
}
