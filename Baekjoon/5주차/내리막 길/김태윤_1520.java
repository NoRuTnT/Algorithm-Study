package aps스터디5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김태윤_1520 {
	static class Node{
		int r;
		int c;
		int val;
		Node(){}
		Node(int r, int c, int val){
			this.r=r;
			this.c=c;
			this.val=val;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int m=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		int[][] arr=new int[m+2][n+2]; // 상하좌우로 0 한줄씩 여백
		int[][] ans=new int[m+2][n+2];
		boolean[][] isVisited = new boolean[m+2][n+2];
		int[] dr= {-1,0,0,1};
		int[] dc= {0,1,-1,0};
		for(int i=1;i<=m;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<Node> pq=new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) { // val의 오름차순으로 정렬
				if(o1.val<o2.val) return 1; 
				else if(o1.val>o2.val) return -1; 
				return 0;
			}
			
		});
		pq.offer(new Node(1,1,arr[1][1])); // 초기화
		ans[1][1]=1; // 처음엔 1개의 루트
		isVisited[1][1]=true;
		while(!pq.isEmpty()) {
			Node curr=pq.poll();
			
			for(int k=0;k<4;k++) {
				int r=curr.r+dr[k];
				int c=curr.c+dc[k];
				if(arr[r][c]==0) continue; // 경계면 continue
				
				//1) 주변에 더 높은데 있으면 경로 개수 더해주기
				if(arr[r][c]>arr[curr.r][curr.c]) {
					ans[curr.r][curr.c]+=ans[r][c];
				}
				//2) 주변에 더 낮은데 있으면 다음 이동으로 고려해주기
				if(arr[r][c]<arr[curr.r][curr.c]) { 
					if(isVisited[r][c]) continue;
					pq.offer(new Node(r,c,arr[r][c]));
					isVisited[r][c]=true;
				}
			}
			if(curr.r==m && curr.c==n) break; // 도착지점 도달했을 시 더이상 탐색 x
		}
		System.out.println(ans[m][n]);
	}
}
