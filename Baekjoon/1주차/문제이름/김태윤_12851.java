import java.util.LinkedList;
import java.util.Scanner;

public class Baekjoon12851숨바꼭질2 {

	public static void main(String[] args) {
		final int MAX=100000;
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		boolean[] isVisited=new boolean[MAX+1];
		//bfs를 이용하여 +1, -1, *2로 이동하는 경우를 만들어간다
		//visited 배열을 통해 이미 지나간 경우 queue에 담지 않고 나머지에 대해서만 bfs 수행
		int ans=0; // 가장 빠른 시간으로 찾는 방법 개수
		int t=-1; // 가장 빠른 시간 -> while문 도는거 때문에 일단 -1로 시작
		LinkedList<Integer> queue=new LinkedList<>();
		queue.add(n); // 출발지점
		isVisited[n]=true;
		while(ans==0) {
			int len=queue.size(); //queue사이즈가 계속 바뀌기 때문에, t값을 잘 구하기 위해 고정값을 얻는다
			for(int i=0;i<len;i++) {
				int now=queue.poll();
				isVisited[now]=true;
				if(now==k) {
					ans++; // 정답까지 가는 방법 1개 추가, break는 하지 않음
				}
				//경계조건을 만족하고 한번도 안 지난 경우만 queue에 담음
				if(now+1<=MAX && !isVisited[now+1]) {
					queue.add(now+1);
				}
				if(now-1>=0 && !isVisited[now-1]) {
					queue.add(now-1);
				}
				if(now*2<=MAX && !isVisited[now*2]) {
					queue.add(now*2);
				}
			}
			t++; // queue에 담긴 모든 경우 확인했으니 시간 증가
		}//while문 나오면 저장된 값이 그대로 답이다
		System.out.println(t);
		System.out.println(ans);
	}
}
