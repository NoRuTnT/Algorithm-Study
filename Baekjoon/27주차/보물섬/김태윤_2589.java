package Week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_2589 {
	static class Node{
		int r;
		int c;
		Node(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	static int n, m;
	static char[][] map;
	static int[] dr={-1,0,1,0};
	static int[] dc={0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new char[n][m];
		for(int i=0; i<n; i++){
			map[i]=br.readLine().toCharArray();
		}
		br.close();
		int ans=0;
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(map[i][j]=='L') ans=Math.max(ans, bfs(i,j));
			}
		}
		System.out.println(ans);
	}
	private static int bfs(int r, int c){
		Queue<Node> queue=new LinkedList<>();
		int[][] isVisited=new int[n][m];
		queue.add(new Node(r,c));
		isVisited[r][c]=1;
		int max=1;
		while(!queue.isEmpty()){
			Node curr=queue.poll();
			max=Math.max(max, isVisited[curr.r][curr.c]);
			for(int k=0; k<4; k++){
				Node next=new Node(curr.r+dr[k], curr.c+dc[k]);
				if(next.r<0 || next.r>=n || next.c<0 || next.c>=m) continue;
				if(isVisited[next.r][next.c]>0) continue;
				if(map[next.r][next.c]=='W') continue;
				queue.add(next);
				isVisited[next.r][next.c]=isVisited[curr.r][curr.c]+1;
			}
		}
		return max-1;
	}
}
