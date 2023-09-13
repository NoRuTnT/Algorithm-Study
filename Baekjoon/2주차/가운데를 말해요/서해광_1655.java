import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// 1655. 가운데를 말해요
// 정렬이 필요하고 빠른 속도를 요구하는 상황이기 때문에
// 우선순위큐(최대힙/최소힙)의 아이디어를 적용할 수 있다.
public class Main10 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 각각 최소힙과 최대힙
		// 하나하나 출력하면 시간초과
		PriorityQueue<Integer> q1 = new PriorityQueue<>();
		PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0 ; i<N ; i++) {
			int num = Integer.parseInt(br.readLine());
			if(q1.size()==q2.size()) q2.add(num);
			else q1.add(num);
			
			if(!q1.isEmpty() && !q2.isEmpty()) {
				if(q1.peek() < q2.peek()) {
					int x = q1.remove();
					q1.add(q2.remove());
					q2.add(x);
				}
			}
			sb.append(q2.peek()+"\n");
		}
		System.out.println(sb.toString());
	}
}
