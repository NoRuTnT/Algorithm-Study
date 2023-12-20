import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//이분탐색
public class 김태윤_10942 {
    static int[] nums;
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        nums = new int[n+1];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        //palindrome 모든 경우 미리 찾아놓기
        boolean[][] dp=new boolean[n+1][n+1];
        for(int end=1;end<=n;end++){
            dp[end][end]=true; // 길이 1인 팰린드롬 (자기자신)
            if(nums[end-1]==nums[end]) dp[end-1][end]=true; // 길이 2인 팰린드롬
            for(int start=end-2;start>0;start--){
                if(dp[start+1][end-1] && nums[start]==nums[end]){
                    dp[start][end]=true; // start~end까지인 팰린드롬
                }
            }
        }
        int m=Integer.parseInt(br.readLine());
        StringBuilder ans=new StringBuilder();
        for(int tc=1;tc<=m;tc++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            if(dp[start][end]) ans.append("1\n");
            else ans.append("0\n");
        }
        System.out.println(ans.toString());
    }
}
