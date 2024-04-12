import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static long[] arr;
	static long left, right, mid, min,max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		arr = new long [n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(arr[i], max);
		}
		left = 1;
		right = max*m;		
		while(left<right) {
			mid = (left+right)/2;	
			long cnt = 0;
			for(int i=0;i<n;i++) {
				cnt += mid/arr[i];
				if(cnt>=m) {
					break;
				}
			}
			if(cnt>=m) {
				right = mid;
				
			}else {
				left = mid+1;
			}
		}
		System.out.println(right);
		
	}
	

}
