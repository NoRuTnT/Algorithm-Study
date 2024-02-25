// 17135. 캐슬 디펜스
// 같은 적을 때리는 게 가능하다
// 테케만 다맞은 맞왜틀

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node{
	int r;
	int c;
	public Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
}
public class Main {
	public static int N, M, D;
	public static int ans, stage;
	public static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	public static int[] dc = {0, 0, -1, 1};
	public static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		ans = 0;
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<M ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		label1:
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				if(arr[i][j]==1) {
					stage = N-i;
					break label1;
				}
			}
		}
		perm(0, new int[3], new boolean[M]);
		System.out.println(ans);
	} // main
	
	public static void perm(int idx, int[] arrows, boolean[] visited) {
		if(idx == 3) {
			int[] arr2 = new int[3];
			for(int i=0 ; i<3 ; i++) {
				arr2[i] = arrows[i];
			}
			play(arr2);
			return;
		}
		for(int i=0 ; i<M ; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arrows[idx] = i;
				perm(idx+1, arrows, visited);
				visited[i] = false;
			}
		}
	}
	public static void play(int[]arr2) {
		int count = 0;
		// stageCount + 궁수들의 공통 행 좌표
		boolean[][] visited = new boolean[N][M];
		int[][] arr3 = new int[N][M];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				arr3[i][j] = arr[i][j];
			}
		}
		for(int i=N ; i>=N+1-stage ; i--) {
			for(int k=0 ; k<3 ; k++) {
				label:
				for(int d=1 ; d<=D ; d++) {
					// 이 다음부터는 적의 좌표를 2중 for문으로 탐색
					for(int row=i-1 ; row>=0 ; row--) {
						for(int col=0 ; col<M ; col++) {
							if(Math.abs(row-i)+Math.abs(col-arr2[k])==d && arr3[row][col]==1) {
								// 일단 찍은 좌표는 3명이 모두 끝날때 까지 가지고 가야 됨
								// 같은 곳을 중복으로 공격할 수 있기 때문에 한 명이 쏘고 난 뒤 섣불리
								// 0으로 초기화하면 안됨
								visited[row][col] = true;
								break label;
							}
						}
					}
				}
			}
			for(int id1=0 ; id1<N ; id1++) {
				for(int id2=0 ; id2<M ; id2++) {
					if(visited[id1][id2]) arr3[id1][id2]=0;
				}
			}
		}
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				if(visited[i][j]) count++;
			}
		}
		ans = Math.max(ans, count);
	} // method
}
