package Week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_2412 {
	static class Node {
		int x;
		int y;
		int move;
		Node(int x, int y, int move){
			this.x=x;
			this.y=y;
			this.move=move;
		}
	}
	static int n, t;
	static List<Integer>[] rocks;
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(bfs());
	}
	private static int bfs(){
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0,0, 0));
		while(!queue.isEmpty()){
			Node curr=queue.poll();
			if(curr.y==t) return curr.move;
			for(int y=curr.y-2; y<=curr.y+2; y++){
				if(y<0 || y>t || rocks[y].isEmpty()) continue;
				for(int j=rocks[y].size()-1; j>=0; j--){
					int x= rocks[y].get(j);
					if(x<curr.x-2 || x>curr.x+2) continue;
					queue.add(new Node(x, y, curr.move+1));
					rocks[y].remove(j);
				}
			}
		}
		return -1;
	}
	private static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		rocks=new ArrayList[t+1];
		for(int i=0; i<=t; i++){
			rocks[i]=new ArrayList<>();
		}
		for(int i=1; i<=n; i++){
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			rocks[y].add(x);
		}
		for(int i=0; i<=t; i++){
			Collections.sort(rocks[i]);
		}
		br.close();
	}
}
