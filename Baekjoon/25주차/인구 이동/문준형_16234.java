package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b16234 {
	static int n,l,R;
	static int[][] country;
	static boolean[][] check;
	static ArrayList<ArrayList<Node>> list;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x =x;
			this.y =y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		country = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int movecnt = 0;
		while(true) {			 
			check = new boolean[n][n]; 
			list = new ArrayList<>();
			if(check()) {
				break;
			}
			move();			
			movecnt++;		
			
		}	
		System.out.println(movecnt);
		
	}
	
	private static boolean check() {
		Queue<Node> queue = new LinkedList<>();
		boolean pass = true;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!check[i][j]) {				
					ArrayList<Node> save = new ArrayList<>();
					queue.offer(new Node(i,j));
					check[i][j]=true;	
					save.add(new Node(i,j));
					while(!queue.isEmpty()) {
						Node now = queue.poll();
						int x = now.x;
						int y = now.y;
												
						for(int d=0;d<4;d++) {
							int r = x+dr[d];
							int c = y+dc[d];
							if(r<0 || r>=n || c<0 || c>=n || check[r][c]) {
								continue;
							}
							int diff = Math.abs(country[x][y]-country[r][c]);
							
							if(diff>=l && diff<=R) {
								queue.offer(new Node(r,c));
								save.add(new Node(r,c));
								check[r][c]=true;
								pass = false;
							}
						}
						
						list.add(save);						
					}
					
				}	
				
			}
		}
		return pass;		
	}
	
	
	private static void move() {
		for(ArrayList<Node>tmp :list) {
			int sum =0;
			
			for(Node node :tmp) {
				sum += country[node.x][node.y];
			}	
			
			int divide = sum / tmp.size();
			
			for(Node node :tmp) {
				country[node.x][node.y] = divide;
			}
		}
		
		
	}

}
