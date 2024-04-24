
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int s=Integer.parseInt(st.nextToken());
		int[] partialSum=new int[n+1];
		st=new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++){
			partialSum[i]=partialSum[i-1]+Integer.parseInt(st.nextToken());
		}
		int l=0;
		int r=1;
		int ans=n+1;
		while(l<n){
			int sum=partialSum[r]-partialSum[l];
			if(r<n && sum<s) r++;
			else if(l<r && sum>=s){
				ans=Math.min(ans, r-l);
				l++;
			}
			else l++;
		}
		if(ans==n+1) ans=0;
		System.out.println(ans);
	}
}
