// 12851. 숨바꼭질 2
// 미완성. BFS로 풀어내려 시도함.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int K, N;
	public static int[][] visited;
	public static class Pos{
		int len;
		int time;
		public Pos(int len, int time) {
			this.len = len;
			this.time = time;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		if(N>K) {
			// 수빈이가 동생보다 앞에 있으면 -1로만 구성된
			// 1가지 방법밖에 없으므로 아래를 출력하고 프로그램 종료
			System.out.println(N-K);
			System.out.println(1);
			return;
		}
		visited = new int[2][100001];
		// 0행 : 방문체크(1로)
		// 1행 : 방문횟수
		int minTime = Integer.MAX_VALUE;
		int cases = 0;
		bfs();
		for(int i=0 ; i<100001 ; i++) {
			if(visited[0][i]==1) {
				minTime=i;
				cases=visited[1][i];
			}
		}
		System.out.println(minTime);
		System.out.println(cases);
	}
	public static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(N, 0));
		while(!q.isEmpty()) {
			Pos p = q.remove();
			if(p.len==K) {
				visited[0][K]=1;
				visited[1][K]++;
			}
			int np1 = p.len-1;
			int np2 = p.len+1;
			int np3 = p.len*2;
			if(np1>=0 && np1 <= 100000) {
				q.add(new Pos(np1, p.time+1));
			}
			if(np2>=0 && np2 <= 100000) {
				q.add(new Pos(np2, p.time+1));
			}
			if(np3>=0 && np3 <= 100000) {
				q.add(new Pos(np3, p.time+1));
			}
		}
	
	}
}
