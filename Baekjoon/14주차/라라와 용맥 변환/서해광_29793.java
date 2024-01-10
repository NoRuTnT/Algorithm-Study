// 29793. 라라와 용맥 변환
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static int N, H, ans;
	public static String str;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		str = br.readLine();
		if(N==1 || H==1) {
			System.out.println(0);
			return;
		}
		// N>=2
		if(H==2) {
			for(int i=0 ; i<N-1 ; i++) {
				if(str.charAt(i)==str.charAt(i+1)) {
					ans++;
					i++;
				}
			}
			System.out.println(ans);
			return;
		}
		if(H==3) {
			String[] arr = {"SRW", "SWR", "WRS", "WSR", "RWS", "RSW"};
			ans = N+1;
			for(int i=0 ; i<6 ; i++) {
				int cnt=0;
				for(int j=0 ; j<N ; j++) {
					if(str.charAt(j)!=arr[i].charAt(j%3)) cnt++;
				}
				ans = Math.min(ans, cnt);
			}
			System.out.println(ans);
			return;
		}
		if(H>=4) {
			if(N>=4) {
				System.out.println(-1);
				return;
			}else { // 2<=N<=3
				Set<Character> set = new HashSet<>();
				for(int i=0 ; i<N ; i++) {
					set.add(str.charAt(i));
				}
				System.out.println(N-set.size());
				return;
			}
		}
	} // main
}
