// 2473. 세 용액
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static long[] arr, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		ans = new long[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		long tmp = Long.MAX_VALUE;
		for(int i=0 ; i<arr.length-2 ; i++) {
			int start = i+1;
			int end = arr.length-1;
			while(start<end) {
				long sum = arr[i]+arr[start]+arr[end];
				if(tmp>Math.abs(sum)) {
					tmp = Math.abs(sum);
					ans[0] = arr[i];
					ans[1] = arr[start];
					ans[2] = arr[end];
				}
				if(sum==0) break;
				if(sum<0)start++;
				else if(sum>0)end--;
			}
		}
		System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
	} // main
}
