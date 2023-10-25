// 9935. 문자열 폭발
// 스택 안씀
import java.util.Scanner;

public class Main8 {
	public static String sentence, key;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sentence = sc.next();
		key = sc.next();
		int M = key.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0 ; i<sentence.length() ; i++) {
			sb.append(sentence.charAt(i));
			if(sb.length()<M) continue;
			// sb에 담긴 글자수가 key단어의 글자 개수보다 작으면 skip
			if(sb.substring(sb.length()-M, sb.length()).equals(key))
				sb.setLength(sb.length()-M);
			// sb의 마지막 끝에 M개의 글자를 비교해서 일치하면 폭발
		}
		if(sb.length()==0) sb.append("FRULA");
		System.out.println(sb.toString());
	}
}
