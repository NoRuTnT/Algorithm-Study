package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 김태윤_16234 {
	static class Node{
		int r;
		int c;
		Node(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	static int n, l, r;
	static int[][] num;
	static int[] dr={-1,0,1,0};
	static int[] dc={0,1,0,-1};
	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
	private static void solve(){
		int count=0;
		while(move()) {
			count++;
		}
		System.out.println(count);
	}
	private static boolean move(){
		boolean flag=false;
		boolean[][] isVisited=new boolean[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(isVisited[i][j]) continue;
				if(bfs(new Node(i,j), isVisited)) {
					flag=true;
				}
			}
		}
		return flag;
	}
	private static boolean bfs(Node start, boolean[][] isVisited){
		Queue<Node> queue=new LinkedList<>();
		Stack<Node> stack=new Stack<>();
		int sum=0;
		boolean flag=false;
		queue.offer(start);
		while(!queue.isEmpty()){
			Node curr=queue.poll();
			if(isVisited[curr.r][curr.c]) continue;
			isVisited[curr.r][curr.c]=true;
			stack.push(curr);
			sum+=num[curr.r][curr.c];
			for(int i=0; i<4; i++){
				Node next=new Node(curr.r+dr[i], curr.c+dc[i]);
				//경계조건
				if(next.r<0 || next.r>=n || next.c<0 || next.c>=n) continue;
				//이전 방문 체크
				if(isVisited[next.r][next.c]) continue;
				//인구이동 조건
				int diff=Math.abs(num[next.r][next.c]-num[curr.r][curr.c]);
				if(diff<l || diff>r) continue;
				// bfs 추가 및 인구 이동 1번은 실시함을 확인
				queue.offer(next);
				flag=true;
			}
		}
		int avg=sum/stack.size();
		while(!stack.isEmpty()){
			Node curr=stack.pop();
			num[curr.r][curr.c]=avg;
		}
		return flag;
	}
	private static void init() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		l=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		num=new int[n][n];
		for(int i=0; i<n; i++){
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<n;j++){
				num[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	}
}
