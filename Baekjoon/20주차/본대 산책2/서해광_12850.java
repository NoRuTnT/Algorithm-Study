// 12850. 본대 산책2
import java.util.Scanner;

public class Main {
	static long NUM = 1_000_000_007L;
	public static void main(String[] args) {
		long[][] graph= {
				{0L, 1L, 1L, 0L, 0L, 0L, 0L, 0L},
				{1L, 0L, 1L, 1L, 0L, 0L, 0L, 0L},
				{1L, 1L, 0L, 1L, 0L, 1L, 0L, 0L},
				{0L, 1L, 1L, 0L, 1L, 1L, 0L, 0L},
				{0L, 0L, 0L, 1L, 0L, 1L, 1L, 0L},
				{0L, 0L, 1L, 1L, 1L, 0L, 0L, 1L},
				{0L, 0L, 0L, 0L, 1L, 0L, 0L, 1L},
				{0L, 0L, 0L, 0L, 0L, 1L, 1L, 0L}
		};
		Scanner sc=new Scanner(System.in);
		long D=sc.nextLong();
		long[][]result=multiple(D, graph);
		System.out.println(result[0][0]);
	}
	public static long[][] multiple(long pow, long[][]graph) {
		if(pow==1) {
			return graph;
		}
		long[][]half=multiple(pow/2, graph);
		long[][]arr2=new long[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				for(int k=0;k<8;k++) {
					arr2[i][j]+=(half[i][k]*half[k][j])%NUM;
				}
				arr2[i][j]%=NUM;
			}
		}
		if(pow%2==0) return arr2;
		long[][]arr3=new long[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				for(int k=0;k<8;k++) {
					arr3[i][j]+=(graph[i][k]*arr2[k][j])%NUM;
				}
				arr3[i][j]%=NUM;
			}
		}
		return arr3;
	}
}
