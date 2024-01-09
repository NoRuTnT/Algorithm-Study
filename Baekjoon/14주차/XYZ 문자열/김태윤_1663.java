import java.util.Scanner;

public class 김태윤_1663 {
	static long cnt=0;
	static long q2=0;
	static String ans2="";
	static boolean flag=false;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int question=sc.nextInt();
		int n=sc.nextInt();
		String q3="";
		switch(question) {
		case 2:
			q2=sc.nextLong();
			break;
		case 3:
			q3=sc.next();
			break;
		}
		
		//n번째 문자열: n-3번째문자열 + n-2번째 문자열
		//1번째: X, 2번째: YZ, 3번째: ZX
	
		//1번 문제 답안
		long[] dp1=new long[n+1];
		if(n>=1) dp1[1]=1;
		if(n>=2) dp1[2]=2;
		if(n>=3) dp1[3]=2;
		for(int i=4;i<=n;i++) {
			dp1[i]=dp1[i-3]+dp1[i-2];
		}
		
		//3번 문제 답안
		long[][] dp3=new long[n+1][3]; //n번째 문자열의 x, y, z 개수
		if(n>=1) dp3[1][0]=1;
		if(n>=2) {
			dp3[2][1]=1;
			dp3[2][2]=1;
		}
		if(n>=3) {
			dp3[3][0]=1;
			dp3[3][2]=1;
		}
		
		for(int i=4;i<=n;i++) {
			for(int j=0;j<3;j++) {
				dp3[i][j]=dp3[i-3][j]+dp3[i-2][j];
			}
		}
		
		//2번 문제 답안
		recursion(n, dp1);
		
		switch(question) {
		case 1:
			System.out.println(dp1[n]);
			break;
		case 2:
			System.out.println(ans2);
			break;
		case 3:
			if(q3.equals("X")) System.out.println(dp3[n][0]);
			else if(q3.equals("Y")) System.out.println(dp3[n][1]);
			else System.out.println(dp3[n][2]);
		}
	}
	public static void recursion(int num, long[] dp1) {
		if(flag) return;
		if(num<4) {
			if(num==1) cnt++;
			else cnt+=2;
			if(cnt==q2) {
				if(num==2) ans2="Z";
				else ans2="X";
				flag=true;
			}
			else if(cnt>q2) {
				if(num==2) ans2="Y";
				else ans2="Z";
				flag=true;
			}
			return;
		}
		
		// xyz_i=xyz_(i-3)+xyz_(i-2) 이므로 길이 넘어가면 뒤로, 아니면 앞 부분 보기
		if(dp1[num-3]+cnt<q2) {
			cnt+=dp1[num-3];
			recursion(num-2, dp1);
		}
		else {
			recursion(num-3, dp1);
		}
	}
}
