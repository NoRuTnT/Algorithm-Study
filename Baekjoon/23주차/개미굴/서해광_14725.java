// 14725. 개미굴
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Node implements Comparable<Node>{
		String name;
		TreeSet<Node> set;
		public Node(String name) {
			this.name=name;
			set=new TreeSet<Node>();
		}
		public int compareTo(Node o) {
			return this.name.compareTo(o.name);
		}
	}
	static int N;
	static Node root;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		root=new Node("");
		N=Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int depth=Integer.parseInt(st.nextToken());
			insert(root, st);
		}
		for(Node next : root.set) {
			print(0, next);
		}
		System.out.println(sb);
	}
	public static void insert(Node node, StringTokenizer st) {
		if(!st.hasMoreTokens()) return;
		String str=st.nextToken();
		if(!node.set.contains(new Node(str))) {
			node.set.add(new Node(str));
		}
		for(Node next : node.set) {
			if(next.name.equals(str)) {
				insert(next, st);
				break;
			}
		}
	}
	public static void print(int depth, Node node) {
		for(int i=0;i<depth*2;i++) {
			sb.append("-");
		}
		sb.append(node.name+"\n");
		for(Node next : node.set) {
			print(depth+1, next);
		}
	}
}
