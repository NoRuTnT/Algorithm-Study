package Week22;

import java.util.Scanner;

public class 김태윤_2447 {
	static char[][] ans;
	static int n;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		ans=new char[n][n];
		recursion(0, 0, n, false);
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++){
			for(int j=0;j<n; j++){
				sb.append(ans[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void recursion(int r, int c, int len, boolean isBlank){
		if(isBlank) {
			for(int i=r; i<r+len; i++){
				for(int j=c; j<c+len; j++){
					ans[i][j]=' ';
				}
			}
			return;
		}
		if(len==1) {
			ans[r][c]='*';
			return;
		}
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(i==1 && j==1) recursion(r+len/3, c+len/3, len/3, true);
				else recursion(r+i*(len/3), c+j*(len/3), len/3, false);
			}
		}
	}
}
