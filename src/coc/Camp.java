package coc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Camp {
	Map<String, Integer> res = new HashMap<String, Integer>();
	LinkedList<Army> ans = new LinkedList<Army>();
	public int totalTime;
	int id;
	int size;
	int[] sizes = {0, 75, 75, 75};
	
	public Camp(int id, int level) {
		this.totalTime = 0;
		this.id = id;
		this.size = sizes[level];
	}
}
