package algo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class b13334 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TreeMap<Integer, ArrayList<Integer>> treemap = new TreeMap<>();
		int n = Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int start = Math.min(a,b);
			int end=Math.max(a,b);
			if(treemap.containsKey(end)) {
				ArrayList<Integer> tmp = treemap.get(end);
				tmp.add(start);
				treemap.put(end, tmp);
			}else {		
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(start);
				treemap.put(end, tmp); 
			}
		}
		int d = Integer.parseInt(br.readLine());
		int max=0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Set<Integer> set = treemap.keySet();
		for(int e:set) {
			//막대길이벗어나면 큐에서뻄
			while(!pq.isEmpty()&&pq.peek()<e-d) {
				pq.poll();
			}	
			ArrayList<Integer> tmp2 = treemap.get(e);
			for(int i=0;i<tmp2.size();i++) {				
				int start = tmp2.get(i);					
				int out = e-d;
				if(e-start<=d) {					
					pq.offer(start);
				}
				max = Math.max(max, pq.size());
			}
		}
		System.out.println(max);
		
	}

}
