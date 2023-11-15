import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,res;
	static int[] arr;
	static boolean[] visit,finish;	
		
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=t;tc++) { //testcase
			res=0; //팀만들어진수
			n = Integer.parseInt(br.readLine());	
			arr=new int[n+1]; //부모저장배열
			visit=new boolean[n+1]; //방문배열
			finish=new boolean[n+1]; //팀완성체크배열
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=n;i++) {
				arr[i]=Integer.parseInt(st.nextToken()); //부모입력
			}
			for(int i=1;i<=n;i++) {
				if(!finish[i]) { //팀완성안된사람 dfs진행
					dfs(i);
				}
			}
			System.out.println(n-res);
		}
	}




	private static void dfs(int i) {
		if(visit[i]) { //이미 방문한사람이라면 팀완성체크하고 팀완성사람수+1
			finish[i]=true;
			res++;
		}else { //아니면 방문체크
			visit[i]=true;
		}
		
		if(!finish[arr[i]]) { //부모가 팀완성이 안되었다면 부모dfs진행
			dfs(arr[i]);
		}
		visit[i]=false; //dfs한줄기끝나고 다시 방문체크해제
		finish[i]=true; // i를 사용한건 다체크했으니까 finish
		
		
	}

}
