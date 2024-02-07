// 2533. 사회망 서비스(SNS)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static List<Integer>[] graph;
    public static int[][] dp;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a); // 양방향 그래프로 저장
        }
        dfs(1, -1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int currNode, int prevNode) {
        visited[currNode] = true;
        dp[currNode][0] = 0; // 현재 노드를 얼리어답터로 선택하지 않은 경우
        dp[currNode][1] = 1; // 현재 노드를 얼리어답터로 선택한 경우
        for (int nextNode : graph[currNode]) {
            if (!visited[nextNode]) {
                dfs(nextNode, currNode); // 다음 노드를 탐색
                dp[currNode][0] += dp[nextNode][1]; // 현재 노드를 얼리어답터로 선택x
                dp[currNode][1] += Math.min(dp[nextNode][0], dp[nextNode][1]); // 현재 노드를 얼리어답터로 선택o 둘중 최소값
            }
        }
    }
}
