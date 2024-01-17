// 17143. 낚시왕
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int R, C, M, ans;
	public static int[] dr = {-1, 1, 0, 0}; // 1부터시작하는 인데스 보정 필요
	public static int[] dc = {0, 0, 1, -1}; // 위, 아래, 오른쪽, 왼쪽
	static class Fish{
		int r;
		int c;
		int s;
		int d;
		int z;
		public Fish(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}
	public static List[][] arr;
	public static List[][] next;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new List[R][C];
		
		for(int i=0 ; i<R ; i++) {
			arr[i] = new List[C];
			for(int j=0 ; j<C ; j++) {
				arr[i][j] = new ArrayList<Fish>();
			}
		}
		for(int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); // r-1
			int c = Integer.parseInt(st.nextToken()); // c-1
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			arr[r-1][c-1].add(new Fish(r-1, c-1, s, d-1, z)); // d인덱스 보정
		}
		for(int i=0 ; i<C ; i++) {
			catchFish(i);
			move();
		}
		System.out.println(ans);
	}
	public static void catchFish(int c) {
		for(int i=0 ; i<R ; i++) {
			if(arr[i][c].size()>0) {
				ans+=((Fish)(arr[i][c].get(0))).z;// 점수 올려
//				System.out.println(((Fish)(arr[i][c].get(0))).z+"크기 물고기 잡았지");
				arr[i][c].remove(0);// 상어 제거
				return;
			}
		}
	}
	public static void move() {
		next = new List[R][C];
		for(int i=0 ; i<R ; i++) {
			next[i] = new List[C];
			for(int j=0 ; j<C ; j++) {
				next[i][j] = new ArrayList<Fish>();
			}
		}
		// 각각 이동 시키고
		for(int i=0 ; i<R ; i++) {
			for(int j=0 ; j<C ; j++) {
				if(arr[i][j].size()!=0) moveFish((Fish)(arr[i][j].get(0)));
			}
		}
		for(int i=0 ; i<R ; i++) {
			for(int j=0 ; j<C ; j++) {
				if(next[i][j].size()>1) {
					Fish f = (Fish)(next[i][j].get(0));
					// 안돼면 최대/최소힙 최적화
					for(int k=1 ; k<next[i][j].size() ; k++) {
						if(((Fish)(next[i][j].get(k))).z>f.z)
							f = (Fish)(next[i][j].get(k));
					}
					next[i][j].clear();
					next[i][j].add(f);
				}
			}
		}
		arr = next;
	}
	public static void moveFish(Fish f) {
		Fish currFish = (Fish)arr[f.r][f.c].remove(0);
		int K = 0;
		// 0123 위 아래 오른쪽 왼쪽
		if(currFish.d<=1) K = R;// 0or1 이건 위아래
		else K = C;
		int bound = currFish.s%(2*(K-1)); // 나누기로 한번에 계산해야 1초를 넘지 않음
		int nr = currFish.r;
		int nc = currFish.c;
		for(int i=0 ; i<bound ; i++) {
			if(nr+dr[currFish.d]<0 || nr+dr[currFish.d]>=R || nc+dc[currFish.d]<0 || nc+dc[currFish.d]>=C) {
				currFish.d=changePos(currFish.d);
			}
			nr += dr[currFish.d];
			nc += dc[currFish.d];
		}
		currFish.r = nr;
		currFish.c = nc;
		next[nr][nc].add(currFish);
	}
	public static int changePos(int d) {
		if(d==0) return 1;
		if(d==1) return 0;
		if(d==2) return 3;
		return 2;
	}
}
