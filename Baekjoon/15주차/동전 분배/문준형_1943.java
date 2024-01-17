import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] money;
    static int[] dp;
    static int n,target;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int tc=1;tc<=3;tc++) {
            n = Integer.parseInt(br.readLine());
            money = new int[n][2];
            dp = new int[50001];
            dp[0]=1;
            int total =0;
            target =0;
            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                money[i][0] = Integer.parseInt(st.nextToken());
                money[i][1] = Integer.parseInt(st.nextToken());
                int sum = money[i][0]*money[i][1];
                total += sum;
            }
            if(total%2==1) {
                System.out.println(0);
                continue;
            }
            target = total/2;
            System.out.println(makedp());

        }
    }
    private static int makedp() {
        for(int i=0;i<n;i++) {
            for(int j=50000;j>=money[i][0];j--) {
                if(dp[j-money[i][0]]!=0){
                    for(int k=0;k<money[i][1];k++){
                        if(j+money[i][0]*k <= 50000){
                            dp[j+money[i][0]*k]=1;
                        }
                    }
                }
            }
        }
        if(dp[target]!=0) {
            return 1;
        }else {
            return 0;
        }


    }

}
