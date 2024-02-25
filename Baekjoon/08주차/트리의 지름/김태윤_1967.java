import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 블로그 검색 통해서 아이디어 얻음.
// 임의의 노드로부터 가장 먼 노드 하나는 무조건 트리의 지름 끝점에 해당함이 보장된다 => dfs 2번
// 이러면 구조는 트리긴 한데, 문제풀이는 adjList로 푸는게 나은듯
// 97퍼에서 null pointer exception : n=1일때 오류 => 해당 경우만 따로 처리

public class 김태윤_1967 {
	static class Edge{
		int nextNode;
		int value;
		Edge(){}
		Edge(int nextNode, int value){
			this.nextNode=nextNode;
			this.value=value;
		}
	}
	static int n;
	static Edge[] edges;
	static ArrayList<Edge>[] adjList;
	static int max=0;
	static int startPoint=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		//n==1일때 예외처리
		if(n==1) {
			System.out.println(0);
			return;
		}
		adjList = new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			adjList[i]=new ArrayList<>();
		}
		StringTokenizer st;
		for(int i=0;i<n-1;i++) {
			st=new StringTokenizer(br.readLine());
			int parent=Integer.parseInt(st.nextToken());
			int child=Integer.parseInt(st.nextToken());
			int value=Integer.parseInt(st.nextToken());
			adjList[parent].add(new Edge(child, value));
			adjList[child].add(new Edge(parent, value));
		}
		dfs(1, 0, new boolean[n+1]);
		max=0;
		dfs(startPoint, 0, new boolean[n+1]);
		System.out.println(max);
	}
	
	public static void dfs(int curr, int sum, boolean[] isVisited) {	
		isVisited[curr]=true;
		for(int i=0;i<adjList[curr].size(); i++) {
			if(!isVisited[adjList[curr].get(i).nextNode]) {
				dfs(adjList[curr].get(i).nextNode, sum+adjList[curr].get(i).value, isVisited);
			}
		}
		if(sum>max) {
			max=sum;
			startPoint=curr;
		}
	}
}
