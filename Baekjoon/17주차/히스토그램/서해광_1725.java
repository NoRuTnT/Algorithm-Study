import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static int N, ans;
	public static int[] arr1, arr2;
	public static Stack<Integer> s;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr1 = new int[N+1];
		arr2 = new int[N+1];
		s = new Stack<>(); // 인덱스 정보를 저장
		for(int i=1 ; i<=N ; i++) {
			arr1[i] = Integer.parseInt(br.readLine());
		}
		s.push(0);
		for(int i=1 ; i<=N ; i++) { // 작아질 때 스택을 뽑기 시작
			while(!s.isEmpty() && arr1[i]<arr1[s.peek()]) {
				int a = s.peek();
				s.pop();
				ans = Math.max(ans, arr1[a]*(i-s.peek()-1));
			}
			s.push(i);
		}
		System.out.println(ans);
	} // main
}
