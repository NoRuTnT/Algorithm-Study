// 14500. 테트로미노
// 완전탐색
import java.util.Scanner;

public class Main {
	//일자형 블록
	public static int[][][] typeA = {
			{{0, 0, 0, 0},{0, 1, 2, 3}},
			{{0, 1, 2, 3},{0, 0, 0, 0}},
	}; // dr/dc
	// 정사각형 블록
	public static int[][][] typeB = {
			{{0, 0, 1, 1},{0, 1, 0, 1}}
	};
	// ㄴ자 블록
	public static int[][][] typeC = {
			{{0, 1, 2, 2},{0, 0, 0, 1}},
			{{0, 0, 0, 1},{0, 1, 2, 0}},
			{{0, 0, 1, 2},{0, 1, 1, 1}},
			{{0, 1, 1, 1},{2, 0, 1, 2}},
			{{0, 1, 2, 2},{1, 1, 0, 1}},
			{{0, 1, 1, 1},{0, 0, 1, 2}},
			{{0, 0, 1, 2},{0, 1, 0, 0}},
			{{0, 0, 0, 1},{0, 1, 2, 2}}
	};
	// ㄹ자 블록
	public static int[][][] typeD = {
			{{0, 1, 1, 2},{0, 0, 1, 1}},
			{{1, 1, 0, 0},{0, 1, 1, 2}},
			{{0, 1, 1, 2},{1, 0, 1, 0}},
			{{0, 0, 1, 1},{0, 1, 1, 2}}
	};
	// ㅗ자 블록
	public static int[][][] typeE = {
			{{0, 1, 1, 1},{1, 0, 1, 2}},
			{{0, 1, 1, 2},{0, 0, 1, 0}},
			{{0, 0, 0, 1},{0, 1, 2, 1}},
			{{0, 1, 1, 2},{1, 0, 1, 1}}
	};
	public static int[][][][] blocks = {typeA, typeB, typeC, typeD, typeE};
	public static int N, M, ans;
	public static int[][] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		ans = 0;
		for(int i=0; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		for(int i=0; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				func(i, j);
			}
		}
		System.out.println(ans);
	} // main
	public static void func(int r, int c) {
		for(int i=0 ; i<blocks.length ; i++) {
			label:
			for(int j=0 ; j<blocks[i].length ; j++) {
				int tmp = 0;
				for(int d=0 ; d<4 ; d++) {
          // i : 블록의 종류 | j : 블록의 형태(회전이나 대칭)
					int nr = r + blocks[i][j][0][d]; // 0번 : dr
					int nc = c + blocks[i][j][1][d]; // 1번 : dc
					if(nr<0 || nr>=N || nc<0 || nc>=M)
						continue label;
					else
						tmp+=arr[nr][nc];
				}
				ans = Math.max(ans, tmp);
			}
		}
	}
}
