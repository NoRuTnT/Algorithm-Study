package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class b1655 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder()); //최대큐설정
		PriorityQueue<Integer> min = new PriorityQueue<>(); //기본이 최소큐
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine()); //n값입력
		max.offer(Integer.parseInt(br.readLine()));
		bw.write(max.peek()+"\n");
		min.offer(Integer.parseInt(br.readLine())); //1,2번째는 일단 1개씩집어넣고 후에 비교하여 교환
		if(max.peek()>min.peek()) {
			min.offer(max.poll());
			max.offer(min.poll());
		}
		bw.write(max.peek()+"\n");
		for(int i=0;i<n-2;i++) { //나머지숫자들 진행
			int num=Integer.parseInt(br.readLine());
			if(max.size()==min.size()){  //최소큐와 최대큐의 크기가 같다면 최소큐에 집어넣는다.
				max.offer(num);				
			}else {
				min.offer(num);
			}
			if(max.peek()>min.peek()) { // 최대큐의 우선순위가 가장높은 숫자와 최소큐의 우선순위가 가장높은숫자의 크기를 비교했을때 최대큐에서 뽑히는 숫자가 크다면 교환해준다.
				min.offer(max.poll()); // ex) 최대큐 1,2,3,4,6 최소큐 5,7,8,9,10 일때 최대큐의 제일위에있는 6과 최소큐의 제일위에있는 5를 교환한다.
				max.offer(min.poll());
			}
			bw.write(max.peek()+"\n"); //전체숫자의 개수가 홀수개일때는 가운데숫자를 최대큐에 넣어놓았고 짝수개일때는 교환과정을통해 최대큐에 작은숫자를 넣어놓았기때문에		
		}							// 무조건 최소큐에서 중간값을뽑을수있다.	
		bw.flush();
		br.close();
		bw.close();		
	}
}
