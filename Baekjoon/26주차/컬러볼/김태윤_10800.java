import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김태윤_10800 {
	static class Ball implements Comparable<Ball>{
		int idx;
		int color;
		int size;
		Ball(){}
		Ball(int idx, int color, int size){
			this.idx=idx;
			this.color=color;
			this.size=size;
		}

		@Override
		public int compareTo(Ball o) {
			return this.size-o.size;
		}
	}
	static Ball[] balls;
	static int partialSum=0;
	static int[] colorSum;
	static int[] ans;
	static int n;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		input();
		Arrays.sort(balls);
		solve();
	}
	private static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		balls=new Ball[n];
		ans=new int[n];
		colorSum=new int[n+1];
		StringTokenizer st;
		for(int i=0; i<n; i++){
			st=new StringTokenizer(br.readLine());
			int color=Integer.parseInt(st.nextToken());
			int size=Integer.parseInt(st.nextToken());
			balls[i]=new Ball(i, color, size);
		}
		br.close();
	}
	private static void solve(){
		int idx=0;
		for(int i=0; i<n; i++){
			Ball curr = balls[i];
			while(balls[idx].size<curr.size){
				partialSum+=balls[idx].size;
				colorSum[balls[idx].color]+=balls[idx].size;
				idx++;
			}
			ans[curr.idx]=partialSum-colorSum[curr.color];
		}
		for(int i=0; i<n; i++){
			sb.append(ans[i]).append("\n");
		}
		System.out.print(sb.toString());
	}
}
