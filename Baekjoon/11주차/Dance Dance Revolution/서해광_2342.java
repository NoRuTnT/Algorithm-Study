// 2342. Dance Dance Revolution
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int[][][] dp;
	public static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(true) {
			int n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			list.add(n);
		}
		dp = new int[100001][5][5];
		for(int i=0 ; i<100001 ; i++) {
			for(int j=0 ; j<5 ; j++) {
				for(int k=0 ; k<5 ; k++) {
					dp[i][j][k]=-1;
				}
			}
		}
		System.out.println(func(0, 0, 0));
	}
	public static int getPow(int cur, int dst) {
		if(cur==0) return 2;
		if(cur==dst) return 1;
		if(Math.abs(cur-dst)==2) return 4;
		return 3;
	}
	public static int func(int i, int L, int R) {
		if(i==list.size()) return 0;
		if(dp[i][L][R] != -1) return dp[i][L][R];
		dp[i][L][R] = Integer.MAX_VALUE;
		dp[i][L][R] = Math.min(dp[i][L][R], func(i+1, list.get(i), R)+getPow(L, list.get(i)));
		dp[i][L][R] = Math.min(dp[i][L][R], func(i+1, L, list.get(i))+getPow(R, list.get(i)));
		return dp[i][L][R];
	}
}
