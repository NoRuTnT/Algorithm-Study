import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_1005 {
	static int[] inDegree;
	static ArrayList<Integer>[] adjList;
	static int[] time;
	static int[] dp;
	static int n;
	static int k;
	public static void main(String[] args) throws IOException{
		//DAG이기 때문에 일단 위상정렬한 후, 소요시간 계산하면 좋을듯
		//위상정렬을 하면서 dp로 시간 계산
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int tc=0;tc<t;tc++) {
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			inDegree=new int[n+1];
			time=new int[n+1];
			adjList=new ArrayList[n+1];
			dp=new int[n+1]; // 최대 소요시간 저장용
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=n;i++) {
				time[i]=Integer.parseInt(st.nextToken());
				adjList[i]=new ArrayList<>();
			}
			for(int i=0;i<k;i++) {
				st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				adjList[from].add(to);
				inDegree[to]++;
			}
			int target=Integer.parseInt(br.readLine());
			
			Queue<Integer> queue=new LinkedList<>(); // inDegree 0인것만 넣을 곳
			for(int i=1;i<=n;i++) {
				if(inDegree[i]==0) {
					dp[i]=time[i]; // 초기값: 선행작업 없는 경우 자기 작업만 하면 완료
					queue.offer(i);
				}
			}
			
			while(!queue.isEmpty()) {
				int idx=queue.poll();
				if(idx==target) break; // 이 경우 이미 다 찾았음
				for(int i=0;i<adjList[idx].size();i++) {
					int next=adjList[idx].get(i);
					inDegree[next]--;
					dp[next]=Integer.max(dp[next], dp[idx]+time[next]); // 더 큰 값만큼 소요된다
					if(inDegree[next]==0) queue.offer(next);
				}
			}
			sb.append(dp[target]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
