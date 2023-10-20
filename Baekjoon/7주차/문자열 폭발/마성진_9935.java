import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static Stack<Character> str;
	public static Stack<Character> tmp;
	public static String origin, pattern;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		str = new Stack<>();
		tmp = new Stack<>();
		origin = br.readLine();
		pattern = br.readLine();
		find();
		while (!str.isEmpty()) {
			sb.append(str.pop());
		}
		if (sb.length() > 0) {
			System.out.println(sb.reverse().toString());
		} else {
			System.out.println("FRULA");
		}

	}

	public static void find() {
		int originLen = origin.length();
		int patternLen = pattern.length();
		for (int i = 0; i < originLen; i++) {
			str.add(origin.charAt(i));
			if (str.peek() == pattern.charAt(patternLen - 1) && str.size() >= patternLen) {
				tmp.add(str.pop());
				for (int j = patternLen-2; j >= 0; j--) {
					tmp.add(str.pop());
					if(tmp.peek() != pattern.charAt(j)) {
						while(!tmp.isEmpty()) {
							str.add(tmp.pop());
						}
						break;
					}
				}
				tmp.clear();
			}
		}
	}

}
