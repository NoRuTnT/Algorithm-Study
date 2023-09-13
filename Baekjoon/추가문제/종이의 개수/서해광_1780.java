// 1780. 종이의 개수

import java.util.Scanner;

public class Main {
	public static int[] num = new int[3]; // -1, 0, 1
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] mat = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		cut(mat);
		System.out.println(num[0]);
		System.out.println(num[1]);
		System.out.println(num[2]);

	}
	public static void cut(int[][] paper) {
		int N = paper.length;
		if(N==1) {
			num[paper[0][0]+1]++;
			return;
		}
		int firstNum = paper[0][0];
		boolean perfect = true;
		label:
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				if(paper[i][j]!=firstNum) {
					perfect = false;
					break label;
				}
			}
		}
		if(perfect) {
			num[paper[0][0]+1]++;
			return;
		}else {
			for(int i=0 ; i<N ; i+=N/3) {
				for(int j=0 ; j<N ; j+=N/3) {
					int[][] nPaper = new int[N/3][N/3];
					for(int k1=0 ; k1<N/3 ; k1++) {
						for(int k2=0 ; k2<N/3 ; k2++) {
							nPaper[k1][k2]=paper[i+k1][j+k2];
						}
					}
					cut(nPaper);
				}
			}
		}
	}
	
}
