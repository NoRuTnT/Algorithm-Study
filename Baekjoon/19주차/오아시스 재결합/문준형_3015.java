package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class b3015 {	
	static class person{
		int height;
		int count;
		person(int height, int count) {
			this.height = height;
			this.count = count;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<person> stack = new Stack<>();
		long res = 0;
		for(int i=0;i<n;i++) {
			int num = Integer.parseInt(br.readLine());
			person p = new person(num,1);
			if(!stack.isEmpty()) {
				if(stack.peek().height > num) {					
					stack.push(p);
					res++;
				}else if(stack.peek().height <= num) {
					while(!stack.isEmpty() && stack.peek().height <= num) {
						person tmp = stack.pop();
						res+=tmp.count;
						
						if(tmp.height==num) {
							p.count += tmp.count;
						}
					}
					
					if(!stack.isEmpty()) {
						res++;
					}					
									
					
					stack.push(p);										
				}
				
			}else {
				stack.push(p);
			}
		}
		
		System.out.println(res);
		
	}

}
