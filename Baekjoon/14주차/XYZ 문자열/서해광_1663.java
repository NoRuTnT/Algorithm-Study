// 1663. XYZ 문자열
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int P, N;
	public static long k;
	public static long[] xyz = new long[3];
	public static long[] len;
	public static String[] s = {"X", "YZ", "ZX"};
	public static char C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		P = Integer.parseInt(br.readLine()); // 1 or 2 or 3
		N = Integer.parseInt(br.readLine());
		if(P==2) {
			k = Long.parseLong(br.readLine());
		}
		if(P==3) {
			C = br.readLine().charAt(0);
		}
		len = new long[N+1];
		xyz[0]=1;
		len[1]=1;
		for(int i=2 ; i<=N ; i++) {
			long X = xyz[2];
			long Y = xyz[0];
			long Z = xyz[0]+xyz[1];
			xyz[0] = X;
			xyz[1] = Y;
			xyz[2] = Z;
			len[i] = xyz[0]+xyz[1]+xyz[2];
		}
		
		if(P==1) {
			System.out.println(len[N]);
		}else if(P==2) {
			// N단계의 XYZ 문자열에서 k번째 문자가 무엇인지 구한다.
//			s = {"X", "YZ", "ZX"};
			if(N<=3) {
				System.out.println(s[N-1].charAt((int)(k-1)));
				return;
			}
			dfs(k, N);
		}else {
			System.out.println(xyz[C-'X']);
		}
	} // main
	public static void dfs(long k, int N) {
		// 기저조건
		if(N<=3) {
			System.out.println(s[N-1].charAt((int)(k-1)));
			return;
		}
		if(len[N-3]<k) {
			dfs(k-len[N-3], N-2);
		}else {
			dfs(k, N-3);
		}
	}
}
//ex) k=5, N=7
// 1.X
// 2.YZ
// 3.ZX
// 4.XYZ : X + YZ => 1번째 + 2번째
// 5.YZZX : YZ + ZX => 2번째 + 3번째
// 6.ZXXYZ : ZY + XYZ => 3번째 + 4번째
// 7.XYZYZZX : XYZ + YZZX => 4번째 + 5번째
// 8.YZZXZXXYZ : YZZX + ZXXYZ => 5번째 + 6번째
// 9.ZXXYZXYZYZZX : ZXXYZ + XYZYZZX => 6번째 + 7번째

//[6]+[7]
//[3+4]+[4+5]
//[3]+[1]+[2]+[1]+[2]+[2]+[3]
