import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class fish {
	int r;
	int c;
	int size;

	fish() {}

	fish(int r, int c, int size) {
		this.r = r;
		this.c = c;
		this.size = size;
	}
}

public class 김태윤_16236 {
	static int n;
	static int[][] arr;
	static int[] fishcount = new int[7];
	static int eat = 0;
	static int cnt = 0;
	static int eatable;
	static LinkedList<fish> queue = new LinkedList<>();

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					queue.add(new fish(i, j, 2));
					arr[i][j]=0;
				} else {
					fishcount[arr[i][j]]++;
				}
			}
		}
		eatable=fishcount[1];
	}
	public static void eatFish(LinkedList<fish> list) {
		fish ans=list.poll();
		while(!list.isEmpty()) {
			if(ans.r>list.peek().r) {
				ans=list.poll();
			}
			else if(ans.r==list.peek().r && ans.c>list.peek().c) {
				ans=list.poll();
			}
			else list.remove();
		} // 다음 이동할 곳 정해짐
		queue.clear();
		fishcount[arr[ans.r][ans.c]]--;
		eatable--;
		eat++;
		arr[ans.r][ans.c]=0;
		if(eat==ans.size) {
			queue.add(new fish(ans.r, ans.c, ans.size+1));
			if(queue.peek().size<=7) eatable+=fishcount[queue.peek().size-1];
			eat=0;
		}
		else queue.add(ans);
	}

	public static void bfs() {
		int[] dr = { -1, 0, 0, 1 };
		int[] dc = { 0, -1, 1, 0 };
		boolean[][] check = new boolean[n][n];
		int moveCnt=-1;
		while (!queue.isEmpty()) {
			moveCnt++;
			int len = queue.size();
			if(eatable==0) break; // 먹을 수 있는 먹이가 있는지
			LinkedList<fish> tmp=new LinkedList<>();
			for (int i = 0; i < len; i++) {
				fish now = queue.poll();
				check[now.r][now.c] = true;
				for (int k = 0; k < 4; k++) {
					if (now.r + dr[k] < 0 || now.r + dr[k] >= n || now.c + dc[k] < 0 || now.c + dc[k] >= n || check[now.r + dr[k]][now.c + dc[k]]) continue;
					if (arr[now.r + dr[k]][now.c + dc[k]] <= now.size) {
						queue.add(new fish(now.r + dr[k], now.c + dc[k], now.size)); //이동할 수 있는 경우
						check[now.r + dr[k]][now.c + dc[k]] = true; // 이미 방문
						if (arr[now.r + dr[k]][now.c + dc[k]] > 0 && arr[now.r + dr[k]][now.c + dc[k]] < now.size) {
							tmp.add(new fish(now.r + dr[k], now.c + dc[k], now.size)); //먹을 수 있는 경우
						}
					}
				}
			}
			if(!tmp.isEmpty()) {
				eatFish(tmp);
				check=new boolean[n][n];
				cnt+=moveCnt+1;
				moveCnt=-1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		bfs();
		System.out.println(cnt);
	}
}