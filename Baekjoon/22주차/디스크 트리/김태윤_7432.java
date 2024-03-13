package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class 김태윤_7432 {
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
					sb.append(" ");
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
		for(int i=0; i<n; i++){
			String inputs = br.readLine();
			trie.insert(inputs.split("\\\\"));
		}
		trie.print(trie, 0);
		System.out.print(sb.toString());
	}

}
