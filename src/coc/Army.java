package coc;

public class Army {
	//public String name;
	public int time;
	public String kind;
	public int level;
	public int numbers;
	public int size;
	public int whitchCamp;
	
	public Army(){}
	
	public Army(String kind, int level) {
		this.kind = kind;
		this.level = level;
		this.whitchCamp = 0;
	}
}
