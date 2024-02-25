package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b1967 {
    static class Node {
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static int n, maxDistanceNode;
    static ArrayList<Node>[] tree;
    static boolean[] visited;
    static int maxDistance;
    static boolean finish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if(n==1) {	//노드가 하나일때는 따로처리
        	finish=true;
        }
        
        tree = new ArrayList[n+1]; //간선연결정보 저장용
        for (int i=1;i<=n;i++) {
            tree[i] = new ArrayList<>();
        }
        
        
        for (int i=0;i<n-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, c)); //저장
            tree[b].add(new Node(a, c));
        }
        if(!finish) { //n이 1이아닐때
        	maxDistance = 0;
        	visited = new boolean[n+1]; 
        	dfs(1, 0); //아무점에서 dfs돌림
        	
        	maxDistance = 0;
        	visited = new boolean[n+1];
        	dfs(maxDistanceNode, 0); //1차로구한 최대거리끝노드에서 dfs돌림 
        }
        System.out.println(maxDistance); //두번돌린후 결과출력
    }

    private static void dfs(int node, int distance) {
        visited[node] = true; //방문처리
        if (distance > maxDistance) {
            maxDistance = distance;	//길이저장
            maxDistanceNode = node; //노드저장
        }
        
        for (Node n : tree[node]) {    	
            if (!visited[n.end]) {
                dfs(n.end, distance + n.weight);
            }
        }
    }
}
