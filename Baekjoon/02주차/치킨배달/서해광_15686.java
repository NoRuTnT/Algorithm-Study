// 15686. 치킨 배달

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, ans;
	public static int[][] arr;
	public static boolean[] visited;
	public static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	public static ArrayList<Node> list, homeList;
	public static int[][] cList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		ans = Integer.MAX_VALUE;
		list = new ArrayList<>(); // 치킨집 위치를 저장
		cList = new int[M][2]; // M개 선택한 치킨집 위치
		homeList = new ArrayList<>(); // 이것까지 필요(N^2개 직접 찾아보면 시간초과 발생)
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) {
					list.add(new Node(i, j));
					arr[i][j]=0;
				}else if(arr[i][j]==1) {
					homeList.add(new Node(i, j));
				}
			}
		} // 입력 완료
		visited = new boolean[list.size()];
		comb(0, 0);
		
		System.out.println(ans);
	} // main
	
	public static void comb(int cnt, int st) {
		if(cnt==M) {
			getLeng();
			return;
		}
		for(int i=st ; i<list.size() ; i++) {
			visited[i]=true;
//			cList[cnt][0]= list.get(i).r;
//			cList[cnt][1]= list.get(i).c;
			comb(cnt+1, i+1);
			visited[i]=false;
		}
	}
	
	public static void getLeng() {
		int cLen = 0;
		for(int i=0 ; i<homeList.size() ; i++) {
			int tmpMinimum = Integer.MAX_VALUE;
			for(int k=0 ; k<list.size() ; k++) {
				if(visited[k]) {
					tmpMinimum=Math.min(tmpMinimum, Math.abs(list.get(k).r-homeList.get(i).r)+Math.abs(list.get(k).c-homeList.get(i).c));
				}
			}
			cLen+=tmpMinimum;
		}
		ans = Math.min(ans, cLen);
	}
}
