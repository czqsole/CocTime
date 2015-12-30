package coc;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Calculate {

	public List<Army> armys = new LinkedList<Army>();
	public List<Camp> camps = new LinkedList<Camp>();
	public int numCamp = 4;

	public Calculate() {
	}

	public void calc() {
		int totalTime = 0;
		int totalSize = 0;
		int barNum = 200;
		for(int i = 0;i < barNum;i++) {
			armys.add(new Barbarian("bar", 1));
		}
		//armys.add(new Barbarian("bar", 1));
		//armys.add(new Barbarian("bar", 1));
		//armys.add(new Barbarian("bar", 1));
		//armys.add(new Barbarian("bar", 1));
		
		camps.add(new Camp(1, 1));
		camps.add(new Camp(2, 1));
		camps.add(new Camp(3, 1));
		camps.add(new Camp(4, 1));
		for (Army army : armys) {
			totalSize += army.size;
			totalTime += army.time;
		}
		int maxBag = totalTime / numCamp;
		//int[][] f = new int[5][totalSize];
		//f[0][0] = 0;

		for (int k = 0; k < 3; k++) {
			Camp camp = camps.get(k);
			int maxTime = 0;
			int[][] f = new int[barNum + 5][totalTime];
			f[0][0] = 0;
			//System.out.println("k i j = " + k + " " );
			for (int i = 1; i <= armys.size(); i++) {
				//System.out.println("k i j = " + k + " " + i + " " );
				Army army = armys.get(i-1);
				//System.out.println(army.whitchCamp);
				if (army.whitchCamp > 0)
					continue;
				for (int j = 0; j <= maxBag; j++) {
					// f[i][j] = Math.max(f[i][j],
					// f[i-1][j-armys.get(i).size] + armys.get(i).time);
					//System.out.println("k i j = " + k + " " + i + " " + j);
					if(j < army.time) continue;
					if ((f[i - 1][j - army.time] + army.time) > f[i-1][j]) {
						f[i][j] = f[i - 1][j - army.time] + army.time;
						if (camp.res.containsKey(army)) {
							camp.res.put(army, camp.res.get(army) + 1);
						} else {
							camp.res.put(army, 1);
						}
						maxTime = f[i][j];
						army.whitchCamp = k;
					} else {
						f[i][j] = f[i-1][j];
					}
				}
			}
			for(Entry<Army, Integer> entry: camp.res.entrySet()) {
				camp.totalTime += entry.getValue().intValue();
			}
			System.out.println("camp " + k + " time: " + " " + maxTime);
		}
		//for()

	}
	public static void main(String[] args) {
		Calculate cal = new Calculate();
		cal.calc();
	}
}
