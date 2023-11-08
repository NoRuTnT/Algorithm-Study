// 23309. 철도 공사
// HashMap을 사용한 풀이는 시간초과 발생함
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] prev = new int[1000001];
	public static int[] next = new int[1000001];
	public static int N, M;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] tmp = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tmp[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1 ; i<N-1 ; i++) {
			prev[tmp[i]] = tmp[i-1];
			next[tmp[i]] = tmp[i+1];
		}
		prev[tmp[0]] = tmp[N-1];
		next[tmp[0]] = tmp[1];
		prev[tmp[N-1]] = tmp[N-2];
		next[tmp[N-1]] = tmp[0];
		for(int i=0 ; i<M ; i++) {
			String str = br.readLine();
			func(str);
		}
		System.out.println(sb.toString());
	}

	public static void func(String str) {
		String[] token = str.split(" ");
		int i = Integer.parseInt(token[1]);
		int n=0, p=0, j=0;
		if (token.length > 2)
			j = Integer.parseInt(token[2]);
		switch (token[0]) {
		case "BN":
			n = next[i];
			sb.append(n).append("\n");
			next[i] = j;
			next[j] = n;
			prev[n] = j;
			prev[j] = i;
			break;
		case "BP":
			p = prev[i];
			sb.append(p).append("\n");
			next[p] = j;
			next[j] = i;
			prev[j] = p;
			prev[i] = j;
			break;
		case "CP":
			p = prev[i];
			int pp = prev[p];
			sb.append(p).append("\n");
			next[pp] = i;
			prev[i] = pp;
			break;
		case "CN":
			n = next[i];
			int nn = next[n];
			sb.append(n).append("\n");
			next[i] = nn;
			prev[nn] = i;
			break;
		}
	}
}
