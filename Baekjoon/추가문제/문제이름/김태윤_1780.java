import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 김태윤_1780 {
	static int zero=0;
	static int one=0;
	static int minusOne=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		String[][] paper=new String[n][n];
		for(int i=0;i<n;i++) {
			paper[i]=br.readLine().split(" ");
		}
		cut(0, 0, paper, n);
		System.out.println(minusOne);
		System.out.println(zero);
		System.out.println(one);
	}
	public static void cut(int r, int c, String[][] paper, int n) {
		String num=paper[r][c]; // 맨 처음 숫자랑 같은 값 나와야 함
		if(n==1) {
			judge(num);
			return;
		}
		boolean flag=false; // 다른 종이 나오면 break
		search: for(int i=r;i<r+n;i++) {
			for(int j=c;j<c+n;j++) {
				if(!num.equals(paper[i][j])) {
					flag=true;
					break search;
				}
			}
		}
		if(flag) { //색종이 더 잘라야함
			for(int i=r;i<r+n;i+=n/3) {
				for(int j=c;j<c+n;j+=n/3) {
					cut(i,j,paper,n/3);
				}
			}
		}
		else {
			judge(num);
		}
	}
	public static void judge(String num) {
		switch(num) {
		case "1": 
			one++;
			break;
		case "0":
			zero++;
			break;
		case "-1":
			minusOne++;
			break;
		}
	}
}
