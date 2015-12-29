package coc;

import java.util.LinkedList;
import java.util.List;

public class Calculate {
	
	public List<Army> armys = new LinkedList<Army>();
	public int numCamp = 4;
	
	public Calculate() {}
	
	public void calc() {
		int totalTime = 0;
		for(Army army : armys) {
			totalTime += army.time;
		}
		int maxBag = totalTime / numCamp;
		int[][] f = {};
		f[0][0] = 0;
		for(int i=0;i<armys.size();i++) {
			for(int j=0;i < maxBag;j++){
				f[i][j] = Math.max(f[i][j], 
						f[i-1][j-armys.get(i).size] + armys.get(i).time);
				
			}
		}
	}
}
