// 2447. 별 찍기 - 10
import java.util.Scanner;

public class Main {
	static int N;
	static char[][]arr;
	static char[][]b= {
			{'*','*','*'},
			{'*',' ','*'},
			{'*','*','*'},
	};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		arr=new char[N][N];
		dfs(N,0,0);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void dfs(int depth,int r,int c) {
		if(depth==3) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					arr[r+i][c+j]=b[i][j];
				}
			}
			return;
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(i==1&&j==1) {
					skip(depth/3,r+i*depth/3,c+j*depth/3);
				}else {
					dfs(depth/3,r+i*depth/3,c+j*depth/3);
				}
			}
		}
	}
	public static void skip(int depth,int r,int c) {
		for(int i=0;i<depth;i++) {
			for(int j=0;j<depth;j++) {
				arr[r+i][c+j]=' ';
			}
		}
	}
}
