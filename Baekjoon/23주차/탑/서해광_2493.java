// 2493. 탑
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main {
	static int N;
	static int[]arr1,arr2;
	static class Pair{
		int a;
		int b;
		public Pair(int a,int b) {
			this.a=a;
			this.b=b;
		}
	}
	static Stack<Pair> s;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		N=Integer.parseInt(br.readLine());
		arr1=new int[N];
		arr2=new int[N];
		s=new Stack<>();
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr1[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<N;i++) {
			while(!s.isEmpty()&&s.peek().a<arr1[i]) {
				s.pop();
			}
			arr2[i]=s.isEmpty()?0:(s.peek().b+1); // s.peek가 아니라 그 값에 해당하는 번호를 출력
			s.add(new Pair(arr1[i], i));
			sb.append(arr2[i]+" ");
		}
		System.out.println(sb);
	}
}
