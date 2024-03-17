package Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 김태윤_2493 {
	static class Tower{
		int idx;
		int height;
		Tower(int idx, int height){
			this.idx=idx;
			this.height=height;
		}
	}
	static int n;
	static int[] ans;
	static Stack<Tower> input = new Stack<>();
	static Stack<Tower> check = new Stack<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		ans=new int[n+1];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++){
			int height = Integer.parseInt(st.nextToken());
			input.push(new Tower(i, height));
		}
		while(!input.isEmpty()){
			Tower curr = input.pop();
			while(!check.isEmpty() && check.peek().height<curr.height){
				Tower findLaser = check.pop();
				ans[findLaser.idx]=curr.idx;
			}
			check.push(curr);
		}
		StringBuilder sb=new StringBuilder();
		for(int i=1; i<=n; i++){
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
