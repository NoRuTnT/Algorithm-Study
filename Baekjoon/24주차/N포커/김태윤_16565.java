package Week24;

import java.util.Scanner;

public class 김태윤_16565 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		long ans=0;
		for(int i=4; i<=num; i+=4){
			int j=i/4;
			if(j%2==1){
				ans=(ans+(combination(13, j)*combination(52-i, num-i)))%10007;
			}
			else{
				ans=(ans+10007-(combination(13,j)*combination(52-i, num-i))%10007)%10007;
			}
		}
		System.out.println(ans);
	}
	private static long combination(int n, int m){
		if(m>n/2) m=n-m;
		if(m==0) return 1;
		long comb = n;
		for(int i=1; i<m; i++){
			comb=(comb*(n-i)/i);
		}
		comb/=m;
		return comb%10007;
	}
}
