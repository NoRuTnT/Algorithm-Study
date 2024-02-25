package aps스터디5주차;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 김태윤_16954 {
	static class Node{
		int r;
		int c;
		Node(){}
		Node(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		char[][] board=new char[8][8];
		int[] dr= {-1,-1,-1,0,1,1,1,0};
		int[] dc= {-1,0,1,1,1,0,-1,-1};
		
		for(int i=0;i<8;i++) {
			board[i]=sc.next().toCharArray();
		}
		Queue<Node> q=new LinkedList<>();
		q.offer(new Node(7,0)); // 왼쪽 아랫칸
		while(!q.isEmpty()) {
			boolean[][] isVisited=new boolean[8][8];
			//# 떨어지고나서 상황 바뀌니까 원래 있던데로 또 가야 할 수도 있어서
			//while 한번 돌 때마다 isVisited 초기화
			
			//1) 욱제 움직이기 먼저
			int len=q.size();
			for(int i=0;i<len;i++) {
				Node curr=q.poll();
				isVisited[curr.r][curr.c]=true;
				if(board[curr.r][curr.c]=='#') continue; // 벽이 2)에서 떨어진 경우
				q.offer(curr);
				if(curr.r==0 && curr.c==7) {
					System.out.println(1);
					return; // 어떤 방법으로든 우측 상단 도착하면 무조건 1 출력하고 종료
					// 0 출력 조건: while문 끝날때까지
				}
				isVisited[curr.r][curr.c]=true;
				for(int k=0;k<8;k++) {
					int r=curr.r+dr[k];
					int c=curr.c+dc[k];
					if(r<0 || r>=8 || c<0 || c>=8) continue; // 경계조건
					if(isVisited[r][c]) continue; // 중복
					if(board[r][c]=='#') continue; // 가려는 곳이 벽인 경우
					q.offer(new Node(r,c));
					isVisited[r][c]=true;
				}
			}
			//2) # 떨어지기
			for(int i=7;i>=0; i--) {
				for(int j=0;j<8;j++) {
					if(board[i][j]=='#') {
						board[i][j]='.';
						if(i==7) continue;
						board[i+1][j]='#';
					}
				}
			}
		}
		System.out.println(0);
	}
}

// 89퍼 틀렸습니다 -> 제자리 서 있는 경우가 있습니다