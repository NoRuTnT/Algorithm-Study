// 1647. 도시 분할 계획
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 최소신장트리를 만들고 거기서 가장 길이가 긴 간선 하나만 끝으면 끝
public class Main {
	public static int N, M;
	public static int[][] edges;
	public static int[] p; // union 집합에 사용
	public static void main(String[] args) throws IOException {
		// 간선 배열을 이용하는 크루스칼 방식을 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new int[M+1][3];
		for(int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			edges[i][0] = a;
			edges[i][1] = b;
			edges[i][2] = w;
		}
		// 간선 가중치 기준으로 오름차순 정렬
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		p = new int[N];
		for(int i=0 ; i<N ; i++) {
			p[i] = i; // makeset 실행
		}
		int maxVal = -1; // 완성된 최소신장트리에서 빼야되는 가장 큰 간선 가중치 값
		int idx = 0;
		int pick = 0;
		int ans = 0;
		while(true) {
			int[] tmp = edges[idx];
			int px = tmp[0];
			int py = tmp[1];
			if(findset(px)==findset(py)) {
				idx++;
				continue;
			}
			union(px, py);
			pick++;
			ans += tmp[2];
			maxVal = Math.max(maxVal, tmp[2]);
			idx++;
			if(pick == N-1) break;
			// N-1 개의 간선이 뽑히면 끝남
		}
		System.out.println(ans-maxVal);
	}
	public static void union(int x, int y) {
		p[findset(x)] = findset(y);
	}
	public static int findset(int x) {
		if(p[x]!=x)
			p[x] = findset(p[x]);
		return p[x];
	}
}
