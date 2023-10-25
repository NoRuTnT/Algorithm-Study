import java.util.Scanner;

public class 김태윤_18808 {
	public static int ans=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int num=sc.nextInt();
		int[][] sticker;
		boolean[][] laptop=new boolean[n][m];
		for(int i=0;i<num;i++) {
			//스티커를 num개만큼 입력받는데, 1번 입력받으면 노트북에 붙일거임
			int r=sc.nextInt();
			int c=sc.nextInt();
			sticker=new int[r][c];
			for(int j=0;j<r;j++) {
				for(int k=0;k<c;k++) {
					sticker[j][k]=sc.nextInt();
					//sticker의 (j,k) 위치에서의 값
				}
			}
			//1. 0도, 90도, 180도, 270도 회전
			//2. 붙이는 곳을 좌상단이 (0,0)에서 (0,1), ... (0,m-c-1), (1,0), ... (n-r-1,m-c-1)로 돌기
			//3. 1, 2 과정 반복하면서 매번 붙일 수 있는지 체크, 붙일 수 있으면 붙이고 break
			stick: for(int j=0;j<4;j++) { // 회전
				if(j>0) {
					//스티커를 회전시키면 row, col값이 서로 바뀐다
					int tmp=r;
					r=c;
					c=tmp;
					sticker=rotate(sticker, r, c); // j에 따라 회전시켜준다. 원배열 갱신, 무조건 90도씩 돌려준다
				}
				for(int k=0; k<n-r+1; k++) { // r 변화
					for(int l=0; l<m-c+1; l++) { // c 변화
						if(stickable(sticker, laptop, r, c, k, l)) {
							stickOnLaptop(sticker, laptop, r, c, k, l);
							break stick;
						}
					} // l end
				} // k end
			}// stick end
		}//i end
		System.out.println(ans);
	}
	public static int[][] rotate(int[][] sticker, int r, int c){
		int[][] rotated=new int[r][c]; // 회전된 스티커의 껍데기
		//좌하->좌상->우상->우하->좌하
		//기존 배열을 기준으로 for문을 돌린다
		for(int i=0;i<c;i++) {
			for(int j=0;j<r;j++) {
				rotated[j][c-i-1]=sticker[i][j];
			}
		}
		return rotated; // 이 값으로 원래 배열을 갱신시켜준다
	}
	
	public static boolean stickable(int[][] sticker, boolean[][] laptop, int r, int c, int k, int l) {

		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				//스티커의 (i,j)칸 과 비교하는 노트북의 칸은 (i+k, j+l)이 되어야 한다
				if(sticker[i][j]==1 && laptop[i+k][j+l]) return false;
			}
		}
		return true;
	}
	
	public static void stickOnLaptop(int[][] sticker, boolean[][] laptop, int r, int c, int k, int l) {
		for(int i=0; i<r; i++) {
			for(int j=0;j<c;j++) {
				if(sticker[i][j]==1) {
					laptop[i+k][j+l]=true;
					ans++;
				}
			}
		}
	}
}
