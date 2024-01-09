import java.util.Scanner;

public class 김태윤_29793 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt(); // 용맥 길이
		int h=sc.nextInt(); // 몹 체력
		char[] lala=sc.next().toCharArray();
		int[] srw=new int[3];
		
//		n>3 && h>3 이면 무조건 불가능인듯?? 
//		서로 다른 용맥 3개 깔아놓은 다음에도 맨앞에꺼 피 다 못깠는데 새로 용맥 깔아야 되서 불가능함
// 		n<=3이면 무조건 가능 바꿀 용맥 개수만 구하면 된다
// 		h=1이면 무조건 0
// 		h=2이면 바로 앞에꺼랑만 다르게 용맥 변환시키기
//		h=3이면 SRW SWR RSW RWS WSR WRS 계속 반복되는 양상 6개 중 1개 고르면 된다
		
		if(n==1 || h==1) {
			System.out.println(0);
			return;
		}
		if(h==2) {
			int ans=0;
			for(int i=1;i<n;i++) {
				if(lala[i-1]==lala[i]) {
					ans++; // 용맥을 변환
					i++; // 용맥 변환을 그 다음꺼랑 무조건 다르게 할 수 있으므로, 한 칸 건너뛰기 가능
				}
			}
			System.out.println(ans);
			return;
		}
		//체력 이제 무조건 3이상
		if(n==2) {
			if(lala[0]==lala[1]) System.out.println(1);
			else System.out.println(0);
			return;
		}
		if(n==3) {
			if(lala[0]==lala[1] && lala[0]==lala[2]) System.out.println(2);
			else if(lala[0]==lala[1] || lala[1]==lala[2] || lala[0]==lala[2]) System.out.println(1);
			else System.out.println(0);
			return;
		}
		if(h>3) {
			System.out.println(-1);
			return;
		}
		
		//이제 n>3, h=3인 경우만 남는다
		char[][] compare= {{'S','R','W'},{'S','W','R'},{'W','S','R'},{'W','R','S'},{'R','S','W'},{'R','W','S'}};
		int ans=Integer.MAX_VALUE;
		for(int i=0;i<6;i++) {
			int cnt=0;
			for(int j=0;j<n;j++) {
				if(compare[i][j%3]!=lala[j]) cnt++;
			}
			if(cnt<ans) ans=cnt;
		}
		System.out.println(ans);
	}
}
