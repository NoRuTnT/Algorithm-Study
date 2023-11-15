// 12869. 뮤탈리스크
import java.util.Scanner;

public class Main {
	public static int N;
	public static int[] arr;
	public static int[] dp1;
	public static int[][] dp2;
	public static int[][][] dp3;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for(int i=0 ; i<N ; i++) {
			arr[i] = sc.nextInt();
		}
		if(N==1) dp1();
		else if(N==2) dp2();
		else dp3();
	}
	public static void dp1() {
		dp1 = new int[61];
		for(int i=1 ; i<=9 ; i++) {
			dp1[i] = 1;
		}
		for(int i=10 ; i<=60 ; i++) {
			if(dp1[i]==0) {
				dp1[i] = dp1[i-9]+1;
			}
		}
		System.out.println(dp1[arr[0]]);
	}
	public static void dp2() {
		dp2 = new int[61][61];
		// 초기값 설정
		for(int i=0 ; i<=60 ; i++) {
			for(int j=0 ; j<=60 ; j++) {
				if(dp2[i][j]==0 && !(i==0&&j==0)) {
					dp2[i][j] = Math.min(dp2[cal(i-9)][cal(j-3)], dp2[cal(i-3)][cal(j-9)])+1;
					dp2[j][i] = dp2[i][j];
				}
			}
		}
		System.out.println(dp2[arr[0]][arr[1]]);
	}
	public static void dp3() {
		dp3 = new int[61][61][61];
		for(int i=0 ; i<=60 ; i++) {
			for(int j=0 ; j<=60 ; j++) {
				for(int k=0 ; k<=60 ; k++) {
					if(dp3[i][j][k]==0 && !(i==0&&j==0&&k==0)) {
						dp3[i][j][k] = Math.min(dp3[cal(i-1)][cal(j-3)][cal(k-9)], dp3[cal(i-1)][cal(j-9)][cal(k-3)])+1;
						dp3[i][j][k] = Math.min(dp3[i][j][k], dp3[cal(i-3)][cal(j-1)][cal(k-9)]+1);
						dp3[i][j][k] = Math.min(dp3[i][j][k], dp3[cal(i-3)][cal(j-9)][cal(k-1)]+1);
						dp3[i][j][k] = Math.min(dp3[i][j][k], dp3[cal(i-9)][cal(j-1)][cal(k-3)]+1);
						dp3[i][j][k] = Math.min(dp3[i][j][k], dp3[cal(i-9)][cal(j-3)][cal(k-1)]+1);
						
						dp3[k][j][i] = dp3[k][i][j] = dp3[j][k][i] = dp3[j][i][k] = dp3[i][k][j] = dp3[i][j][k];
						
					}
				}
			}
		}
		System.out.println(dp3[arr[0]][arr[1]][arr[2]]);
	}
  // HP가 음수가 될 경우 배열의 인덱스 값을 0으로 표시하게 하는 함수
	public static int cal(int num) {
		return num>0?num:0;
	}
}
