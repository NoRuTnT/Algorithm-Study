//DFS, 예제 1~5번 && 제출 시 1%까지만 맞는 오답

import java.util.Scanner;

public class 김태윤_13460 {
	static class pos{
		int r;
		int c;
		pos(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	static char[][] board;
	static int n;
	static int m;
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	static pos red;
	static pos blue;
	static int ans = 11;
	public static void main(String[] args) {
		input();
		dfs(1, red, blue, -1, -1); // depth, red blue 위치, 이전 이동 방향
		output();
	}
	public static void input() {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		board = new char[n][m];
		for(int i=0;i<n;i++) {
			board[i]=sc.next().toCharArray();
			for(int j=0;j<m;j++) {
				if(board[i][j]=='R') red = new pos(i,j);
				else if(board[i][j]=='B') blue = new pos(i,j);
			}
		}
		sc.close();
	}
	public static void output() {
		if(ans==11) System.out.println(-1);
		else System.out.println(ans);
	}
	public static void dfs(int depth, pos currRed, pos currBlue, int before, int again) {
		if(depth >= ans) { // 백트래킹
			return;
		}
		
		for(int k=0;k<4;k++) {
			if(k==before || k == again) continue; // 이미 한번한 방향 or 왔던 방향 배제
			
			boolean moveRed = true;
			boolean moveBlue = true;
			
			pos movingRed = currRed;
			pos movingBlue = currBlue;
			
			boolean flag=false;
			
			while(moveRed || moveBlue) { // 둘 다 이동 불가능할 때 까지 계속 돌린다
				//가장 바깥쪽은 전부 #으로 되어 있으니 경계조건은 고려하지 않아도 된다.
				
				pos nextRed = new pos(movingRed.r+dr[k], movingRed.c+dc[k]);
				pos nextBlue = new pos(movingBlue.r+dr[k], movingBlue.c+dc[k]);
				
				//1) 빨간색 움직이기
				if(board[nextRed.r][nextRed.c]=='.') {
					swap(movingRed, nextRed); // R과 .을 옮긴다
					moveRed = true;
					movingRed = nextRed; // 다음에는 이 위치에 있다.
					flag=true;
				}
				else if(board[nextRed.r][nextRed.c]=='O') {
					
					// 빨간게 O에 도착하면 성공, 그러나
					// blue 도 hole에 들어갈 수 있는지의 판단이 필요하다
					// 아직 이거는 미구현 상태. 그냥 빨간거 들어가게만 하면 return 되도록 구현했다.
					
					//배열 원상복구
					swap(movingRed, currRed);
					swap(movingBlue, currBlue);
					//값 update
					if(ans>depth) ans=depth;
					return;
					
				}
				else {
					moveRed = false; // 못 움직였음
				}
				
				//2) 파란색 움직이기
				if(board[nextBlue.r][nextBlue.c]=='.') {
					swap(movingBlue, nextBlue);
					moveBlue = true;
					movingBlue = nextBlue;
					flag=true;
				}
				else if(board[nextBlue.r][nextBlue.c]=='O') {
					// 있어선 안 될 일임 => 다음 for문으로 돌도록 원상 복구 시켜놔야 함
					swap(movingRed, currRed);
					swap(movingBlue, currBlue);
					continue;
				}
				else {
					moveBlue = false;
				}
			}// while end
			
			// depth 높여서 현재 이동한 곳이 다음 스타팅 포인트가 된다 
			// 변화가 있던 경우만 dfs 타게 만들자 continue
			if(flag) {
				dfs(depth+1, movingRed, movingBlue, k, reverse(k));	
			}
		} // k end
	}
	public static void swap(pos curr, pos next) {
		board[next.r][next.c]=board[curr.r][curr.c];
		board[curr.r][curr.c]='.';
	}
	public static int reverse(int k) {
		if(k==0) return 1;
		if(k==1) return 0;
		if(k==2) return 3;
		if(k==3) return 2;
		return -1;
	}
}
