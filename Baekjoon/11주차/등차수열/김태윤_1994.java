
import java.util.Arrays;
import java.util.Scanner;

public class 김태윤_1994 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();


        int[] num=new int[n];
        for(int i=0;i<n;i++) {
            num[i] = sc.nextInt();
        }
        if(n==1){
            System.out.println(1);
            return;
        }
        Arrays.sort(num);
        int[][] diff=new int[n][n];

        //두 수 사이의 차이를 먼저 계산
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                diff[i][j]=num[j]-num[i];
            }
        }

        int[][] dp = new int[n][n];
        // index는 각각 이전 수, 현재 수의 위치
        int max=0; // 수열 길이가 갱신되면 max도 갱신
        for(int j=1;j<n;j++){
            for(int i=0;i<j;i++){
                dp[i][j]=2; // 처음엔 무조건 두 수로 가능
                //공차가 0인 경우
                if(diff[i][j]==0){
                    if(i>0 && diff[i-1][i]==0) dp[i][j]=Math.max(dp[i][j], dp[i-1][i]+1);
                    if(dp[i][j]>max) {
                        max=dp[i][j]; // 수열 길이 갱신
                    }
                    continue;
                }

                int l=0;
                int r=i;
                while(l<r){
                    int mid=(l+r)/2;
                    if(diff[mid][i]>diff[i][j]) l=mid+1;
                    else if(diff[mid][i]<diff[i][j]) r=mid;
                    else {
                        l=mid;
                        break;
                    }
                }

                if(diff[l][i]==diff[i][j]) dp[i][j]=Math.max(dp[i][j],dp[l][i]+1);
//                for(int k=i-1;k>=0;k--){
//                    if(diff[k][i]>diff[i][j]) break;
//                    if(diff[k][i]==diff[i][j]){
//                        dp[i][j]=dp[k][i]+1;
//                        break;
//                    }
//                }
                if(dp[i][j]>max) {
                    max=dp[i][j]; // 수열 길이 갱신
                }
            }
        }
        System.out.println(max);
    }
}
