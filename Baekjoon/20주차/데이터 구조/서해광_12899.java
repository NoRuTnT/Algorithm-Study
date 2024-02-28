// 12899. 데이터 구조
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		sb=new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		S=new int[4194304];
		for(int tc=0;tc<N;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int T=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken());
			if(T==1) {
				add(X, 1, 1, 2097152); // 안되면 2백만에서 1빼고 입력
			}else {
				delete(X, 1, 2097152, 1);
			}
		}
		System.out.println(sb);
	}
	public static void add(int X, int idx, int st, int ed) {
		S[idx]++;
		if(st==ed) {
			return;
		}
		int mid=(st+ed)/2;
		if(X>mid) add(X, idx*2+1, mid+1, ed); // 절반중 오른쪽탐색
		else add(X, idx*2, st, mid); // 절반중 왼쪽탐색
	}
	public static void delete(int X, int st, int ed, int idx) {
		S[idx]--;
		if(st==ed) {
			sb.append(idx-2097151+"\n"); // idx 보정
			return;			
		}
		int mid=(st+ed)/2;
		if(S[idx*2]>=X) { // 왼쪽 탐색
			delete(X, st, mid, idx*2);
		}else {
			delete(X-S[idx*2], mid+1, ed, idx*2+1);
		}
	}
}
