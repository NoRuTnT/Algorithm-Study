import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] member;
	static int[][] node;
	static int[] arrA;
	static int[] arrB;
	static boolean[] arrBchk;
	static boolean[] visitedA;
	static boolean[] visitedB;
	static int memberA, memberB;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		member = new int[N + 1];
		node = new int[N + 1][N + 1];
		arrBchk = new boolean[N + 1];
		result = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			member[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				node[i][Integer.parseInt(st.nextToken())] = 1;
			}
		}

		for (int i = 1; i < N; i++) {
			arrA = new int[i];
			arrB = new int[N - i];
			comb(0, 1, i);
		}
		if(result == Math.abs(Integer.MAX_VALUE)) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}

	}

	private static void comb(int depth, int start, int D) {
		if (depth == D) {
			int idx = 0;
			memberA = 0;
			memberB = 0;
			for (int i = 0; i < N; i++) {
				if (!arrBchk[i + 1]) {
					arrB[idx++] = i + 1;
				}
			}
			visitedA = new boolean[N + 1];
			visitedB = new boolean[N + 1];
			dfsA(arrA[0]);
			for (int j : arrA) {
				if (!visitedA[j]) {
					return;
				}
			}
			dfsB(arrB[0]);
			for (int j : arrB) {
				if (!visitedB[j]) {
					return;
				}
			}
			result = Math.min(result, Math.abs(memberA - memberB));
			return;
		}
		for (int i = start; i <= N - D + depth; i++) {
			arrA[depth] = i;
			arrBchk[i] = true;
			comb(depth + 1, i + 1, D);
			arrBchk[i] = false;
		}
	}

	private static void dfsA(int v) {
		if (visitedA[v]) {
			return;
		}
		visitedA[v] = true;
		memberA += member[v];
		for (int i = 1; i < arrA.length; i++) {
			if (node[v][arrA[i]] == 1 && !visitedA[arrA[i]]) {
				dfsA(arrA[i]);
			}
		}
	}

	private static void dfsB(int v) {
		if (visitedB[v]) {
			return;
		}
		visitedB[v] = true;
		memberB += member[v];
		for (int i = 1; i < arrB.length; i++) {
			if (node[v][arrB[i]] == 1 && !visitedB[arrB[i]]) {
				dfsB(arrB[i]);
			}
		}
	}
}
