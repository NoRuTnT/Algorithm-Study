// 2239. 스도쿠
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int depth;
	public static boolean finished;
	public static int[][] board = new int[9][9];
	public static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		for(int i=0 ; i<9 ; i++) {
			String str = sc.next();
			for(int j=0 ; j<9 ; j++) {
				board[i][j]=str.charAt(j)-'0';
				if(board[i][j]==0) {
					depth++;
				}
			}
		}
		dfs(0);
		System.out.println(sb);
	} // main
	public static void dfs(int idx) {
		if(finished) return;
		if(idx==depth) {
			for(int i=0 ; i<9 ; i++) {
				for(int j=0 ; j<9 ; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			finished = true;
			return;
		}
		
		for(int i=0 ; i<9 ; i++) {
			for(int j=0 ; j<9 ; j++) {
				if(board[i][j]==0) {
					for(int k=1 ; k<=9 ; k++) {
						if(chk(i, j, k, board)) {
							board[i][j] = k;
							dfs(idx+1);
							board[i][j] = 0;
						}
					}
					if(board[i][j]==0) return;
				}
			}
		}

	}
	
	public static boolean chk(int r, int c, int num, int[][] arr) {
		for(int i=0 ; i<9 ; i++) {
			if(arr[r][i]==num || arr[i][c]==num) return false;
		}
		int R = (r/3)*3;
		int C = (c/3)*3;
		for(int i=0 ; i<3 ; i++) {
			for(int j=0 ; j<3 ; j++) {
				if(arr[R+i][C+j]==num) return false;
			}
		}
		return true;
	}
}
