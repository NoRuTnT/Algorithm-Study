// 13549. 숨바꼭질 3
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int N, K;
	static class Pos{
		int t;
		int d;
		public Pos(int t, int d) {
			this.t = t;
			this.d = d;
		}
		
	}
	public static int[] arr = new int[100001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		for(int i=0 ; i<=100000 ; i++) {
			arr[i] = Integer.MAX_VALUE;
		}
		bfs(new Pos(0, N));
		System.out.println(arr[K]);
	}
	public static void bfs(Pos p) {
		Queue<Pos> q = new LinkedList<>();
		q.add(p);
		while(!q.isEmpty()) {
			Pos pp = q.remove();
			if(arr[pp.d]<=pp.t) continue; // 이 지점까지 이동하는 최소 경로가 존재
			arr[pp.d] = pp.t;
			if(pp.d == K) continue;
			if(pp.d*2<=100000 && arr[pp.d*2]>pp.t)
				q.add(new Pos(pp.t, pp.d*2));
			if(pp.d-1>=0 && arr[pp.d-1]>pp.t+1)
				q.add(new Pos(pp.t+1, pp.d-1));
			if(pp.d+1<=100000 && arr[pp.d+1]>pp.t+1)
				q.add(new Pos(pp.t+1, pp.d+1));
		}
	}
}
