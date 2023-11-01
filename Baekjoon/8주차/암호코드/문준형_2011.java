import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String args[]) throws Exception{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	String str = st.nextToken();
	long[] dp = new long[str.length()+1];
	if (str.charAt(0) == '0') { //암호가 잘못된 경우
	    System.out.println(0);
	    return;
	}
	else {
	    dp[0]=1; //0,1 일때는 그냥설정해줌
	    dp[1]=1;
	    for(int i=2;i<=str.length();i++) {
		if(str.charAt(i-1)=='0') { 
		    if(str.charAt(i-2)=='1'||str.charAt(i-2)=='2') // 10, 20을 제외한 0으로시작하거나 30이상의 10배수 다쳐냄
			dp[i] = dp[i-2]%1000000;
		    else
			break;
		}
		else {
		    int result = Integer.parseInt(str.substring(i-2,i));
		    if (result<27 && result>9)  //숫자를 추가했을때 두자리까지 해석되는경우
			dp[i] = (dp[i-1]+dp[i-2])%1000000;
		    else {					//숫자를 추가했을때 한자리만 해석되는경우
			dp[i] = dp[i-1]%1000000;
		    }
		}
	    }
	}
	System.out.println(dp[str.length()]%1000000);
    }
}

// 









