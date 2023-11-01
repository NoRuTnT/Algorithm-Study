// BFS, 성진 코드 참조

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 김태윤_13460 {
	//구슬 1개의 위치
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	//구슬 2개 정보
	static class Pair{
		Pos red;
		Pos blue;
		int dir; // 이전 이동의 방향 => 탐색할 필요 없음
		int reverse; // 이전 이동의 반대 방향 => 탐색할 필요 없음
		int move; // 이동 횟수
		Pair (Pos red, Pos blue, int dir, int reverse, int move){
			this.red=red;
			this.blue=blue;
			this.dir=dir;
			this.reverse=reverse;
			this.move = move;
		}
	}
	static char[][] board;
	static int n;
	static int m;
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	static Pos red;
	static Pos blue;
	static Pos end;
	static int ans = 11;
	public static void main(String[] args) {
		input();
		bfs();
		output();
	}
	public static void input() {
		// board에 입력받고, red, blue, end의 위치 갱신
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		board = new char[n][m];
		for(int i=0;i<n;i++) {
			board[i]=sc.next().toCharArray();
			for(int j=0;j<m;j++) {
				if(board[i][j]=='R') red = new Pos(i,j);
				else if(board[i][j]=='B') blue = new Pos(i,j);
				else if(board[i][j]=='O') end = new Pos(i,j);
			}
		}
		sc.close();
	}
	public static void output() {
		if(ans==11) System.out.println(-1);
		else System.out.println(ans);
	}
	public static void bfs() {
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(red, blue, 4, 4, 0));
		while(!queue.isEmpty() || ans < 11) { // 답이 나오면 더 이상 필요가 없다.
			Pair curr = queue.poll();
			if(curr.move>10) break; // 이동 횟수가 10회 넘어가면 break 
			for(int k=0;k<4;k++) {
				if(curr.dir==k || curr.reverse==k) continue; // 탐색할 필요 없는 것들
				boolean moveRed=true, moveBlue=true; // false가 될 때까지 이동한다
				boolean hole=false; // hole에 들어갈 수 있는 경우
				Pos currRed=new Pos(curr.red.r,curr.red.c);
				Pos currBlue=new Pos(curr.blue.r,curr.blue.c);
				//1) 빨간색 일단 이동
				while(moveRed) {
					if(currRed.r+dr[k]==currBlue.r && currRed.c+dc[k]==currBlue.c) {
						moveRed=false;
					}
					else if(board[currRed.r+dr[k]][currRed.c+dc[k]]!='#') {
						currRed.r+=dr[k];
						currRed.c+=dc[k];
						if(board[currRed.r][currRed.c]=='O') {
							hole = true;
							moveRed=false; // O에 도달한 경우 더 이상 이동 X
							currRed.r=-1; //2번에서 이동 제약조건 안 걸리도록
						}
					}
					else moveRed=false; // #인 경우
				}
				
				//2) 파란색 이동
				while(moveBlue) {
					if(currBlue.r+dr[k]==currRed.r && currBlue.c+dc[k]==currRed.c) {
						moveBlue=false;
					}
					else if(board[currBlue.r+dr[k]][currBlue.c+dc[k]]!='#') {
						currBlue.r+=dr[k];
						currBlue.c+=dc[k];
						if(board[currBlue.r][currBlue.c]=='O') {
							hole=false;
							moveBlue=false;
							//이번에는 O에 딱 세워놓기 위해서 currBlue 값 변경 안한다.
						}
					}
					else moveBlue = false; // #인 경우
				}
				
				//3) 빨간색 다시 이동
				moveRed=true;
				while(!hole && moveRed) {
					if(currRed.r==-1) break; // 골인 성공한 경우
					if(currRed.r+dr[k]==currBlue.r && currRed.c+dc[k]==currBlue.c) {
						moveRed=false;
					}
					else if(board[currRed.r+dr[k]][currRed.c+dc[k]]!='#') {
						currRed.r+=dr[k];
						currRed.c+=dc[k];
						if(board[currRed.r][currRed.c]=='O') {
							hole = true;
							moveRed=false; // O에 도달한 경우 더 이상 이동 X
							currRed.r=-1; //2번에서 이동 제약조건 안 걸리도록
						}
					}
					else moveRed=false; // #인 경우
				}
				if(hole) {
					ans=curr.move+1; // 현재 저장된거에서 한번 더 이동
					return; // 가장 최근에 꺼낸거일테니까 더 이상 탐색할 필요가 없다.
				}
				if(board[currBlue.r][currBlue.c]=='O') continue; 
				// 파란게 홀에 들어간 경우 queue에 넣을 필요 없다
				
				queue.offer(new Pair(currRed, currBlue, k, reverse(k), curr.move+1));
			}//for k end
		}
	}
	public static int reverse(int dir) {
		if(dir==0) return 1;
		if(dir==1) return 0;
		if(dir==2) return 3;
		if(dir==3) return 2;
		return -1;
	}
}
