// 16236. 아기 상어
// 먹은 칸은 빈칸으로
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int N, time;
	public static int[][] arr;
	public static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	public static int[] dc = { 0, 0, -1, 1 };
	public static int[] sh; // 상어

	static class Fish implements Comparable<Fish>{
		int r;
		int c;
		int size;
		int approach;

		public Fish(int r, int c, int size, int approach) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.approach = approach;
		}
		@Override
		public int compareTo(Fish o) {
			if(this.approach==o.approach) {
				if(this.r==o.r)
					return this.c-o.c;
				return this.r - o.r;
			}
			return this.approach-o.approach;
		}
	}

	public static List<Fish> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		list = new ArrayList<>();
		sh = new int[4]; // 상어 좌표+크기+먹은 물고기 수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 9) {
					sh[0] = i;
					sh[1] = j;
					sh[2] = 2; // 초기 아기상어 크기
					sh[3] = 0; // 먹은 물고기 수
				} else if (arr[i][j] > 0) {
					list.add(new Fish(i, j, arr[i][j], 0)); // 도달시간은 아직은 모름
				}
			}
		} // 입력 완료
		time = 0;
		while (!list.isEmpty()) {
			PriorityQueue<Fish> able = new PriorityQueue<>(); // 상어가 먹을 수 있는 물고기
			for (int i = 0; i < list.size(); i++) {
				if (sh[2] > list.get(i).size && find(list.get(i).r, list.get(i).c)!=-1) {
					// 크기가 작으면서 동시에 도달할 수 있는 공간
					int R = list.get(i).r;
					int C = list.get(i).c;
					list.get(i).approach = find(R, C);
					able.add(list.get(i));
				}
			}
			if (able.size() == 0) {
				// 못먹어요...
				break;
			}
			arr[sh[0]][sh[1]]=0; // 이전에 상어가 있던 자리는 0으로 
			Fish f = able.remove(); // 1순위 먹고
			arr[f.r][f.c]=0; // 좌표는 0으로 두고
			sh[3]++; // 먹은 물고기수 증가
			if(sh[2]==sh[3]) { // 레벨업
				sh[2]++;
				sh[3]=0;
			}
			sh[0] = f.r;
			sh[1] = f.c; // 상어의 좌표도 이동시킴
//			전체 리스트에서도 제거해 줘야 됨
			for(int i=0 ; i<list.size() ; i++) {
				if(list.get(i).r == sh[0] &&list.get(i).c == sh[1]) {
					list.remove(i);
					break;
				}
			}
			time+=f.approach;
		} // while
		System.out.println(time);
	}

	public static int find(int r, int c) {
		// 가능하다면 이동 거리를
		// 불가능하다면 -1을
		boolean[][] visited = new boolean[N][N];
		visited[sh[0]][sh[1]] = true;
		Queue<Fish> q = new LinkedList<>();
		q.add(new Fish(sh[0], sh[1], 0, 0));
		while (!q.isEmpty()) {
			Fish f = q.remove();
			if (f.r == r && f.c == c) {
				return f.approach;
			}
			for (int d = 0; d < 4; d++) {
				int nr = f.r + dr[d];
				int nc = f.c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || arr[nr][nc] > sh[2])
					continue;
				q.add(new Fish(nr, nc, 0, f.approach+1));
				visited[nr][nc] = true;
			}

		}
		return -1;
	}
}
