// 17070. 파이프 옮기기 1

import java.util.Scanner;

public class Main {
	public static int N, ans;
	public static int[][] arr;
	public static int[] dr = {0, 1, 1}; // 오른쪽, 우하단, 아래쪽
	public static int[] dc = {1, 1, 0};
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		// 배열 기준으로 스타팅 포인트 인덱스 (0,1)
		dfs(0, 0, 0, 1);
		System.out.println(ans);
	}
	
	// 이전 좌표에 대한 위치 정보를 전달해줘야
	// 어느 방향으로 이동이 가능한지 확인할 수 있다.
	public static void dfs(int preR, int preC, int r, int c) {
		if(r==N-1 && c==N-1) {
			// 도달에 성공함!
			ans++;
			return;
		}
		int[] cnt = new int[3]; // 이동 가능성을 확인함
		int sum = 0;
		for(int d=0 ; d<3 ; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<N && nc<N && arr[nr][nc]!=1) {
				cnt[d]++;
				sum++;
			}
		}
		if(preR == r) {
			// 가로로 일직선상
			if(cnt[0]==1) dfs(r, c, r, c+1);
			if(sum==3) dfs(r, c, r+1, c+1);
		}else if(preC == c){
			// 세로로 일직선상
			if(cnt[2]==1) dfs(r, c, r+1, c);
			if(sum==3) dfs(r, c, r+1, c+1);
		}else {
			// 대각선
			if(cnt[0]==1) dfs(r, c, r, c+1);
			if(cnt[2]==1) dfs(r, c, r+1, c);
			if(sum==3) dfs(r, c, r+1, c+1);
		}
		// 경계 밖으로 벗어나도 안되고 
		// 벽을 만나는 경우에도 안되고
	}
}
