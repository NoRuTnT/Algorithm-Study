// 3079. 입국심사
// 이제부터 시간초과 없애기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N,M;
	static long s,e,mid;
	static long[]arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new long[N];
		for(int i=0;i<N;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		s=0;
		e=Long.MAX_VALUE;
		label:
		while(true) { // s<=e
			mid=(s+e)/2;
			if(s==e)break;
			long tmp = 0L;
			for(int i=0;i<N;i++) {
				tmp+=mid/arr[i];
				if(tmp>=M) {
					e=mid;
					continue label;
				}
			}
			s=mid+1;
		}
		System.out.println(mid);
	}
}
