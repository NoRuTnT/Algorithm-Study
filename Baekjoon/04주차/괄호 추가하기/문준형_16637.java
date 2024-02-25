package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b16637 {
	static int max;
	static char[] str;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());		
//		String[] str = br.readLine().split("");
		str = br.readLine().toCharArray();
		max = -987654321;
		
		dfs(2,str[0]-'0'); //dfs메소드가 start숫자 1칸앞의 연산자를 이용하여 진행하기때문에 초기설정이렇게해놓음
		System.out.println(max);
		
		
	}

	private static void dfs(int start,int sum) {
		if(start>=str.length) {
			 max = Math.max(max,sum);
			return;
		}
		dfs(start+2,calculate(str[start]-'0',sum,str[start-1]));//그냥진행하기
  		if(start+2<str.length) {								//괄호넣어서 진행하기
			int sum2 = calculate(str[start+2]-'0',str[start]-'0',str[start+1]);
			int total = calculate(sum2,sum,str[start-1]);
			dfs(start+4,total);
		}
	}
	
	public static int calculate(int x, int sum, char oper) {
		if(oper=='+') {
			return x+sum;
		}else if(oper=='-') {
			return sum-x;
		}else {
			return x*sum;
		}
	}

}

