package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b10830 {
	static int n;
	static int[][] res_matrix;
	
	public static int[][] matrix_dup(int[][] matrix,long b) {
		int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1; //항등행렬로 초기화
        }
        // b를 이진수로 변환, 각비트를 확인한다고 생각 1인경우 result에 matrix를 곱하고 매자리수마다 matrix를 곱해서 자릿수마다 matrix업데이트
        while(b>0) {
            if(b%2 == 1) { 
                result = matrix_mul(result, matrix);
            }
            matrix = matrix_mul(matrix, matrix);
            b/=2;
        }

        return result;
	}  
	public static int[][] matrix_mul(int[][] matrix1, int[][] matrix2) {
	    int[][] result = new int[n][n];
	    for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
	                result[i][j] += matrix1[i][k]*matrix2[k][j];
	                result[i][j] %= 1000; 
	            }
	        }
	    }
	    return result;
	}  
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		int[][] matrix = new int [n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken())%1000;
			}
		}	    
    	res_matrix = matrix_dup(matrix, b);
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(res_matrix[i][j]+ " ");
            }
            System.out.println();
        }
	}
}
