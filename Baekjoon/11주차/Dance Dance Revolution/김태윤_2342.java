import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_2342 {

    static int[] nums=new int[100001];
    static int len=100001;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=1;i<=100000;i++){
            nums[i]=Integer.parseInt(st.nextToken());
            if(nums[i]==0) {
                len=i;
                break;
            }
        }
        br.close();
        if(len==1){ // 반례 가지치기
            System.out.println(0);
            return;
        }

        //dp 초기화
        dp=new int[len][5][5]; // 스텝, 왼발, 오른발
        // 맨 처음은 가운데에서 왼발 옮기거나, 오른발 옮기거나 -> 초기값 설정
        dp[1][nums[1]][0]=2;
        dp[1][0][nums[1]]=2;

        for(int step=2; step<len;step++){
            for(int left=0; left<=4; left++){
                for(int right=0;right<=4;right++){
                    if(dp[step-1][left][right]>0){
                        if(dp[step][nums[step]][right]==0){
                            dp[step][nums[step]][right]=dp[step-1][left][right]+move(left,nums[step]);
                        }
                        else{
                            dp[step][nums[step]][right]=Math.min(dp[step][nums[step]][right], dp[step-1][left][right]+move(left,nums[step]));
                        }
                        if(dp[step][left][nums[step]]==0){
                            dp[step][left][nums[step]]=dp[step-1][left][right]+move(right,nums[step]);
                        }
                        else{
                            dp[step][left][nums[step]]=Math.min(dp[step][left][nums[step]], dp[step-1][left][right]+move(right,nums[step]));
                        }
                    }
                }
            }
        }

        //답안 찾기
        int ans = Integer.MAX_VALUE;
        for(int left=0;left<=4;left++){
            for(int right=0;right<=4;right++){
                if(dp[len-1][left][right]==0) continue;
                if(ans>dp[len-1][left][right]) ans=dp[len-1][left][right];
            }
        }

        System.out.println(ans);
    }


    public static int move(int from, int to){
        if(from==0) return 2; // 가운데 있었을 경우 무조건 2만큼
        if(from==to) return 1; // 제자리
        if(Math.abs(from-to)==2) return 4; // 맞보는 방향
        return 3; // 인접한 칸
    }
}
