package gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class b17298 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> stack = new Stack<>();
    //처음걸집어넣기위해 !stack.isEmpty(), 집어넣으면서 더큰수찾으면 pop으로 큰수로바꾸기
		for(int i=0;i<n;i++) {			
			while(!stack.isEmpty() && arr[stack.peek()]<arr[i]) {
				arr[stack.pop()]=arr[i];
			}
			stack.push(i);
		}
		//스택에남은애들은 바꿀큰수가없음 -1
		while(!stack.isEmpty()) {
			arr[stack.pop()]=-1;
		}
		for(int i=0;i<n;i++) {
			bw.write(arr[i]+" ");
		}
		bw.flush();
		br.close();
		bw.close();
		
	}

}
