package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class b16954 {
	static int[] dr = {0,1,0,-1,0,1,-1,-1,1};
	static int[] dc = {1,0,-1,0,0,1,-1,1,-1};
	static boolean[][] map;
	static boolean pos;
	public static void main(String[] args) throws IOException {
		map = new boolean[8][8];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<8;i++) {
			String[] str = br.readLine().split("");
			for(int j=0;j<8;j++) {
				if(str[j].equals("#")){
					map[i][j]=true;
				}else {
					map[i][j]=false;
				}
			}
		}//입력
		if(bfs(7,0)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	//맵이동
	private static void move_map() {
		for(int i=7;i>=0;i--) {
			for(int j=0;j<8;j++) {
				if(map[i][j]) {
					if(i<7) {
						map[i+1][j]=true;
					}
					map[i][j]=false;
				}
			}
		}
	}

	private static boolean bfs(int i, int j) {
		Queue<int[]>queue = new LinkedList<>();
		queue.offer(new int[] {i,j,0});//turn을 큐에 들고다님
		int turn =0;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(turn != now[2]) { //turn 변수 들고다니다가 둘이 다르게되면 다음턴으로 진행 
				move_map();
				turn++;
			}
			if(turn>=8 || now[0]==0 && now[1]==7) { //8턴이 지나 모든벽이 사라졌거나 목적지에 도달하면 true처리
				return true;
			}
			if(map[now[0]][now[1]]) { //캐릭터가 벽에부딫혔으면 쳐낸다
				continue;
			}
			int x = now[0];
			int y = now[1];	
			for(int d=0;d<9;d++) {
				int r = x+dr[d];
				int c = y+dc[d];
				if(r>=0 && r<8 && c>=0 && c<8 && !map[r][c]) {
					queue.offer(new int[] {r,c,now[2]+1});						
				}
			}
		}
		return false;
	}

}

