// 20541. 앨범정리
// 이제부터 54퍼센트 시간초과 해소하기 작업
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N;
	static BufferedWriter bw;
	static class Node implements Comparable<Node>{
		String name;
		Node parentNode;
		TreeSet<Node> dir;
		TreeSet<String> pic;
		@Override
		public int compareTo(Node o) {
			return this.name.compareTo(o.name);
		}
		public Node(String name, Node parentNode) {
			this.name = name;
			this.parentNode = parentNode;
			// 아예 트리 셋을 사진, 디렉토리 2가지로 만드는 경우로 바꿔버리기
			dir=new TreeSet<Node>();
			pic=new TreeSet<String>();
		}
	}
	static int[]nums;
	static Node albumNode;
	static Node currNode;
	public static void main(String[] args) throws Exception{
		albumNode=new Node("album", albumNode);
		currNode=albumNode;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		String S="";
		for(int tc=0;tc<N;tc++) {
			st=new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "mkalb":
				S=st.nextToken();
				mkalb(S);
				break;
			case "rmalb":
				S=st.nextToken();
				rmalb(S);
				break;
			case "insert":
				S=st.nextToken();
				insert(S);
				break;
			case "delete":
				S=st.nextToken();
				delete(S);
				break;
			case "ca":
				S=st.nextToken();
				ca(S);
				break;
			}
		}
		bw.flush();
		bw.close();
	}
	public static void mkalb(String S) throws IOException {
		if(currNode.dir.contains(new Node(S, currNode))) {
			bw.write("duplicated album name\n");
			return;		
		}
//		for(Node n:currNode.dir) {
//			if(n.name.equals(S)) {
//				// 똑같이 photo name으로 썼음 ...
//				bw.write("duplicated album name\n");
//				return;
//			}
//		}
		Node childNode=new Node(S, currNode);
		currNode.dir.add(childNode);
	}
	public static void rmalb(String S) throws IOException {
		// 명령어 수행후: 삭제된 앨범의 개수와 사진의 개수를 공백으로 구분하여 출력합니다.
		nums=new int[2];
		if(S.charAt(0)-'0'<=1) { // -1, 0, 1
//			int num=S.charAt(0)-'0'; // -가 읽히기 때문에 -1파싱 불가능
			int num=Integer.parseInt(S);
			if(num==-1) {
				if(currNode.dir.size()>0) {
					rmDfs(currNode.dir.first());
					currNode.dir.pollFirst();
				}
			}else if(num==0) { // all
				for(Node child:currNode.dir) {
					rmDfs(child);
				}
				currNode.dir.clear();
			}else { // rmalb 1
				if(currNode.dir.size()>0) {
					rmDfs(currNode.dir.last());
					currNode.dir.pollLast();
//					nums[0]++;
				}
			}
		}else {
			for(Node n:currNode.dir) {
				if(n.name.equals(S)) {
					rmDfs(n);
					currNode.dir.remove(n);
					break;
				}
			}			
		}
//		bw.write("rm "+S+"의 결과물");
		bw.write(String.format("%d %d\n",nums[0], nums[1]));
	}
	public static void insert(String S) throws IOException {
		if(currNode.pic.contains(S)) {
			bw.write("duplicated photo name");
			bw.newLine();
			return;
		}
//		for(String name : currNode.pic) {
//			if(name.equals(S)) {
//				bw.write("duplicated photo name");
//				bw.newLine();
//				return;
//			}
//		}
		currNode.pic.add(S);
	}
	public static void delete(String S) throws IOException {
		int pictureNum=0;
		if(S.charAt(0)-'0'<=1) { // -1, 0, 1
			int num=Integer.parseInt(S);
			if(num==-1) {
				if(currNode.pic.size()>0) {
					currNode.pic.pollFirst();
					pictureNum++;
				}
			}else if(num==0) {
				pictureNum+=currNode.pic.size();
				currNode.pic.clear();
			}else {
				if(currNode.pic.size()>0) {
					currNode.pic.pollLast();
					pictureNum++;
				}
			}
		}else {
			for(String picName:currNode.pic) {
				if(picName.equals(S)) {
					currNode.pic.remove(S);
					pictureNum++;
					break;
				}
			}
		}
		bw.write(pictureNum+"\n");
//		bw.newLine();
	}
	public static void ca(String S) throws IOException {
		if(S.equals("/")) {
			currNode=albumNode;
		}else if(S.equals("..")) {
			if(!currNode.name.equals("album"))
			currNode=currNode.parentNode; // album의 상위노드를 album으로 지정함
		}else {
			for(Node n : currNode.dir) {
				if(n.name.equals(S)) {
					currNode=n;
					break;
				}
			}
		}
//		System.out.println("ca명령어를 수행했고 현재 노드는 "+currNode.name);
		bw.write(currNode.name);
		bw.newLine();
	}
	// 들어온 node자신과 그 아래 모든 내용을 삭제
	
	// case2의 rmalb japanese이 안됨
	// 이게 남아서 맨 마지막 테스트케이스가 1 더 생기는 거임
	public static void rmDfs(Node node) {
//		System.out.println("현재 탐색중인 디렉토리"+ node.name);
		nums[1]+=node.pic.size();
		node.pic.clear();
//		nums[0]+=node.dir.size();
		for(Node child : node.dir) {
			rmDfs(child);
		}
		node.dir.clear();
		nums[0]++;
	}
}
