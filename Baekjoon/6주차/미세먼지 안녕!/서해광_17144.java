// 17144. 미세먼지 안녕!
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int R, C, T, ans;
	public static int[][] arr, tmp;
	public static int[][] cleaner = new int[2][2];
	// 3시부터 시계방향
	public static int[] dr = {0, 1, 0, -1};
	public static int[] dc = {1, 0, -1, 0};
	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static List<Node> up, down;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		arr = new int[R][C];
		int idx = 0; // 공기청정기 위치를 저장할 곳을 가리키는 카운터
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == -1) {
					cleaner[idx][0] = i;
					cleaner[idx][1] = j;
					idx++; // (0->1) 2번째 -1이 들어갈 곳으로
				}
			}
		}
		
		// 공기청정기 위쪽은 반시계, 아래쪽은 시계방향
		up = new ArrayList<>();
		down = new ArrayList<>();
		// 델타 탐색 배열을 쓰기보다는 line 마다 돌아가면서 직접 추가
		// up
		for(int i=0 ; i<cleaner[0][0] ; i++) {
			up.add(new Node(i, 0));
		}
		for(int j=0 ; j<C-1 ; j++) {
			up.add(new Node(cleaner[0][0], j));
		}
		for(int i=cleaner[0][0] ; i>0 ; i--) {
			up.add(new Node(i, C-1));
		}
		for(int j=C-1 ; j>0 ; j--) {
			up.add(new Node(0, j));
		}
		// down
		for(int j=0 ; j<C-1 ; j++) {
			down.add(new Node(cleaner[1][0], j));
		}
		for(int i=cleaner[1][0] ; i<R-1 ; i++) {
			down.add(new Node(i, C-1));
		}
		for(int j=C-1 ; j>0 ; j--) {
			down.add(new Node(R-1, j));
		}
		for(int i=R-1 ; i>cleaner[1][0] ; i--) {
			down.add(new Node(i, 0));
		}
		for(int t=0 ; t<T ; t++) {
			totalSpread();
			clean();
		}
		// 답을 구함
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != -1)
					ans += arr[i][j];
			}
		}
		System.out.println(ans);
	}

	public static void totalSpread() {
		tmp = new int[R][C]; // 확산 결과를 뿌려줄 배열
		for(int i=0 ; i<R ; i++) {
			for(int j=0 ; j<C ; j++) {
				if(arr[i][j]!=-1) {
					spread(i, j);
				}
			}
		}
		for(int i=0 ; i<R ; i++) {
			for(int j=0 ; j<C ; j++) {
				arr[i][j] = tmp[i][j];
			}
		}
		// copy 해주면서 공기청정기 위치도 -1로
		arr[cleaner[0][0]][cleaner[0][1]] = -1;
		arr[cleaner[1][0]][cleaner[1][1]] = -1;
	}
	public static void spread(int r, int c) {
		int cnt = 0; // 확산이 일어난 방향 개수
		for(int d=0 ; d<4 ; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=R || nc<0 || nc>=C || arr[nr][nc]==-1)
				continue;
			tmp[nr][nc] += arr[r][c]/5;
			cnt++;
		}
		tmp[r][c] += arr[r][c] - cnt*(arr[r][c]/5);
	}

	public static void clean() {
		// 적어도 2칸 이상 떨어져 있다는 점을 잘 활용
		int tmp = arr[up.get(up.size()-1).r][up.get(up.size()-1).c];
		for(int i=up.size()-1 ; i>0 ; i--) {
			arr[up.get(i).r][up.get(i).c] = arr[up.get(i-1).r][up.get(i-1).c];
		}
		arr[up.get(0).r][up.get(0).c] = tmp;
		arr[cleaner[0][0]][cleaner[0][1]] = -1;
		arr[cleaner[0][0]][cleaner[0][1]+1] = 0;
		// 아래
		tmp = arr[down.get(down.size()-1).r][down.get(down.size()-1).c];
		for(int i=down.size()-1 ; i>0 ; i--) {
			arr[down.get(i).r][down.get(i).c] = arr[down.get(i-1).r][down.get(i-1).c];
		}
		arr[down.get(0).r][down.get(0).c] = tmp;
		arr[cleaner[1][0]][cleaner[1][1]] = -1;
		arr[cleaner[1][0]][cleaner[1][1]+1] = 0;
		
	}
}
