package coc;

import java.util.LinkedList;
import java.util.List;

public class Calculate {
	
	public List<Army> armys = new LinkedList<Army>();
	public List<Camp> camps = new LinkedList<Camp>();
	public int numCamp = 4;
	
	public Calculate() {}
	
	public void calc() {
		int totalTime = 0;
		armys.add(new Army());
		for(Army army : armys) {
			totalTime += army.time;
		}
		int maxBag = totalTime / numCamp;
		int[][] f = {};
		f[0][0] = 0;
		for(int k = 0;k < 3;k++) {
			Camp camp = camps.get(k);
		for(int i=0;i<armys.size();i++) {
			Army army = armys.get(i);
			if(army.whitchCamp > 0) continue;
			for(int j=0;i < maxBag;j++) {
//				f[i][j] = Math.max(f[i][j], 
//						f[i-1][j-armys.get(i).size] + armys.get(i).time);
				if((f[i-1][j-army.size] + army.time) > f[i][j]) {
					f[i][j] = f[i-1][j-army.size] + army.time;
					if(camp.res.containsKey(army)) {
						camp.res.put(army, camp.res.get(army) + 1);
					} else {
						camp.res.put(army, 1);
					}
					army.whitchCamp = k;
				}			
			}
		}
		}
		
	}
}
