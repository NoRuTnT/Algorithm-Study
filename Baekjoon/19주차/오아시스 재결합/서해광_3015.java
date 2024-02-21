// 3015. 오아시스 재결합
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int N;
	static long ans;
	static int[]arr;
	static class Node{
		int height;
		int num;
		public Node(int height, int num) {
			this.height = height;
			this.num = num;
		}
	}
	static Stack<Node> s;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr=new int[N];
		s=new Stack<>();
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		long cnt=1;
		for(int i=0;i<N;i++) {
			int tmp=arr[i];
			int tmpCnt=1;
			while(!s.isEmpty()&&tmp>s.peek().height) {
				ans+=s.peek().num;
				s.pop();
			}
			if(!s.isEmpty()) {
				if(s.peek().height==arr[i]) {
					ans+=s.peek().num;
					tmpCnt=s.peek().num+1;
					if(s.size()>1)ans++;
					s.pop();
				}else {
					ans++;
				}
			}
			s.push(new Node(arr[i], tmpCnt));
		}
		System.out.println(ans);
	}
}
