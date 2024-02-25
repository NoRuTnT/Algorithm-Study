import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//Trie 구조를 구현해서 풀어보자 
//https://codingnojam.tistory.com/40
public class 김태윤_5052 {
	static class Node{
		//자식 노드
		Map<Character, Node> childNode= new HashMap<>();
		//단어 끝인지 아닌지 판별
		boolean endOfWord=false;
		Node(){}
		Node(Map<Character, Node> childNode, boolean endOfWord){
			this.childNode=childNode;
			this.endOfWord=endOfWord;
		}
	}
	static class Trie{
		Trie(){}
		
		//기본적인 root Node
		Node rootNode=new Node();
		
		//문자열 저장
		void insert(String str) {
			Node node=this.rootNode;
			
			for(int i=0;i<str.length();i++) {
				//자식 노드가 없으면, 자식 노드 새로 생성
				node=node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
			}
			//for문 다 돌면 마지막임 => 끝임을 명시
			node.endOfWord=true;
		}
		
		//문자열 검색
		boolean search(String str) {
			Node node = this.rootNode;
			
			//문자열의 각 단어마다 노드가 존재하는지 체크
			for(int i=0;i<str.length();i++) {
				//매핑된 노드가 있으면 가져오고, 아니면 null을 가져옴
				node=node.childNode.getOrDefault(str.charAt(i), null);
				//가져온 게 없으면 return false : 문자열이 겹치지 않음
				if(node==null) {
					return false;
				}
			}
			
			// 문자열의 마지막 단어까지 매핑된 노드가 존재한다고해서 무조건 문자열어 존재하는게 아님
			// busy를 Trie에 저장했으면, bus의 마지막 s단어에 매핑 된 노드는 존재하지만 Trie에 저장된건 아님
			// 그러므로 현재 노드가 단어의 끝인지 아닌지 체크하는 변수로 리턴
			return node.endOfWord;
		}
		
		//문자열이 접두어를 가지고 있는지 아닌지 판별 <- 문제 풀이를 위해 만든 메서드
		boolean hasHeader(String str) {
			Node node=this.rootNode;
			int idx=0;
			while(!node.endOfWord) {
				node=node.childNode.get(str.charAt(idx++));
				//이미 trie에 넣은거만 탐색하니까 getordefault할 필요 없음
			}
			//만약 접두어 가지고 있으면 idx 크기가 str보다 작을 것
			return (idx<str.length());
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		StringBuilder ans=new StringBuilder();
		for(int tc=0;tc<t;tc++) {
			Trie phoneNumTrie=new Trie();
			int n=Integer.parseInt(br.readLine());
			String[] phoneNumArr=new String[n];
			
			for(int i=0;i<n;i++) {
				String s=br.readLine();
				phoneNumTrie.insert(s); 
				phoneNumArr[i]=s;
			}
			Arrays.sort(phoneNumArr,new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if(o1.length()>o2.length()) return -1;
					else if(o1.length()<o2.length()) return 1;
					else return 0;
				}
			});
			boolean flag=true;
			for(int i=0;i<n;i++) {
				String s=phoneNumArr[i];
				if(phoneNumTrie.hasHeader(s)) {
					flag=false;
					break;
				}
			}
			ans.append(flag?"YES":"NO").append("\n");
		}
		System.out.println(ans.toString());
	}
}
