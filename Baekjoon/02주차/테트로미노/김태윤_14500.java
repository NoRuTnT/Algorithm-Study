import java.util.Scanner;

public class 김태윤_14500 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[][] arr=new int[n+5][m+5];
		for(int i=2; i<n+2;i++) {
			for(int j=2;j<m+2;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		int[][] horizon=new int[n+5][m+5];
		int[][] vertical = new int[n+5][m+5]; // 각각 가로 두칸, 세로 두칸 합. 왼쪽/위에 칸이 기준
		int[][] cross=new int[n+5][m+5]; //자기 자신을 중심으로 십자가 모양 (총 5칸, 나중에 1칸 빼는 거로 계산)
		for(int i=2;i<n+2;i++) {
			for(int j=2;j<m+2;j++) {
				horizon[i][j]=arr[i][j]+arr[i][j+1];
				vertical[i][j]=arr[i][j]+arr[i+1][j];
				cross[i][j]=arr[i][j]+arr[i-1][j]+arr[i][j+1]+arr[i+1][j]+arr[i][j-1];
			}
		}
		
		int max=-1;
		
		int[] dyH= {-1,-1,-1,0,1,1,1,0};
		int[] dxH= {-1,0,1,2,1,0,-1,-2};
		int[] dyV= {-2,-2,-1,0,1,1,0,-1};
		int[] dxV= {0,1,2,2,1,0,-1,-1};
		int[] dy= {0,1,0,-1};
		int[] dx= {1,0,-1,0};
		int[] dy1= {1,-1,-1,1};
		int[] dx1= {1,1,-1,-1,};
		for(int i=2;i<n+2;i++) {
			for(int j=2;j<m+2;j++) {
				//가로짝대기 두개를 중심으로 주변에 구축할 수 있는 모든 경우의 수
				for(int k=0;k<8;k++) {
					if(horizon[i][j]+horizon[i+dyH[k]][j+dxH[k]]>max) {
						max=horizon[i][j]+horizon[i+dyH[k]][j+dxH[k]];
					}
					if(horizon[i][j]+vertical[i+dyV[k]][j+dxV[k]]>max) {
						max=horizon[i][j]+vertical[i+dyV[k]][j+dxV[k]];
					}
				//세로로 쭉
				}
				if(vertical[i][j]+vertical[i-2][j]>max) {
					max=vertical[i][j]+vertical[i-2][j];
				}
				if(vertical[i][j]+vertical[i+2][j]>max) {
					max=vertical[i][j]+vertical[i+2][j];
				}
				//ㅜ 모양
				for(int k=0;k<4;k++) {
					if(cross[i][j]-arr[i+dy[k]][j+dx[k]]>max) {
						max=cross[i][j]-arr[i+dy[k]][j+dx[k]];
					}
					//번개모양 세워진거
					if(vertical[i][j]+vertical[i+dy1[k]][j+dx1[k]]>max) {
						max=vertical[i][j]+vertical[i+dy1[k]][j+dx1[k]];
					}
				}
			}
		}
		System.out.println(max);
	}
}