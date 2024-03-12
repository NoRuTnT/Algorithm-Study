import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static class TrieNode {
        Map<String, TrieNode> childNode = new HashMap<>();

        TrieNode() {
        }

        public void insert(String str) {
            TrieNode trieNode = this;
            String[] arr = str.split("\\\\");
            for (String s : arr) {
                trieNode.childNode.putIfAbsent(s, new TrieNode());
                trieNode = trieNode.childNode.get(s);
            }
        }

        public void print(TrieNode cur, int depth) {
            if (cur.childNode != null) {
                List<String> list = new ArrayList<>(cur.childNode.keySet());
                Collections.sort(list);
                for (String str : list) {
                    for (int i = 0; i < depth; i++) {
                        sb.append(" ");
                    }
                    sb.append(str).append("\n");
                    print(cur.childNode.get(str), depth + 1);
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        TrieNode trie = new TrieNode();
        for (int i = 0; i < n; i++) {
            trie.insert(br.readLine());
        }

        trie.print(trie, 0);

        System.out.println(sb);

    }
}
