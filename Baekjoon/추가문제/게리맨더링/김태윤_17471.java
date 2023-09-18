import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] people;
	static boolean[][] links;
	static int min=Integer.MAX_VALUE;
	public static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		people=new int[n+1];
		links=new boolean[n+1][n+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			people[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			int chkNum=Integer.parseInt(st.nextToken());
			for(int j=0;j<chkNum;j++) {
				int idx=Integer.parseInt(st.nextToken());
				links[i][idx]=true;
				links[idx][i]=true;
			}
		}
	}
	public static void bfs(boolean[] team) { //true인 애들 전부 이어졌는지, false인 애들 전부 이어졌는지 확인
		int tNum=0, fNum=0; //t 개수, f개수
		int tFirst=n+1, fFirst=n+1; // bfs 시작할 t, f 지역 설정
		for(int i=1;i<=n;i++) {
			if(team[i]) {
				tNum++;
				if(i<tFirst) tFirst=i;
			}
			else {
				fNum++;
				if(i<fFirst) fFirst=i;
			}
		}
		boolean flag=true;
		//(1) t이어졌는지 확인
		LinkedList<Integer> queue=new LinkedList<>();
		boolean[] check=new boolean[n+1];
		queue.add(tFirst);
		check[tFirst]=true;
		int cnt=1;
		while(!queue.isEmpty()) {
			int idx=queue.poll();
			for(int i=1;i<=n;i++) {
				if(check[i]) continue; // 이미 훑은 경우
				if(team[i] && links[idx][i]) {
					queue.add(i);
					check[i]=true;
					cnt++;
				}
			}
		}
		if(cnt!=tNum) {
			flag=false;
			return; // 더이상 볼 필요 없다
		}
		//2) false 연결된 경우
		check=new boolean[n+1];
		cnt=1;
		queue.add(fFirst);
		check[fFirst]=true;
		while(!queue.isEmpty()) {
			int idx=queue.poll();
			for(int i=1;i<=n;i++) {
				if(check[i]) continue; // 이미 훑은 경우
				if(!team[i] && links[idx][i]) { //false팀이고 서로 연결된 경우
					queue.add(i);
					check[i]=true;
					cnt++;
				}
			}
		}
		if(cnt!=fNum) flag=false;
		if(flag) { // 구역 가르기 성공일 경우, 이제 각각 합산 구해야 됨
			int tSum=0, fSum=0;
			for(int i=1;i<=n;i++) {
				if(team[i]) tSum+=people[i];
				else fSum+=people[i];
			}
			if(min>Math.abs(tSum-fSum)) min=Math.abs(tSum-fSum);
		}
		return;
	}
	public static void dfs(int depth, int fin, int idx, boolean[] check) {
		if(depth==fin) { //조합 구성 완료 : check 넘겨서 지역구 다 짰음 -> bfs로 실제 연결됐는지 확인
			bfs(check);
			return;
		}
		for(int i=idx;i<=n;i++) {
			if(!check[i]) {
				check[i]=true;
				dfs(depth+1, fin, i+1, check);
				check[i]=false;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		input();
		for(int i=1;i<n;i++) {
			dfs(0, i, 1, new boolean[n+1]); //조합 생성 - depth, i:조합개수
		}
		if(min==Integer.MAX_VALUE) System.out.println(-1); // min값 안 바뀌면 두 선거구 못나눔
		else System.out.println(min);
	}

}
