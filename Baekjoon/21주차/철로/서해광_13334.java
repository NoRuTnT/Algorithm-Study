// 13334. 철로
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n,ans;
	static long d;
	static class Pair implements Comparable<Pair>{
		long l;
		long r;
		public Pair(long l, long r) {
			this.l = l;
			this.r = r;
		}
		public int compareTo(Pair o) {
			if(this.r==o.r)
				return Long.compare(this.l, o.l);
			return Long.compare(this.r, o.r);
		}
	}
	static PriorityQueue<Long>pq;
	static List<Pair>list;
	public static void main(String[] args) throws Exception{
		pq=new PriorityQueue<>();
		list=new ArrayList<Pair>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long a1=Long.parseLong(st.nextToken());
			long a2=Long.parseLong(st.nextToken());
			if(a1<a2) {
				list.add(new Pair(a1, a2));
			}else {
				list.add(new Pair(a2, a1));				
			}
		}
		d=Long.parseLong(br.readLine());
		Collections.sort(list);
		for(int i=0;i<n;i++) {
			if(list.get(i).r-list.get(i).l>d)continue;
			pq.add(list.get(i).l);
			while(!pq.isEmpty()&&pq.peek()<list.get(i).r-d)pq.poll();
			ans=Math.max(ans, pq.size());
		}
		System.out.println(ans);
	}
}
