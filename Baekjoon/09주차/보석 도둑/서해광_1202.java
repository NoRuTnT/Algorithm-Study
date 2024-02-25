// 1202. 보석 도둑
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class J implements Comparable<J>{
		int M; 
		int V;
		public J(int m, int v) {
			M = m;
			V = v;
		}
		@Override
		public int compareTo(J o) {
			return o.V - this.V;
		}
	}
	public static int N, K;
	public static long ans;
	public static PriorityQueue<J> pqJ;
	public static ArrayList<J> listJ;
	public static int[] bags;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bags = new int[K];
		listJ = new ArrayList<>();
		pqJ = new PriorityQueue<J>();
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			listJ.add(new J(m, v));
		}
		Collections.sort(listJ, (o1, o2)->o1.M - o2.M);
		for(int i=0 ; i<K ; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);
		int idx=0;
		for(int i=0 ; i<K ; i++) {
			while(idx<N && listJ.get(idx).M<=bags[i]) {
				J tmp = listJ.get(idx++);
				pqJ.add(new J(tmp.M, tmp.V));
			}
			if(!pqJ.isEmpty()) {
				ans += pqJ.remove().V;
			}
		}
		System.out.println(ans);
	}
}
