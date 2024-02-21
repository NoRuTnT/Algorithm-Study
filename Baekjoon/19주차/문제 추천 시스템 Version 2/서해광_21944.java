// 21944. 문제 추천 시스템 Version 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Problem implements Comparable<Problem>{
		int P;
		int L;
		int G;
		public Problem(int P, int L, int G) {
			this.P=P;
			this.L=L;
			this.G=G;
		}
		public boolean equals(Object obj) {
			if(this==obj) return true;
			if(obj==null||obj.getClass()!=getClass())
				return false;
			Problem p=(Problem)obj;
			return (this.P==p.P)&&(this.L==p.L)&&(this.G==p.G);
		}
		public int compareTo(Problem o) {
			if(this.L==o.L)
				return this.P-o.P;
			return this.L-o.L;
		}
	}
	static int N,M,x,P,L,G;
	static TreeSet<Problem> s=new TreeSet<>();
	static Map<Integer, List<Problem>> m=new HashMap<>();
	static TreeSet[] l=new TreeSet[101];
	public static void main(String[] args) throws Exception{
		for(int i=0;i<=100;i++) {
			l[i]=new TreeSet<Problem>();
		}
		StringBuilder sb=new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			int G=Integer.parseInt(st.nextToken());
			s.add(new Problem(P, L, G));
			if(!m.containsKey(P))
				m.put(P, new ArrayList<Problem>());
			m.get(P).add(new Problem(P, L, G));
			l[G].add(new Problem(P, L, G));
		}
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "recommend":
				G=Integer.parseInt(st.nextToken());
				x=Integer.parseInt(st.nextToken());
				sb.append(recommend(G, x)).append("\n");
				break;
			case "recommend2":
				x=Integer.parseInt(st.nextToken());
				sb.append(recommend2(x)).append("\n");
				break;
			case "recommend3":
				x=Integer.parseInt(st.nextToken());
				L=Integer.parseInt(st.nextToken());
				sb.append(recommend3(x, L)).append("\n");
				break;
			case "add":
				P=Integer.parseInt(st.nextToken());
				L=Integer.parseInt(st.nextToken());
				G=Integer.parseInt(st.nextToken());
				add(P, L, G);
				break;
			case "solved":
				P=Integer.parseInt(st.nextToken());
				solved(P);
				break;
			}
		}
		System.out.println(sb);
	}
	public static int recommend(int G, int x) {
		if(x==1) {
			return ((Problem)(l[G].last())).P;
		}else {
			return ((Problem)(l[G].first())).P;			
		}
	}
	public static int recommend2(int x) {
		if(x==1) {
			return s.last().P;
		}else {
			return s.first().P;
		}
	}
	public static int recommend3(int x, int L) {
		if(x==1) {
			if(s.ceiling(new Problem(0, L, 0))==null)
				return -1;
			return s.ceiling(new Problem(0, L, 0)).P;
		}else {
			if(s.floor(new Problem(0, L, 0))==null)
				return -1;
			return s.floor(new Problem(0, L, 0)).P;
		}
	}
	public static void add(int P, int L, int G) {
		s.add(new Problem(P, L, G));
		if(!m.containsKey(P))
			m.put(P, new ArrayList<Problem>());
		m.get(P).add(new Problem(P, L, G));
		if(l[G]==null)
			l[G]=new TreeSet<Problem>();
		l[G].add(new Problem(P, L, G));
	}
	public static void solved(int P) {
		List<Problem> li=m.get(P);
		if(li==null||li.size()==0) return;
		for(Problem p:li) {
			s.remove(p);
			if(l[p.G]!=null)
				l[p.G].remove(p);
		}
		m.remove(P);
	}
}
