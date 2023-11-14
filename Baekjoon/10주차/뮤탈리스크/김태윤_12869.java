import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 김태윤_12869 {

	static ArrayList<int[]> damage = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] scv=new int[3];
		for(int i=0;i<n;i++) {
			scv[i]=sc.nextInt();
		}
		int[][][] dp=new int[61][61][61]; // 마리 별 체력
		int[][] attack= {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
		if(n==2) {
			for(int i=0;i<6;i++) {
				for(int j=0;j<3;j++) {
					if(attack[i][j]==1) attack[i][j]=0;
				}
			}
		}
		else if(n==1) {
			for(int i=0;i<6;i++) {
				for(int j=0;j<3;j++) {
					if(attack[i][j]<9) attack[i][j]=0;
				}
			}
		}
		
		for(int i=0;i<=60;i++) {
			for(int j=0;j<=60;j++) {
				Arrays.fill(dp[i][j], 987654321);
			}
		}
		dp[0][0][0]=0;
		for(int i=0;i<=60;i++) {
			for(int j=0;j<=60;j++) {
				for(int k=0;k<=60;k++) {
					for(int l=0;l<6;l++) {
						dp[i][j][k]=Math.min(dp[i][j][k], dp[(i-attack[l][0]<0)?0:(i-attack[l][0])][(j-attack[l][1]<0)?0:j-attack[l][1]][(k-attack[l][2]<0)?0:(k-attack[l][2])]+1);
					}
				}
			}
		}
		System.out.println(dp[scv[0]][scv[1]][scv[2]]);
	}
}
