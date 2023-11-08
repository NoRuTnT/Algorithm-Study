// 1011. Fly me to the Alpha Centauri
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int T, x, y; 
	public static long ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int tc=0 ; tc<T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			int d = y-x;
			long n = 1;
			if(d<=3) {
				ans = d;
				sb.append(ans).append("\n");
				continue;
			}
			while(true) {
				if(n*n<d && d<=n*(n+1)) {
					ans = n*2;
					break;
				}else if(n*(n+1)<d && d<=(n+1)*(n+1)) {
					ans = n*2+1;
					break;
				}
				n++;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
}
