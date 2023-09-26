// 16637. 괄호 추가하기
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int N, M, ans;
	public static String str;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		str = sc.next();
		ans = Integer.MIN_VALUE;
		M = (N+1)/2; // 주어진 식의 항의 개수
		makeBr(new boolean[M], 0);
		System.out.println(ans);
	}
	public static void makeBr(boolean[] chk, int idx) {
		if(idx>M-2) {
			boolean[] chk2 = new boolean[M];
			for(int i=0 ; i<M ; i++) {
				chk2[i] = chk[i];
			}
			calculate(chk2);
			return;
		}
		chk[idx] = true;
		makeBr(chk, idx+2);
		chk[idx] = false;
		makeBr(chk, idx+1);
	}
	public static void calculate(boolean[] chk) {
		StringBuilder sb = new StringBuilder();
		List<Integer> num1 = new ArrayList<>();
		List<Character> op1 = new ArrayList<>();
		for(int i=0 ; i<str.length() ; i++) {
			if(i%2==0)
				num1.add(str.charAt(i)-'0');
			else
				op1.add(str.charAt(i));
		}
		Queue<Integer> num2 = new LinkedList<>();
		Queue<Character> op2 = new LinkedList<>();
		int idx=0;
		while(idx<M) {
			if(chk[idx]) {
				int tmp=operate(num1.get(idx), num1.get(idx+1), op1.get(idx));
				num2.add(tmp);
				if(idx!=M-2)
					op2.add(op1.get(idx+1));
				idx++;
			}else {
				num2.add(num1.get(idx));
				if(idx!=M-1)
					op2.add(op1.get(idx));
			}
			idx++;
		}
		int result = num2.remove();
		while(!op2.isEmpty()) {
			result = operate(result, num2.remove(), op2.remove());
		}
		ans = Math.max(result, ans);
	}
	public static int operate(int a, int b, char op) {
		if(op=='+') return a+b;
		if(op=='-') return a-b;
		if(op=='*') return a*b;
		return -1;
	}
	
}
