// 7432. 디스크 트리 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Main {
	static int N,L;
	static StringBuilder sb;
	static class Dir implements Comparable<Dir>{
		String name;
		TreeSet<Dir> dirs;
		public Dir(String name) {
			this.name=name;
			this.dirs=new TreeSet<>();
		}
		public int compareTo(Dir o) {
			return this.name.compareTo(o.name);
		}
		public boolean equals(Object obj) {
			if(this==obj) return true;
			if(obj==null||getClass()!=obj.getClass())
				return false;
			Dir d=(Dir)obj;
			return this.name.equals(d.name);
		}
	}
	static Dir curr,root;
	static String[]arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		root=new Dir("");
		N=Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			String str=br.readLine();
			arr=str.split("\\\\"); // 정규식 주의
			L=arr.length;
			insert(root,0);
		}
		for(Dir d:root.dirs) {
			print(d, 0);
		}
		System.out.println(sb);
	}
	public static void insert(Dir d, int idx) {
		if(idx==L) return;
		if(!d.dirs.contains(new Dir(arr[idx]))) {
			d.dirs.add(new Dir(arr[idx]));
		}
		for(Dir dd:d.dirs) {
			if(dd.name.equals(arr[idx])) {
				insert(dd, idx+1);
			}
		}
		
	}
	// complete
	public static void print(Dir d,int depth) {
		for(int i=0;i<depth;i++) {
			sb.append(" ");
		}
		sb.append(d.name);
		sb.append("\n");
		if(d.dirs.size()==0) {
			return;
		}
		for(Dir dd:d.dirs) {
			print(dd, depth+1);
		}
	}
}
