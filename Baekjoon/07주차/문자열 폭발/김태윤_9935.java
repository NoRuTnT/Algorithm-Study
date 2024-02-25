import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class 김태윤_9935 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] target=sc.next().toCharArray();

		Deque<Integer> deque=new LinkedList<>();
		StringBuilder sb=new StringBuilder();


		int curr=0; // 현재 target 단어에서의 위치
		//1) 앞에서부터 순회하면서, target에 없는 문자인 경우 sb에 그대로 붙이고, 아닌 경우 deque에 넣어서 처리 시작해야함
		for(int i=0;i<s.length();i++) {
			//target에 없는 문자인 경우 그대로 갖다붙이고, deque을 초기화시킴
			//이제 deque에 넣어서 각 재면서 계속 순회해야 하는데
			//index가 0~s.length-1 까지 차면 그거는 폭발 -> 길이만큼 deque 비우기
			//index가 0인게 새로 들어오면, curr를 0으로 초기화 하고 다시 deque 채우기
			if(s.charAt(i)==target[curr]) {
				deque.add(curr++);
			}
			else if(s.charAt(i)==target[0]) {
				curr=0;
				deque.add(curr++);
			}
			//위의 두 경우가 아니면 이제 deque에 담은거 다 비우고, 그 길이만큼 sb에 합쳐주면 된다
			else {
				while(!deque.isEmpty()) {
					sb.append(target[deque.poll()]);
				}
				sb.append(s.charAt(i));
				deque.clear();
				curr=0;
				continue;
			}
			// 이 경우 target.length만큼 비워준다
			if(deque.peekLast()==target.length-1) { 
				for(int j=0;j<target.length;j++) {
					deque.pollLast();
				}
				if(deque.isEmpty()) curr=0;
				else curr=deque.peekLast()+1;
			}
		}
		while(!deque.isEmpty()) {
			sb.append(target[deque.poll()]);
		}
		//sb에 채운게 없는 경우 vs 있는 경우
		if(sb.length()==0) System.out.println("FRULA");
		else System.out.println(sb.toString());
	}
}

// 1회 오류 / 오류 사유: for문을 다 돈 후 deque에 남아있는게 있을 수 있는데, 그거를 sb에 붙여주는 과정이 없었
// 2회 오류 / 오류 사유: 처음에 자료구조 stack으로 썼는데 수정을 대충해서 뒤에꺼 빼내야하는걸 앞에꺼 바꾸게 됐다