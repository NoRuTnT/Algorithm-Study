import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	static ArrayList<Integer>[] adjList;
	static int[] inDegree;
	static boolean[] check;
	static Stack<Integer> stack;
	static StringBuilder sb;
	public static void recursion(int vertexIdx) {
		check[vertexIdx]=true;
		for(int i=0;i<adjList[vertexIdx].size();i++) {
			int nextIdx=adjList[vertexIdx].get(i); // 연결된 곳
			if(!check[nextIdx]) {
				recursion(nextIdx);
			}
		}
		stack.add(vertexIdx);
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int v=sc.nextInt(); //정점 개수
		int e=sc.nextInt(); //간선 개수
		adjList=new ArrayList[v+1]; // 인접리스트
		inDegree=new int[v+1]; // 정점별 진입 차수
		check=new boolean[v+1];
		for(int i=1;i<=v;i++) {
			adjList[i]=new ArrayList<>(); // 인접리스트 초기화
			//방향이 존재하므로, i를 출발점, adjList[i] 내의 어레이 리스트 요소를 도착점이라 생각
		}
		for(int i=0;i<e;i++) {
			int v1=sc.nextInt();
			int v2=sc.nextInt();
			adjList[v1].add(v2); // 출발지 index에 도착지 index를 넣는다
			inDegree[v2]++; //  v2로 들어오는 간선의 개수 하나 추가
		}
		stack=new Stack<>();
		sb=new StringBuilder();
		for(int i=1;i<=v;i++) {
			if(inDegree[i]==0) recursion(i); // 정점 0인 거만 탐색 시작
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}
