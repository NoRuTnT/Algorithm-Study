package Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 김태윤_14725 {
	static class TrieNode{
		Map<String, TrieNode> childNode = new TreeMap<>();
		TrieNode(){}

		public void insert(String[] inputs){
			TrieNode trieNode = this;
			for(String word: inputs){
				trieNode.childNode.putIfAbsent(word, new TrieNode());
				trieNode = trieNode.childNode.get(word);
			}
		}

		public void print(TrieNode curr, int depth){
			if(curr.childNode.isEmpty()) return;
			for(String str : curr.childNode.keySet()){
				for(int i=0; i<depth; i++){
					sb.append("--");
				}
				sb.append(str+"\n");
				print(curr.childNode.get(str), depth+1);
			}
		}
	}
	static int n;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		TrieNode trie = new TrieNode();
		StringTokenizer st;
		for(int i=0; i<n; i++){
			st=new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			String[] strings = new String[m];
			for(int j=0; j<m; j++){
				strings[j]=st.nextToken();
			}
			trie.insert(strings);
		}
		trie.print(trie, 0);
		System.out.print(sb.toString());
	}

}
