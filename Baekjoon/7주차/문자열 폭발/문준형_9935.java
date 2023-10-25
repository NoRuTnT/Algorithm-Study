package gold;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class b9935 {
	public static void main(String[] args) throws IOException {
		Stack<String> stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split("");
		String[] target = br.readLine().split("");
		int len = target.length;		
		//그냥 깡 탐색 1트 시간초과
		//deque 2개이용해서 서로이동하면서 문자열체크되면 빼버리기 시간초과
		//stack 3트
		for(int i=0;i<str.length;i++) { //입력된 문자열길이만큼반복
			stack.add(str[i]);   //한글자씩 스택에추가
			int cnt=0;	//타겟문자열카운트
			if(stack.size()>=len) { //스택에쌓인게 타겟문자길이보다 길어진다면
				for(int j=0;j<len;j++) { //타겟문자열길이만큼 반복							
					if(stack.get(stack.size()-len+j).equals(target[j])) { //스택에있는 문자열 인덱스를 구해서 뒷부분만 타겟과비교
						cnt++;
					}						
					
					if(cnt==len) { //cnt가 타겟길이만큼쌓였다면 
						for(int k=0;k<len;k++) {
							stack.pop(); //제거
						}
					}
				}
			}
		}
		if(stack.isEmpty()) {
			bw.write("FRULA");
		}else {
			for(String s:stack) {
				bw.write(s);					
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
