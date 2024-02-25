import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//1. 10퍼에서 시간초과 => pq 한개만 씀
//2. pq 2개 쓰는 방법 블로그 보고 배워서 통과

public class 김태윤_1202 {
	
	static class Gem{
		int mass;
		int value;
		
		Gem(int mass, int value){
			this.mass=mass;
			this.value=value;
		}
	}
	
	static int n;
	static int m;
	static long ans=0;
	static int[] bag;
	static PriorityQueue<Gem> gems = new PriorityQueue<Gem>(new Comparator<Gem>(){
		@Override
		public int compare(Gem o1, Gem o2) {
			if(o1.mass<o2.mass) return -1;
			else return 1;
		}
	});
	static PriorityQueue<Gem> gem=new PriorityQueue<>(new Comparator<Gem>() {
		@Override // 내림차순으로 정렬
		public int compare(Gem o1, Gem o2) {
			if(o1.value<o2.value) return 1;
			else if(o1.value>o2.value) return -1; 
			else return 0;
		}
	});
	
	public static void main(String[] args) throws IOException{
		//1. 가방을 무게에 따라 정렬, 보석을 가치에 따라 정렬
		//2. 보석 가치 순서대로 가방을 정하기. -> 이분탐색을 통해 보석보다 용량 큰 것 중 가장 작은 가방 찾기
		//2-1. cnt해서 가방이 다 차거나, 더 이상 넣을 수 있는 보석이 없을 때 까지!
		input();
		process();
		output();
	}
	public static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		bag=new int[m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int mass=Integer.parseInt(st.nextToken());
			int value=Integer.parseInt(st.nextToken());
			gems.offer(new Gem(mass,value));
		}
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			bag[i]=Integer.parseInt(st.nextToken());
		}
		//무게순 오름차순
		
		Arrays.sort(bag);
	}
	public static void process() {
		for(int i=0;i<m;i++) {
			while(!gems.isEmpty() && gems.peek().mass<=bag[i]) {
				gem.offer(gems.poll());
			}
			if(!gem.isEmpty()) {
				Gem curr=gem.poll();
				ans+=curr.value;
			}
		}
	}
	public static void output() {
		System.out.println(ans);
	}

}
