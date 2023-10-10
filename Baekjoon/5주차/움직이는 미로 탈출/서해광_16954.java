// 16954. 움직이는 미로 탈출
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int N = 8, ans;
	public static boolean flag = false; // dfs 전용
	public static char[][] map = new char[N][N];
	public static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1, 0 }; // 12시부터 시계방향 + 제자리
	public static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		dfs(N-1, 0, 0);
		System.out.println(flag?1:0);
	}
	public static void dfs(int r, int c, int time) {
		if(flag) return;
		if(time >= N) { // 8초까지 벽을 마주치지 않고 버틸 수 있다면 이후에는 모든 벽이 없어지기 때문에 탈출 가능해짐
			flag = true;
			return;
		}
		if(falling(time)[r][c]=='#') return;
		for(int d=0 ; d<9 ; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || falling(time)[nr][nc] == '#')
				continue;
			dfs(nr, nc, time+1);
		}
	}
	public static char[][] falling(int t) {
		char[][] arr = new char[N][N];
		if (t >= N) {
			for(int i=0 ; i<N ; i++) {
				arr[i] = "........".toCharArray();
			}
			return arr;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = map[i][j];
			}
		} // arrCpy

		for (int i = 0; i < N - t; i++) {
			arr[N-1-i] = arr[N-1-i-t];
		}
		for(int i=0 ; i<t ; i++) {
			arr[i] = "........".toCharArray();
		}
		return arr;
	}
}
