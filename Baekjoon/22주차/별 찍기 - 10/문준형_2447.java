import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int n;
	static String[][] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n= Integer.parseInt(br.readLine());
		arr = new String[n][n];
		for(int i=0;i<n;i++) {
			Arrays.fill(arr[i]," ");
		}
		star(0,0,n);
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				bw.write(arr[i][j]);
			}
			bw.write("\n");
		}
		bw.flush();
		
	}

	private static void star(int a, int b, int n) {
		if(n==1) {
			arr[a][b]="*";
			return;
		}
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(!(i==1 && j==1)) {
					star(a+(i*(n/3)), b+(j*(n/3)), n/3);
				}
			}
		}
		
	}

}
