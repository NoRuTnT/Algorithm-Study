// 5052. 전화번호 목록
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static int t, n;
	public static String ans;
	// 트라이 구현부
	static class TrieNode{
		Map<Character, TrieNode> childNode = new HashMap<Character, TrieNode>();
		boolean isTerminal;
		public TrieNode() {
		}
	}
	static class Trie{
		public Trie() {
		}
		TrieNode rootNode = new TrieNode();
		// 1 저장부분
		public void insert(String word) {
			TrieNode node = this.rootNode;
			for(int i=0 ; i<word.length() ; i++) {
				char c = word.charAt(i);
				node.childNode.putIfAbsent(c, new TrieNode());
				node = node.childNode.get(c);
				if(i == word.length()-1) {
					node.isTerminal = true;
				}
			}
		}
		// 2 검색부분
		public int search(String word) {
			TrieNode node = this.rootNode;
			int cnt = 0;
			for(int i=0 ; i<word.length() ; i++) {
				char c = word.charAt(i);
				node = node.childNode.get(c);
				if(node.isTerminal) cnt++;
			}
			// 반환값은 terminal을 만난 개수.
			// 이 값이 2이상이면 접두사가 겹치는 경우가 존재
			return cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		for(int tc=0 ; tc<t ; tc++) {
			n = Integer.parseInt(br.readLine());
			Trie trie = new Trie();
			List<String> words = new ArrayList<>();
			for(int i=0 ; i<n ; i++) {
				ans = "YES";
				String phone = br.readLine();
				trie.insert(phone);
				words.add(phone);
			}
			for(int i=0 ; i<n ; i++) {
				if(trie.search(words.get(i))>1) {
					ans = "NO";
					break;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
}
