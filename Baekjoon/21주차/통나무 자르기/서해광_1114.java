// 1114. 통나무 자르기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int L,K,C,ans1,ans2,mid;
	static int start,end=2000000000;
	static int comp,s,p;
	static int[]arr,pos;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		L=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		pos=new int[K+1];
		arr=new int[K+1];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			pos[i]=Integer.parseInt(st.nextToken());
		}
		pos[K]=L;
		Arrays.sort(pos);
		arr[0]=pos[0];
		for(int i=1;i<=K;i++) {
			arr[i]=pos[i]-pos[i-1];
		}
		int a=0;
		while(start<=end) {
			mid=(start+end)/2;
			int i=0;
			for(p=s=comp=0,i=K;i>=0;i--) {
				if(arr[i]>mid) {
					comp=C+1;
					break;
				}
				if(s+arr[i]>mid) {
					s=0;
					comp++;
					p=i;
				}
				s+=arr[i];
			}
			if(comp>C)start=mid+1;
			else {
				ans1=mid;
				end=mid-1;
				ans2=comp==C?pos[p]:pos[0];
			}
		}
		System.out.println(ans1+" "+ans2);
	} // main
}
