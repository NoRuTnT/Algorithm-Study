// 1016. 제곱 ㄴㄴ 수
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static long min, max;
	public static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		min = sc.nextLong();
		max = sc.nextLong();
		int N = (int)(max-min+1);
		boolean[] chk = new boolean[N];
		// min <= x <= max
		boolean[] primeChk = new boolean[1000001];
		List<Long> primeSquare = new ArrayList<>(); // 소수 제곱을 저장
		for(int i=2 ; i<=1000000 ; i++) {
			if(!primeChk[i]) {
				primeSquare.add((long)i*i);
				for(int k=1 ; k*i<=1000000 ; k++) {
					primeChk[k*i] = true;
				}
			}
		}
		for(int i=0 ; i<primeSquare.size() ; i++) {
			long tmp = primeSquare.get(i);
			if(tmp>max) break;
			long start = (min/tmp)*tmp;
			if(start<min) start+=tmp;
			while(start<=max) {
				chk[(int)(start-min)] = true;
				start+=tmp;
			}
		}
		for(int i=0 ; i<N ; i++) {
			if(!chk[i]) ans++;
		}
		System.out.println(ans);
	}
	
	
}
