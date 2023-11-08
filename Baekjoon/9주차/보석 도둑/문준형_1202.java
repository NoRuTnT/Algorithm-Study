package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1202 {
	static class jewel implements Comparable<jewel>{
		int weight;
		int value;
		
		public jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(jewel o) { //무게기준 오름차순정렬
			return this.weight-o.weight;
		}
			
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> (o2-o1)); // 내림차순정렬 우선순위큐
		ArrayList<jewel> weightlist = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			weightlist.add(new jewel(m,v));  //Arraylist에 jewel클래스 입력다넣음
		}
		Collections.sort(weightlist); // 무게순오름차순정렬
		int[] bag = new int[k]; 
		for(int i=0;i<k;i++) {
			bag[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag);		//가방 입력후 오름차순정렬
		long res=0; // 결과저장 변수 int범위 넘어갈수있어서 long
		int jem=0; // 밑에서 반복문돌면서 가방에넣을 보석 선택시 사용할변수
		
		for(int i=0;i<k;i++) {  //가방갯수만큼 반복
			while(true) {
				if(jem>=n) { //가방에 보석넣은횟수가 보석수를 채웠다면 break
					break;
				}
				jewel now = weightlist.get(jem); //무게순정렬 첫번째보석부터 비교시작
				if(bag[i]<now.weight) { //보석무게가 가방보다 크면 쳐냄
					break;
				}
				queue.add(now.value); // 조건 만족하면 value를 우선순위큐에 추가
				jem++; //다음보석
			}
			//break로 나올때마다 쌓인 우선순위큐 맨위꺼 꺼내서 결과에추가
			if(!queue.isEmpty()) {
				res+=queue.poll(); 
			}
		}
		
		
		System.out.println(res);
	}

}
