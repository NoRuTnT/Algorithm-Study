import java.util.Scanner;

public class 김태윤_2011 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		char[] code=sc.next().toCharArray();
		int[] num=new int[code.length];
		int MOD=1000000;
		int len=code.length;
		//1) 0은 반드시 10 혹은 20 형태로 있어야 한다 -> 여러가지로 분화 불가능
		//1-2) 0 앞에 1, 2가 아닌 다른 수가 있거나, 0이 맨 처음 시작인 경우 암호 해석 불가능처리
		//2) 앞자리와 뒷자리 합쳐서 26이하면 a / b or ab 두가지 형태로 나눠질 수 있다.
		int[][] dp=new int[len][2]; 
		// n번째 자리에서, 단독인 경우 & 앞자리인 경우 / 끝자리인 경우
		
		//예외처리
		if(code[0]=='0') {
			System.out.println(0);
			return;
		}
		
		//초기화
		dp[0][0]=1;
		dp[0][1]=0;
		num[0]=code[0]-'0'; // 정수형으로 변환해서 사용할 배열
		
		//이제 2번째 자리부터 끝까지 시작
		for(int i=1;i<len;i++) {
			num[i]=code[i]-'0';
			
			//1) i번째 자리가 0인 경우, 앞자리가 1, 2인 경우만 가능 + 단독 사용 불가능
			if(num[i]==0) {
				if(num[i-1]==1 || num[i-1]==2) {
					dp[i][0]=0;
					dp[i][1]=dp[i-1][0]%MOD; // 이전꺼에서 단독사용된 경우들을 앞자리로 온다 생각
				}
				else {
					//오류 발생이므로 0 출력하고 리턴
					System.out.println(0);
					return;
				}
			}
			//2) i-1번째와 i번째 합해서 26이하인 경우, 단독 사용 가능 + 두개 붙여서 가능
			else if(num[i-1]*10+num[i]<=26) {
				dp[i][0]=dp[i-1][0]%MOD+dp[i-1][1]%MOD; // 단독 사용 가능일 경우 둘다 괜춘
				dp[i][1]=dp[i-1][0]%MOD; // 이어서인 경우 앞에서 단독 사용만 가능
			}
			//3) 나머지: 무조건 단독사용
			else {
				dp[i][0]=dp[i-1][0]%MOD+dp[i-1][1]%MOD;
				dp[i][1]=0;
			}
		}
		System.out.println((dp[len-1][0]%MOD+dp[len-1][1]%MOD)%MOD);
	}
}

// 엄청 많이 틀렸는데 55번째 줄 출력 할 때, 두 수의 합을 또 나머지 처리 해야됐는데 그걸 안 해서 많이 틀렸어요