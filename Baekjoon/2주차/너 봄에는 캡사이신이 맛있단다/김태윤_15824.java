import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_15824 {
	public static final int divide=1000000007;
	public static int n;
	public static int[] spicy;
	public static int sum=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		spicy=new int[n];
		for(int i=0;i<n;i++) {
			spicy[i]=Integer.parseInt(st.nextToken());
		}
		quickSort(0, n-1);
		long[] exp=new long[n];
		for(int i=0;i<n;i++) {
			exp[i]=exp(i);
		}
		//정렬 완료
		long ans=0;
		for(int i=n-1;i>=0;i--) {
			long max=(spicy[i]*exp(i))%divide;
			long min=(spicy[i]*exp(n-1-i))%divide;
			ans+=(max-min+divide)%divide;
			ans%=divide;
		}
		System.out.println(ans);
	}
	public static long exp(int num) {
		if(num==0) return 1;
		if(num==1) return 2;
		long a=exp(num/2)%divide;
		if(num%2==0) {
			return (a*a)%divide;
		}
		else {
			return (a*a*2)%divide;
		}
	}
	public static void quickSort(int start, int end) {
		if(start>=end) return;
		mot(start, end);
		int pivot=spicy[start];
		int l=start, r=end;
		while(l<r) {
			while(pivot>spicy[++l]);
			while(pivot<spicy[--r]);
			if(l<r) swap(l,r);
		}
		swap(start, r);
		quickSort(start,r-1);
		quickSort(r+1, end);
	}
	public static void mot(int start, int end) {
		int mid=(start+end)/2;
		if(spicy[mid]>spicy[end]) swap(mid, end);
		if(spicy[start]>spicy[end]) swap(start,end);
		if(spicy[start]<spicy[mid]) swap(start, mid);
	}
	public static void swap(int a, int b) {
		int tmp=spicy[a];
		spicy[a]=spicy[b];
		spicy[b]=tmp;
	}
}
