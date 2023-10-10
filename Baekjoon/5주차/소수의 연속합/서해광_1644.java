// 1644. 소수의 연속합
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		boolean[] chk = new boolean[N+1];
		chk[1] = true;
		List<Integer> list = new ArrayList<>();
		for(int i=2 ; i<=N ; i++) {
			if(!chk[i]) {
				list.add(i);
				for(int j=2 ; i*j<=N ; j++) {
					chk[i*j] = true;
				}
			}
		}
		int ans, sum, start, end;
		ans = sum = start = end = 0;
		while(true) {
			if(sum>=N)sum-=list.get(start++);
			else if(end==list.size()) break;
			else sum+=list.get(end++);
			if(sum==N) ans++;
		}
		System.out.println(ans);
	}
}
