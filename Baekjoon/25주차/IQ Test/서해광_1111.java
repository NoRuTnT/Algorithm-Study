// 1111. IQ Test
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
	static int N,ans;
	static int[]arr;
	static class Pair{
		int a;
		int b;
		public Pair(int a,int b) {
			this.a=a;
			this.b=b;
		}
		@Override
		public String toString() {
			return "Pair [a=" + a + ", b=" + b + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		arr=new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		if(N==1) {
			System.out.println('A');
			return;
		}
		
		if(N==2) {
			System.out.println(arr[0]==arr[1]?arr[1]:"A");
			return;
		}
		
		List<Pair> list=new ArrayList<>();
		for(int a=-200;a<=200;a++) {
			int tmp=arr[0]*a;
			int b=arr[1]-tmp;
			if(arr[1]*a+b==arr[2]) {
				list.add(new Pair(a, b));
			}
		}
		for(int i=1;i<N-1;i++) {
			for(int j=list.size()-1;j>=0;j--) {
				Pair p=list.get(j);
				int a=p.a;
				int b=p.b;
				int next=arr[i]*a+b;
				if(next!=arr[i+1]) {
					list.remove(j);
				}
			}
		}
		if(list.size()==0)
			System.out.println('B');
		else {
			int a=list.get(0).a;
			int b=list.get(0).b;
			System.out.println(arr[N-1]*a+b);
		}
	}
}
