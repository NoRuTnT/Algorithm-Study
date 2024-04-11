// 16565. N포커
import java.util.Scanner;
public class Main {
	static long[][]C;
	static int N;
	static long ans;
	static final int MOD = 10007;
	public static void main(String[] args) {
		combinationInit();
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		for(int i=4;i<=N&&N>=4;i+=4) {
			if(i/4%2==1) {
				ans+=C[13][i/4]*C[52-i][N-i];
			}else
				ans=ans-C[13][i/4]*C[52-i][N-i]%MOD+MOD;
			ans%=MOD;
		}
		System.out.println(ans);
	}
	public static void combinationInit() {
		C=new long[53][53];
		C[1][1]=C[1][0]=C[0][0]=1;
		for(int i=2;i<=52;i++) {
			C[i][0]=1;
			for(int j=1;j<i;j++) {
				C[i][j]=C[i-1][j-1]+C[i-1][j];
			}
			C[i][i]=1;
		}
	}
}
