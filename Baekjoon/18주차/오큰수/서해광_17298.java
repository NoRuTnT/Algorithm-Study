// 17298. 오큰수
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[] A, NGE;
	public static Stack<Integer> s;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		s = new Stack<>();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new int[N];
		NGE = new int[N];
		for(int i=0 ; i<N ; i++) {
			A[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=N-1 ; i>=0 ; i--) {
			while(!s.isEmpty()&&s.peek()<=A[i]) {
				s.pop();
			}
			if(s.isEmpty()) {
				NGE[i]=-1;
			}else if(A[i]<s.peek()) {				
				NGE[i]=s.peek();
			}
			s.push(A[i]);
		}
		for(int i=0 ; i<N ; i++) {
			sb.append(NGE[i]).append(" ");
		}
		System.out.println(sb);
	} // main
}
