import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 김태윤_16637 {
	static int n;
	static char[] eq;
	static int max=Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		eq=sc.next().toCharArray();
		//짝수번 index는 숫자, 홀수번은 기호
		//기호를 기준으로 괄호를 넣을지 말지 설정 -> check배열
		comb(1, new boolean[n]);
		System.out.println(max);
	}
	public static void comb(int idx, boolean[] check) {
		if(idx==n) { // 식 계산
			calc(check);
			return;
		}
		comb(idx+2, check); // check=false
		if(idx-2<0 || !check[idx-2]) check[idx]=true; //괄호 하나에 연산자 하나만 필요해서 넣어준 조건
		comb(idx+2, check); // check=true
		check[idx]=false;
	}
	public static void calc(boolean[] check) {
		Queue<Integer> nums=new LinkedList<>(); // 계산할 수를 넣어두는 queue -> 앞에서부터 순서대로 계산
		for(int i=0;i<n;i+=2) {
			nums.offer(eq[i]-'0'); // queue에 일단 채워놓기
		}
		
		int beforeCalc=nums.poll(); // 맨 앞 수를 일단 꺼내놓는다
		
		for(int i=1;i<n;i+=2) {
			//1) 우선 괄호 쌓인거만 먼저 계산하기
			if(check[i]) {
				int nextCalc=nums.poll();
				beforeCalc=two(beforeCalc, nextCalc, i); // 계산한 값 저장
			}
			else {
				nums.offer(beforeCalc); // 맨 뒤에 다시 넣어놓는다
				beforeCalc=nums.poll(); // 새로운걸 꺼내서 갱신
			}
		}
		nums.offer(beforeCalc); // 맨마지막거 다시 넣어줌
		beforeCalc=nums.poll(); // 계산 안 한거중 맨앞 꺼내옴
		for(int i=1;i<n;i+=2) {
			//2) 괄호 없는거 계산하기
			if(!check[i]) {
				int nextCalc=nums.poll();
				beforeCalc=two(beforeCalc, nextCalc, i);
			}
		}
		if(beforeCalc>max) {
			max=beforeCalc;
		}
	}
	public static int two(int beforeCalc, int nextCalc, int i) {
		int ans=0;
		switch(eq[i]) {
		case '+': ans=beforeCalc+nextCalc; break;
		case '-': ans=beforeCalc-nextCalc; break;
		case '*': ans=beforeCalc*nextCalc; break;
		}
		return ans;
	}
}
