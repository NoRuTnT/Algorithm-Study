import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class pos {
	int r;
	int c;

	pos() {
	}

	pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class 김태윤_15686 {
	static int n;
	static int m;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static List<pos> home = new ArrayList<>();
	static List<pos> chicken = new ArrayList<>();

	public static void input() throws IOException { // 1) 치킨집 위치 저장
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					home.add(new pos(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new pos(i, j));
				}
			}
		}
	}

	public static void bfs(int[] deleteIdx) {

		int sum=0;
		for(int i=0;i<home.size();i++) {
			int len=Integer.MAX_VALUE;
			int pos=0;
			for(int j=0;j<chicken.size();j++) {
				if(pos<deleteIdx.length && j==deleteIdx[pos]) {
					pos++;
					continue;
				}
				if(len>Math.abs(home.get(i).r-chicken.get(j).r)+Math.abs(home.get(i).c-chicken.get(j).c)) {
					len=Math.abs(home.get(i).r-chicken.get(j).r)+Math.abs(home.get(i).c-chicken.get(j).c);
				}
			}
			sum+=len;
		}
		if(min>sum) min=sum;
	}

	public static void dfs(int depth, int fin, int idx, int[] deleteIdx) { // 2) 삭제할 치킨집 조합 생성
		if (depth == fin) {
			bfs(deleteIdx);
			return;
		}
		for (int i = idx; i < chicken.size(); i++) {
			deleteIdx[depth] = i;
			dfs(depth + 1, fin, i + 1, deleteIdx);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		dfs(0, chicken.size() - m, 0, new int[chicken.size() - m]);
		System.out.println(min);
	}
}
