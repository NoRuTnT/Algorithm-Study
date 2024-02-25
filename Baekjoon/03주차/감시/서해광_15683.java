// 15683. 감시

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, ans;
	public static int[] dr = {-1, 0, 1, 0}; // 위부터 시계방향
	public static int[] dc = {0, 1, 0, -1};
	public static class Node{
		int type;
		int r;
		int c;
		public Node(int type, int r, int c) {
			this.type = type;
			this.r = r;
			this.c = c;
		}
		
	}
	public static ArrayList<Node> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 65;
		list = new ArrayList<>();
		int[][] arr = new int[N][M];
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<M ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]>0&&arr[i][j]!=6) { // 감카 발견
					list.add(new Node(arr[i][j], i, j));
				}
			}
		}
		func(0, arr);
		System.out.println(ans);
	}
	// 재귀 호출
	// 백트래킹 가지치기
	public static void func(int idx, int[][]arr) {
		// 사각지대의 개수가 이전에 저장한 값보다 크면 return으로 날리기
		
		if(idx==list.size()) {
			// 사각지대의 수를 세고 비교
			int cnt = 0;
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<M ; j++) {
					if(arr[i][j]==0) cnt++;
				}
			}
			ans = Math.min(ans, cnt);
			return;
		}
		int[][] ar = detect(list.get(idx).type);
		int r = list.get(idx).r;
		int c = list.get(idx).c;
		for(int i=0 ; i <ar.length ; i++) {
			int[][]arr2 = arrCpy(arr);
			for(int j=0 ; j<ar[i].length ; j++) {
				int d = ar[i][j];
				// 6을 제외한 다른 숫자(1,2,3,4,5)는 표시하지 않고 넘어가야 됨
				int nr = r + dr[d];
				int nc = c + dc[d];
				while(nr>=0 && nr<N && nc>=0 && nc<M ) {
					if(arr2[nr][nc]==6) break;
					if(arr2[nr][nc]==0)
						arr2[nr][nc]=-1; // 감시된 영역은 -1로 표시
					nr+=dr[d];
					nc+=dc[d];
				}
			}
			func(idx+1, arr2);
		}
		
	}
	public static int[][] detect(int num) {
		switch (num) {
		case 1:
			return new int[][]{{0}, {1}, {2}, {3}};
		case 2:
			return new int[][]{{0, 2}, {1, 3}};
		case 3:
			return new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}};
		case 4:
			return new int[][]{{0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}};
		case 5:
			return new int[][]{{0, 1, 2, 3}};
		default:
			break;
		}
		return null;
	}
	public static int[][] arrCpy(int[][] a){
		int[][] rtr = new int[N][M];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				rtr[i][j] = a[i][j];
			}
		}
		return rtr;
	}
}
//1번 : (0), (1), (2), (3)
//2번 : (0, 2), (1, 3)
//3번 : (0, 1), (1, 2), (2, 3), (3, 0)
//4번 : (0, 1, 2), (0, 1, 3), (0, 2, 3), (1, 2, 3)
//5번 : (0, 1, 2, 3)
