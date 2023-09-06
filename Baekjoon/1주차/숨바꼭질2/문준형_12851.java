package gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b12851 {
	static int k,n,cnt,min;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		check = new boolean[100001]; //방문지점기록배열
		cnt=0; //최단경로 갯수저장
		min=987654321; //최단경로 길이저장
		if(n==k) { //n과k가 같을경우는 바로종료
			System.out.println('0');
			System.out.println('1');			
		}else {
			bfs(n,0); 
			System.out.println(min);
			System.out.println(cnt);			
		}
	}

	public static void bfs(int n,int num) {
		Queue<int[]>queue = new LinkedList<int[]>(); //bfs진행하기위해 queue사용 	
		queue.offer(new int[] {n,num}); //queue는 배열을 원소로가짐 현재위치n과 이동한횟수 num을 저장
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			// 큐에집어넣을때 체크해주는게 아니라 뺄때 체크해주는이유는 같은횟수일때 같은점을 지나는 최단경로가 2개이상일경우도 있기때문에 모두탐색해주기위함
			check[now[0]]=true; 
			if(now[0]==k) { //목표지점에 도착했을때 cnt가 0인경우(처음탐색했을경우) 길이를 min에 저장하고 cnt++ 
				if(cnt==0) {
					min=now[1];
					cnt++;
				}else if(cnt>0 && min==now[1]) { // 이미 cnt가 증가한 도착지점일경우는 cnt++만
					cnt++;
				}
			}
			//가능한 이동방향은 2*now[0] now[0]+1 now[0]-1 세가지 
			//현재위치에서 이동이가능한자리인지 검사하고 아직방문하지않은경우에 큐에추가
			if(2*now[0]<=100000 && !check[2*now[0]]) {				
				queue.offer(new int[] {2*now[0],now[1]+1});
			}
			if(now[0]+1<=100000 && !check[now[0]+1]) {				
				queue.offer(new int[] {now[0]+1,now[1]+1});
			}
			if(now[0]-1>=0 && !check[now[0]-1]) {				
				queue.offer(new int[] {now[0]-1,now[1]+1});
			}
			
		}
		
	}

}
