// 13460. 구슬 탈출 2
// 완성할 수 있는데까지 풀었습니다.
import java.util.Scanner;

public class Main4 {
	public static int N, M, ans;
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	static class Node{
		char val;
		int r;
		int c;
		public Node(char val, int r, int c) {
			this.val = val;
			this.r = r;
			this.c = c;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ans = Integer.MAX_VALUE; // 10번 이하로 탈출이 가능하면 그 값으로 갱신
		N = sc.nextInt();
		M = sc.nextInt();
		char[][] map = new char[N][M];
		int[][] balls = new int[2][2];
		for(int i=0 ; i<N ; i++) {
			String str = sc.next();
			for(int j=0 ; j<M ; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='R') {
					balls[0][0] = i; // r
					balls[0][1] = j; // c
				}else if(map[i][j]=='B') {
					balls[1][0] = i; // r
					balls[1][1] = j; // c
				}
			}
		}
		dfs(0, map, balls);
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println((ans==Integer.MAX_VALUE)?-1:ans);
	}
	// 방문체크를 원본 map에 X 형태로 표시
	public static void dfs(int time, char[][] arr, int[][] ballArr) {
		if(time>=10 || time>=ans) return; // 10번 이내
		for(int d=0 ; d<4 ; d++) {
			char[][] c = arrCpy(arr);
			int[][] newBalls = ballCpy(ballArr);
			for(int i=0 ; i<2 ; i++) {
				for(int j=0 ; j<2 ; j++) {
					newBalls[i][j] = ballArr[i][j];
				}
			}
			Node nodeR = null;
			Node nodeB = null;
			if(priority(newBalls, d)) { // true면 빨간공부터 움직임
				nodeR = move(c, d, newBalls[0], 'R'); // 빨간색
				if(nodeR.val=='X') continue;
				nodeB = move(c, d, newBalls[1], 'B');// 파란색
				if(nodeB.val=='O') continue; // 파란공이 들어가면 안됨
			}else {
				nodeB = move(c, d, newBalls[1], 'B');// 파란색
				if(nodeB.val=='O') continue; // 파란공이 들어가면 안됨
				nodeR = move(c, d, newBalls[0], 'R'); // 빨간색
				if(nodeR.val=='X') continue;
			}
			
			if(nodeR.val=='O') {
				 ans = Math.min(ans, time);
				 continue;
			}
			
			dfs(time+1, c, newBalls);
		}
	}
	public static Node move(char[][] arr, int dir, int[]ballArr, char ballColor) {
		// O를 만나면 구멍의 좌표를 반환 
		// #이나 RorB를 만나면 벽이나 공을 만나서 멈추게 된 지점의 좌표를 반환
		int nr = ballArr[0] + dr[dir];
		int nc = ballArr[1] + dc[dir];
		if(arr[nr][nc]=='#') {
			return new Node('#', ballArr[0], ballArr[1]);
		}
		if(arr[nr][nc]=='B') {
			return new Node('B', ballArr[0], ballArr[1]);
		}
		if(arr[nr][nc]=='R') {
			return new Node('R', ballArr[0], ballArr[1]);
		}
		if(arr[nr][nc]=='O') {
			return new Node('O', nr, nc);
		}
		while(true) {
			if(arr[nr][nc]=='#') {
				arr[ballArr[0]][ballArr[1]]='X';
				arr[nr-dr[dir]][nc-dc[dir]]= ballColor;
				ballArr[0] = nr-dr[dir];
				ballArr[1] = nc-dc[dir];
				return new Node('#', nr-dr[dir], nc-dc[dir]);
			}
			if(arr[nr][nc]=='R'||arr[nr][nc]=='B') {
				arr[ballArr[0]][ballArr[1]]='X';
				arr[nr-dr[dir]][nc-dc[dir]]= ballColor;
				ballArr[0] = nr-dr[dir];
				ballArr[1] = nc-dc[dir];
				return new Node('#', nr-dr[dir], nc-dc[dir]);
			}
			if(arr[nr][nc]=='O') {
				arr[ballArr[0]][ballArr[1]]='.';
				return new Node('O', nr, nc);
			}
			if(arr[nr][nc]=='X')
				return new Node('X', nr, nc);
			nr += dr[dir];
			nc += dc[dir];
		}
	}
	// 0:상, 1:우, 2:하, 3:좌
	// true면 빨간곻부터 false면 파란공부터 움직임
	public static boolean priority(int[][] ballArr, int dir) {
		if(dir==0) return ballArr[0][0]<ballArr[1][0]?true:false;
		if(dir==1) return ballArr[0][1]>ballArr[1][1]?true:false;
		if(dir==2) return ballArr[0][0]>ballArr[1][0]?true:false;
		if(dir==3) return ballArr[0][1]<ballArr[1][1]?true:false;
		return true;
	}
	public static int[][] ballCpy(int[][] arr){
		int[][] arr2 = new int[2][2];
		for(int i=0 ; i<2 ; i++) {
			for(int j=0 ; j<2 ; j++) {
				arr2[i][j] = arr[i][j];
			}
		}
		return arr2;
	}
	public static char[][] arrCpy(char[][] arr){
		char[][] arr2 = new char[N][M];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				arr2[i][j] = arr[i][j];
			}
		}
		return arr2;
	}
}
// 범위 작음. 웬만한건 다 해볼 수 있음.
// 공이 벽이나 다른 공을 만나서 멈추게 된 지점이 아닌 경우 (중간 이동 과정)
// 경로상 중복되는 경우가 발생할 수 있다. 따라서 이경우 방문체크를 해서는 안될 듯.
// dfs가 답이다

// R이 못움직여도 기다릴 수 있어야 됨. 아래 케이스
//#######
//###.###
//#R.B.O#
//#######

// 코앞에 벽에서 막혔으면 그냥 자기 자신자리 X로 쳐놓기 
