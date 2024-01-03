import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        long[] nums=new long[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        long[] ans=new long[3];
        long abs=Long.MAX_VALUE;
        for(int i=0;i<n;i++){ // 임의의 한 용액 지정
            int l=i+1, r=n-1; // 투 포인터
            while(l<r){
                long sum = nums[i]+nums[l]+nums[r];
                if(Math.abs(sum)<abs){ // 기록 갱신
                    abs=Math.abs(sum);
                    ans[0]=nums[l];
                    ans[1]=nums[r];
                    ans[2]=nums[i];
                }
                if(sum<0) l++;
                else r--;
            }
        }
        Arrays.sort(ans);
        System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
    }
}
