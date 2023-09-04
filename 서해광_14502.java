// 14502. 연구소

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int[][] arr;
	private static int N;
	private static int M;
	private static boolean[][] visited;
	private static int ans;
	
	private static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	private static ArrayList<Node> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		visited = new boolean[N][M];
		list = new ArrayList<>();
		ans = 0;
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j]==0)
					list.add(new Node(i, j));
			}
		}
		for(int i=0 ; i<list.size()-2 ; i++) {
			for(int j=i+1 ; j<list.size()-1 ; j++) {
				for(int k=j+1 ; k<list.size() ; k++) {
					int[][] arr2 = arrCpy();
					Node n1 = list.get(i);
					Node n2 = list.get(j);
					Node n3 = list.get(k);
					arr2[n1.r][n1.c]=1;
					arr2[n2.r][n2.c]=1;
					arr2[n3.r][n3.c]=1;
					for(int m1=0 ; m1<N ; m1++) {
						for(int m2=0 ; m2<M ; m2++) {
							dfs(m1,m2, arr2);
						}
					}
					int tmp = 0;
					for(int m1=0 ; m1<N ; m1++) {
						for(int m2=0 ; m2<M ; m2++) {
							if(arr2[m1][m2]==0)
								tmp++;
						}
					}
					ans = Math.max(tmp, ans);
				}
			}
		}
		System.out.println(ans);
	}
	public static void dfs(int r, int c, int[][]arr) {
		if(arr[r][c]!=2)
			return;
		for(int d=0 ; d<4 ; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>= N || nc<0 || nc>=M || arr[nr][nc]!=0)
				continue;
			arr[nr][nc] = 2;
			dfs(nr, nc, arr);
		}
	}
	public static int[][] arrCpy(){
		int[][] arr2 = new int[N][M];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				arr2[i][j] = arr[i][j];
			}
		}
		return arr2;
	}
}
