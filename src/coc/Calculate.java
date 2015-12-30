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
		int totalNum = 0;
		int pikaNum = 2, mageNum = 18, boomNum = 8, archerNum = 2;
		int maxOneTime = 0;
		for(int i = 0;i < pikaNum;i++) {
			armys.add(new Pika("pika", 1));
			totalNum++;
		}
		for(int i = 0;i < mageNum;i++) {
			armys.add(new Mage("mage", 1));
			totalNum++;
		}
		for(int i = 0;i < boomNum;i++) {
			armys.add(new Boom("boom", 1));
			totalNum++;
		}
		for(int i = 0;i < archerNum;i++) {
			armys.add(new Archer("archer", 1));
			totalNum++;
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
			maxOneTime = Math.max(maxOneTime, maxOneTime);
		}
		int maxBag = totalTime / numCamp;
		//int[][] f = new int[5][totalSize];
		//f[0][0] = 0;
		System.out.println(totalNum + " " + totalTime + " " +maxBag);
		
		for (int k = 0; k < 3; k++) {
			Camp camp = camps.get(k);
			int maxTime = 0;
			int[][] f = new int[totalNum + 5][totalTime];
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
					if ((f[i - 1][j - army.time] + army.time) > f[i-1][j] 
							&& (f[i - 1][j - army.time] + army.time) <= maxBag) {
						f[i][j] = f[i - 1][j - army.time] + army.time;
						camp.res.put(army, 1);
						/*if (camp.res.containsKey(army)) {
							camp.res.put(army, camp.res.get(army) + 1);
						} else {
							camp.res.put(army, 1);
						}*/
						maxTime = f[i][j];
						army.whitchCamp = k + 1;
					} else {
						f[i][j] = f[i-1][j];
					}
				}
			}
			camp.totalTime = 0;
			for(Entry<Army, Integer> entry: camp.res.entrySet()) {
				camp.totalTime += entry.getKey().time;
			}
			System.out.println("camp " + k + " time: " + " " + camp.totalTime + " " + maxTime);
		}
		Camp camp = camps.get(3);
		for(int i = 0; i < armys.size(); i++) {
			Army army = armys.get(i);
			if(army.whitchCamp == 0) {
				if (camp.res.containsKey(army)) {
					camp.res.put(army, camp.res.get(army) + 1);
				} else {
					camp.res.put(army, 1);
				}
			}
		}
		int maxTime=0;
		for(Entry<Army, Integer> entry: camp.res.entrySet()) {
			camp.totalTime += entry.getKey().time;
		}
		System.out.println("camp 3" +  " time: " + " " + camp.totalTime);
		//for()
		for(Army army : armys) {
			System.out.println(army.kind + " " + army.whitchCamp);
		}

	}
	public static void main(String[] args) {
		Calculate cal = new Calculate();
		cal.calc();
	}
}
