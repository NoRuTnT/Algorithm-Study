import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1806 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n+1];
		int[] sum = new int[n+1];
		st = new StringTokenizer(br.readLine());
		arr[1] = Integer.parseInt(st.nextToken());
		sum[1] = arr[1];
		for(int i=1;i<n;i++) {
			arr[i+1] = Integer.parseInt(st.nextToken());
			sum[i+1] = sum[i]+arr[i+1];
		}
		int l=0;
		int r=0;
		int ans=100001;
		while(true) {
			if(r>n || l>r) {
				break;
			}
			
			int tmp=0;
			if(l==r) {
				tmp = arr[r];
			}else {
				tmp = sum[r]-sum[l];
			}
			if(tmp>=s) {
				int len=100001;
				if(l==r) {
					len = 1; 
				}else {
					len = r-l;
				}
				ans = Math.min(ans, len);
				l++;
			}else {
				r++;
			}
		}
		if(ans==100001) {
			System.out.println(0);
		}else {
			System.out.println(ans);
		}
	}

}
