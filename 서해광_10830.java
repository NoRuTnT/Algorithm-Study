// 10830. 행렬 제곱

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long B = sc.nextLong();
		int[][] A = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				A[i][j] = sc.nextInt();
			}
		}
		int[][] R = pow(A, B);
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				System.out.printf("%d ",R[i][j]);
			}
			System.out.printf("\n");
		}
	}
	public static int[][] pow (int[][] mat, long C){
		int N = mat.length;
		if(C==1) {
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<N ; j++) {
					mat[i][j]%=1000; // 반례: 1000으로 구성된 행렬을 입력받았을 때 1승일 경우 나머지가 1000이 아닌 0으로 만들어야 함
				}
			}
			return mat;
		}
		if(C==2) {
			int[][] retMat = new int[N][N];
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<N ; j++) {
					for(int k=0 ; k<N ; k++) {
						retMat[i][j] += mat[i][k]*mat[k][j];
					}
					retMat[i][j]%=1000;
				}
			}
			return retMat;
		}
		if(C%2==0) {
			return pow(pow(mat, C/2), 2);
		}else {
			int[][] mat1 = new int[N][N];
			int[][] mat2 = pow(pow(mat,(C-1)/2), 2);
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<N ; j++) {
					for(int k=0 ; k<N ; k++) {
						mat1[i][j] += mat[i][k]*mat2[k][j];
					}
					mat1[i][j]%=1000;
				}
			}
			return mat1;
		}
	}
}
