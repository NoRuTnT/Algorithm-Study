//velog.io/@emplam27

import java.util.Scanner;

public class 김태윤_9251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] word1 = sc.next().toCharArray();
        char[] word2 = sc.next().toCharArray();

        int[][] dp = new int[word1.length][word2.length];
        for(int i=0;i<word1.length;i++){
            for(int j=0;j<word2.length;j++){
                if(word1[i]==word2[j]){
                    if(i==0 || j==0) dp[i][j]=1;
                    else dp[i][j]=dp[i-1][j-1]+1;
                }
                else{ // 경계 조건 따지면서 계산
                    if(i==0 && j==0) dp[i][j]=0;
                    else if(i==0) dp[i][j]=dp[i][j-1];
                    else if(j==0) dp[i][j]=dp[i-1][j];
                    else dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[word1.length-1][word2.length-1]);
    }
}
