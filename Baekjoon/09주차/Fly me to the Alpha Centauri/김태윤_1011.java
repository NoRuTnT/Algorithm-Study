import java.util.Scanner;

public class 김태윤_1011 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		StringBuilder ans=new StringBuilder();
		for(int tc=1;tc<=t;tc++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			int dist=y-x;
			int cnt=1;
			//등차 수열 두개의 합이라 생각 (오르락내리락 두개)
			for(long i=1;;i++) {
				if(i*i>=dist) break;
				cnt++;
				if(i*(i+1)>=dist) break;
				cnt++;
			}
			ans.append(cnt).append("\n");
		}
		System.out.println(ans.toString());
	}
}
