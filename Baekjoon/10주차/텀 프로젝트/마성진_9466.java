import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int n;
	public static int[] classRoom;
	public static boolean[] visited, finished;
	public static int teamCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			classRoom = new int[n + 1];
			visited = new boolean[n + 1];
			finished = new boolean[n + 1];
			teamCount = 0;

			for (int i = 1; i <= n; i++) {
				classRoom[i] = Integer.parseInt(st.nextToken());
				if (i == classRoom[i]) {
					teamCount++;
					finished[i] = true;
				}
			}

			for (int i = 1; i <= n; i++) {
				if (!finished[i]) {
					dfs(i);
				}
			}
			System.out.println(n - teamCount);
		}
	}

	public static void dfs(int v) {
		// 이미 방문
		if (visited[v]) {
			finished[v] = true; // 방문했으면 팀 편성 완료
			teamCount++;
		} else {
			// 방문한 적이 없을 때 방문 처리
			visited[v] = true;
		}
		// 내가 선택한 학생이 팀 결성이 안되어 있을 때
		if (!finished[classRoom[v]]) {
			dfs(classRoom[v]);
		}
		// 해당 학생 방문 체크 해제, 팀 완성 여부 체크
		visited[v] = false;
		finished[v] = true;
	}

}
