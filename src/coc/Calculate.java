package coc;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
		
		for(int i = 0;i < boomNum;i++) {
			armys.add(new Boom("boom", 1));
			totalNum++;
		}
		for(int i = 0;i < archerNum;i++) {
			armys.add(new Archer("archer", 1));
			totalNum++;
		}
		for(int i = 0;i < pikaNum;i++) {
			armys.add(new Pika("pika", 1));
			totalNum++;
		}
		for(int i = 0;i < mageNum;i++) {
			armys.add(new Mage("mage", 1));
			totalNum++;
		}
		Collections.sort(armys, new Comparator<Army>() {
			public int compare(Army o1, Army o2) {
				return Integer.compare(o2.time, o1.time);
				//return 0;
			}
			
		});
		
		camps.add(new Camp(1, 1));
		camps.add(new Camp(2, 1));
		camps.add(new Camp(3, 1));
		camps.add(new Camp(4, 1));
		
		int[] afterMaxTime = new int[totalNum + 1];
		//afterMaxTime[totalNum - 1] = armys.get(totalNum - 1).time;
		for(int i = totalNum - 1;i >= 0;i--) {
			afterMaxTime[i] = Math.max(armys.get(i).time, afterMaxTime[i + 1]);
			System.out.println("i:" + i + " maxTime:" + afterMaxTime[i] + " army.time:" + armys.get(i).time);
		}
		
		for (Army army : armys) {
			totalSize += army.size;
			totalTime += army.time;
			maxOneTime = Math.max(maxOneTime, maxOneTime);
		}
		int maxBag = totalTime / numCamp;
		//int[][] f = new int[5][totalSize];
		//f[0][0] = 0;
		System.out.println(totalNum + " " + totalTime + " " +maxBag);
		
		for(Army army : armys) {
			Camp camp = camps.get(0);
			camp.totalTime += army.time;
			camp.ans.add(army);
			if (camp.res.containsKey(army.kind)) {
				camp.res.put(army.kind, camp.res.get(army.kind) + 1);
			} else {
				camp.res.put(army.kind, 1);
			}
			Collections.sort(camps, new Comparator<Camp>() {
				public int compare(Camp o1, Camp o2) {
					return Integer.compare(o1.totalTime, o2.totalTime);
					//return 0;
				}
				
			});
		}
		
		for(Camp camp : camps) {
			System.out.print("camp: " + camp.id + " time:" + normalTime(camp.totalTime));
			for(Entry<String, Integer> entry : camp.res.entrySet()) {
				System.out.print(" " + entry.getValue() + entry.getKey());
			}
			System.out.println("");
		}
		
		/*for (int k = 0; k < 3; k++) {
			Camp camp = camps.get(k);
			int maxTime = 0;
			int[][] f = new int[totalNum + 5][maxBag + 5];
			//int[][][] ff = new int[totalNum + 5][maxBag + 5][totalNum + 5];
			f[0][0] = 0;
			//System.out.println("k i j = " + k + " " );
			//int sum = 0;
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
					if ((f[i - 1][j - army.time] + army.time) >= f[i-1][j]) {
							//&& (f[i - 1][j - army.time] + army.time) <= maxBag) {
						f[i][j] = f[i - 1][j - army.time] + army.time;
						camp.res.put(army, 1);
						//System.out.println("sum = " + sum);
						if (camp.res.containsKey(army)) {
							camp.res.put(army, camp.res.get(army) + 1);
						} else {
							camp.res.put(army, 1);
						}
						maxTime = f[i][j];
						//army.whitchCamp = k + 1;
					} else {
						f[i][j] = f[i-1][j];
					}
				}
			}
			//System.out.println("f[4][2700]=" + f[4][3180]);
			System.out.println("aymys.size maxBag" + f[armys.size()][maxBag]);
			//int _maxBag = maxBag;
			for(int i = armys.size();i > 0;i--) {
				Army army = armys.get(i-1);
				if(f[i][_maxBag] != f[i-1][_maxBag]) {
					army.whitchCamp = k + 1;
					_maxBag -= army.time;
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
		}*/

	}
	
	public String normalTime(int time) {
		String ans = null;
		if(time >= 0) {
			ans = String.valueOf(time / 3600) + "h " + String.valueOf((time - 3600) / 60) + "min "  + String.valueOf(time % 60) + "s";
		} else if(time >= 60) {
			ans = String.valueOf((time - 3600) / 60) + "min "  + String.valueOf(time % 60) + "s";
		} else if(time >= 0) {
			ans = String.valueOf(time % 60) + "s";
		}
		return ans;
	}
	public static void main(String[] args) {
		Calculate cal = new Calculate();
		cal.calc();
	}
}
