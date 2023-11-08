package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b23309 {
	static class Station{
		int[] preNodes;
		int[] nextNodes;
		
		Station(){
			preNodes = new int[1000001]; //역의개수는 최대 1000000개
			nextNodes = new int[1000001];
		}
		
		void add(int target, int node) {
			if(target==-1) { //처음추가할때
				preNodes[node] = nextNodes[node] = node;
				return;
			}
			preNodes[node] = target; //추가하는곳에서 이전역가리키는곳은 target으로 변경
			nextNodes[node] = nextNodes[target]; // 추가하는곳에서 다음역가리키는곳은 이전에 target역이 다음역가리키는 곳으로
			preNodes[nextNodes[target]] = node; //다음역의 이전역가리키는곳을 추가하는곳으로
			nextNodes[target] = node; //이전역의 다음역가리키는곳을 추가하는곳으로  
		}
		void delete(int target) {
			preNodes[nextNodes[target]] = preNodes[target]; //다음역의 이전역가리키는곳을 삭제할역의 이전역으로
			nextNodes[preNodes[target]] = nextNodes[target];//이전역의 다음역가리키는곳을 삭제할역의 다음역으로
		}
		void print(int num) throws IOException {
			bw.write(num+"\n");
		}
	}
	static BufferedWriter bw;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Station station = new Station();
		int target=-1;
		while(st.hasMoreTokens()) {
			int id = Integer.parseInt(st.nextToken());
			station.add(target, id);
			target = id;
		}
		int insert=0;
		for(int i=0;i<m;i++)  {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			target = Integer.parseInt(st.nextToken());
			if(st.hasMoreTokens()) {
				insert = Integer.parseInt(st.nextToken());
			}
			if(order.equals("BN")) { 
				station.print(station.nextNodes[target]);
				station.add(target, insert);				
			}else if(order.equals("BP")) {
				station.print(station.preNodes[target]);
				station.add(station.preNodes[target], insert);	
			}else if(order.equals("CN")) {
				station.print(station.nextNodes[target]);
				station.delete(station.nextNodes[target]);
			}else {
				station.print(station.preNodes[target]);
				station.delete(station.preNodes[target]);
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
