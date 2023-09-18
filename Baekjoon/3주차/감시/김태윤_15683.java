import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class cctv{
	int r;
	int c;
	int num;
	cctv(){}
	cctv(int r, int c, int num){
		this.r=r;
		this.c=c;
		this.num=num;
	}
}
public class Main {
	static int n;
	static int m;
	static int[][] arr;
	static int empty=0;
	static int min=Integer.MAX_VALUE;
	static List<cctv> list=new ArrayList<>();
	public static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		arr=new int[n][m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) empty++;
				else if(arr[i][j]<6) {
					list.add(new cctv(i,j, arr[i][j]));
				}
			}
		}
	}
	public static int sum(cctv now, int idx, boolean[][] check) {
		int[] dr= {-1,0,1,0};
		int[] dc= {0,1,0,-1};
		int cnt=0;
		int multiple=1;
		while(true) {
			if(now.r+dr[idx]*multiple<0 || now.r+dr[idx]*multiple>=n || now.c+dc[idx]*multiple<0 || now.c+dc[idx]*multiple>=m) break; // 범위 나가면 break
			if(check[now.r+dr[idx]*multiple][now.c+dc[idx]*multiple]) {
				multiple++;
				continue; // 이미 check됐으면 다음으로
			}
			if(arr[now.r+dr[idx]*multiple][now.c+dc[idx]*multiple]==0) { // 빈 공간이면 cnt++
				cnt++;
				check[now.r+dr[idx]*multiple][now.c+dc[idx]*multiple]=true;
			}
			else if (arr[now.r+dr[idx]*multiple][now.c+dc[idx]*multiple]==6) break; //벽에서 break
			multiple++;
		}
		return cnt;
	}
	public static void sol(int[] d) {
		int del=0;
		boolean[][] check=new boolean[n][m];
		for(int i=0;i<list.size();i++) {
			cctv now=list.get(i);
			del+=sum(now,d[i], check);
			switch(now.num) {
			case 1:
				break;
			case 2:
				del+=sum(now, (d[i]+2)%4, check);
				break;
			case 3:
				del+=sum(now,(d[i]+1)%4, check);
				break;
			case 4:
				del+=sum(now,(d[i]+1)%4, check);
				del+=sum(now,(d[i]+2)%4, check);
				break;
			case 5:
				del+=sum(now,(d[i]+1)%4, check);
				del+=sum(now,(d[i]+2)%4, check);
				del+=sum(now,(d[i]+3)%4, check);				
				break;
			}
		}
		if(empty-del<min) min=empty-del;
	}
	public static void dfs(int depth, int fin, int[] d) {
		if(depth==fin) {
			sol(d);
			return;
		}
		for(int i=0;i<4;i++) {
			d[depth]=i;
			dfs(depth+1, fin, d);
		}
	}
	public static void main(String[] args) throws IOException{
		input();
		dfs(0, list.size(), new int[list.size()]);
		System.out.println(min);
	}
}
