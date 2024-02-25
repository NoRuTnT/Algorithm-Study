import java.util.Scanner;

public class Baekjoon10830행렬제곱 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long b=sc.nextLong();
		int[][] matrix=new int[n][n];
		int[][] ans=new int[n][n];
		for(int i=0;i<n;i++) {
			ans[i][i]=1; // I로 초기화
			for(int j=0;j<n;j++) {
				matrix[i][j]=sc.nextInt();
			}
		}
		sc.close();
		// 입력 완료
		if(b==1) { // 예외 사항 미리 처리
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(matrix[i][j]%1000+" ");
				}
				System.out.println();
			}
			return;
		}
		// A^2=A*A, A^4=A^2*A^2, A^8=A^4*A^4, ... 요런 식으로 곱해나가면 시간 초과 안 나고 행렬 곱셈 가능
		// b를 2^x 의 합으로 나타내서 합에 필요한 x를 저장. x에 해당하는 값을 exp라 하자ㅏ
		long num=2;
		int exp=1;
		while(num<b) {
			num*=2;
			exp++;
		} // 완료하면 while문 시행 횟수가 exp로 저장. 이 크기만큼의 배열 만들어서 b를 2^x의 합으로 나타낸다
		boolean[] check=new boolean[exp+1];
		int po=exp;
		while(b>0) {
			if(num<=b) {
				b-=num;
				check[po]=true;
			} // 합으로 나타내는 부분
			num/=2;
			po--;
		}
		for(int i=0;i<=exp;i++) {
			if(check[i]) {
				int[][] tmp=new int[n][n];
				for(int j=0;j<n;j++) {
					for(int k=0;k<n;k++) {
						for(int l=0;l<n;l++) {
							tmp[j][k]+=(ans[j][l]*matrix[l][k]);
						}
						tmp[j][k]%=1000;
					}
				}
				for(int j=0;j<n;j++) {
					ans[j]=tmp[j].clone();
				}
			}
			int[][] tmp=new int[n][n];
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) { // 행렬곱 좌표정하기
					for(int l=0;l<n;l++) { // 실제 곱을 위해서
						tmp[j][k]+=(matrix[j][l]*matrix[l][k]);
					}
					tmp[j][k]%=1000;
				}
			}
			for(int j=0;j<n;j++) {
				matrix[j]=tmp[j].clone(); // matrix 갱신
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
	}
}
