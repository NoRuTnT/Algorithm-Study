import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,min;
	static boolean[][] map;
	static int[] popul;
	static ArrayList<Integer> list,list2; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		popul = new int[n];
		int[] city = new int[n];
		for(int i=0;i<n;i++) {
			city[i]=i+1;
		}
		boolean []visit = new boolean[n];
		map = new boolean[n][n];
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for(int i=0;i<n;i++) {
			popul[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j=0;j<num;j++) {
				int a = Integer.parseInt(st.nextToken())-1;
				map[i][a]=true;
				map[a][i]=true;
			}
		}
		min=987654321;
		combination(city,visit,0,n,0);
		if(min==987654321) {
			System.out.println(-1);
		}else {
			System.out.println(min);			
		}
	}

	private static void combination(int[] city, boolean[] visit, int start, int n, int r) {
		if(r>=1 && r<=n/2) {
			ArrayList<Integer> list = new ArrayList<>();
			ArrayList<Integer> list2 = new ArrayList<>();
			int sum =0;
			int sum2 = 0;
			for(int i=0;i<n;i++) {
				if(visit[i]) {
					list.add(city[i]);	
					sum+=popul[city[i]-1];
					
				}else {
					list2.add(city[i]);
					sum2+=popul[city[i]-1];
				}
			}
			if(check(list)&&check(list2)) {
				min = Math.min(min, Math.abs(sum-sum2));
			}
			if(r==n/2) {
				return;
			}
		}
		for(int i=start;i<n;i++) {
			visit[i]=true;
			combination(city,visit,i+1,n,r+1);
			visit[i]=false;
		}
		
	}

	private static boolean check(ArrayList<Integer> list) {
		Queue<Integer>queue = new LinkedList<>();
		boolean[] visit2 = new boolean[n];
		queue.offer(list.get(0)-1);
		visit2[list.get(0)-1]=true;
		int cnt=1;
		while(!queue.isEmpty()){
			int now = queue.poll();
			for(int i=0;i<n;i++) {
				if(map[now][i] && !visit2[i] && list.contains(i+1)) {
					queue.offer(i);
					visit2[i]=true;
					cnt++;
				}
			}		
		}
		if(cnt==list.size()) {
			return true;
		}else {
			return false;			
		}
		
	}
	

}
