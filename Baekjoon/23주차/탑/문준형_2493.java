import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class save{	
		int id;
		int height;
		int receive;
		
		public save(int id, int height, int receive) {
			this.id = id;
			this.height = height;
			this.receive = receive;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		save[] tower = new save [500001];
		int n = Integer.parseInt(br.readLine());		
		Stack<save> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {			
			int height = Integer.parseInt(st.nextToken());
			tower[i] = new save(i,height,0);			
		}
		
		for(int i=n;i>=1;i--) {
			save tmp = tower[i];
			if(!stack.isEmpty() && stack.peek().height < tmp.height) {
				while(true) {
					if(stack.isEmpty() || stack.peek().height>tmp.height) {
						break;
					}
					tower[stack.peek().id].receive = i;					
					stack.pop();					
				}
			}
			stack.add(tmp);
		}
		for(int i=1;i<=n;i++) {
			bw.write(tower[i].receive+" ");
		}		
		bw.flush();
	}

}
