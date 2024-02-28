import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[][] map = new long[8][8];
		//0:정보과학관 1:전산관 2:미래관 3:신양관 4:한경직기념관 5:진리관 6:학생회관 7:형남공학관
		map[0][1]=map[1][0]=map[0][2]=map[2][0]=1;
		map[1][2]=map[2][1]=map[1][3]=map[3][1]=1;
		map[2][3]=map[3][2]=map[2][4]=map[4][2]=1;
		map[3][4]=map[4][3]=map[3][5]=map[5][3]=1;
		map[4][7]=map[7][4]=map[4][5]=map[5][4]=1;
		map[5][6]=map[6][5]=1;
		map[6][7]=map[7][6]=1;
		int d = Integer.parseInt(br.readLine());
		long[][] ans = new long[8][8];
		
		ans = half(map,d);
		System.out.println(ans[0][0]);
				
	}
	
	public static long[][] half(long[][] matrix, int a) {
		if(a==1) {
			return matrix;
		}
		
		if(a%2==0) {
			long[][] halfmatrix = half(matrix,a/2);			
			return pow(halfmatrix, halfmatrix); 
		}else {
			return pow(half(matrix,a-1),matrix);
		}
	}
	
	public static long[][] pow(long[][] matrix1, long[][] matrix2) {
		long[][] resmatrix = new long[8][8];
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				for(int k=0;k<8;k++) {
					resmatrix[i][j] = (resmatrix[i][j]+(matrix1[i][k]*matrix2[k][j]))%1000000007;
				}
			}
		}
		return resmatrix;
	}
	

}
