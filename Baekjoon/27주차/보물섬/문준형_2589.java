package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2589 {
	static class Node{
		int x;
		int y;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
			
		}
	}
	
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		char[][] copymap = new char[n][m];
		int[][] visit = new int[n][m];
		int max = 0;
		for(int i=0;i<n;i++) {
			map[i] = br.readLine().toCharArray();			
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j] == 'L') {
					for(int k=0;k<n;k++) {
						Arrays.fill(visit[k], -1);	
					}
					
					visit[i][j]=0;
					Queue<Node> queue = new LinkedList<>();
					queue.offer(new Node(i,j));
					while(!queue.isEmpty()) {
						Node now = queue.poll();
						int x = now.x;
						int y = now.y;
						
						for(int d=0;d<4;d++) {
							int r = x+dr[d];
							int c = y+dc[d];
							if(r<0 || r>=n || c<0 || c>=m || map[r][c]=='W') {
								continue;
							}							
							if(visit[r][c]!=-1) {
								continue;
							}
							visit[r][c] = visit[x][y]+1;
							max = Math.max(max, visit[r][c]);
							queue.offer(new Node(r,c));
						
					}
						
						
					}
				}
			}
		}
		System.out.println(max);
		
		
	}

}
