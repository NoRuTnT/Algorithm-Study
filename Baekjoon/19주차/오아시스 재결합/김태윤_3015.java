package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 김태윤_3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        long ans=0;
        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            int num = Integer.parseInt(br.readLine());
            long sameNum=0;
            int curr=0;
            while(!stack.isEmpty() && stack.peek()<num){
                if(stack.peek()!=curr){
                    ans+=sameNum*2 + (sameNum*(sameNum-1)/2);
                    sameNum=0;
                }
                curr=stack.pop();
                sameNum++;
            }
            ans+=sameNum + (sameNum*(sameNum-1)/2);
            if(!stack.isEmpty()) ans+=sameNum;
            stack.push(num);
        }
        long sameNum=1;
        int curr=stack.pop();
        while(!stack.isEmpty()){
            if(stack.peek()!=curr){
                ans+=sameNum + (sameNum*(sameNum-1)/2);
                sameNum=0;
            }
            curr=stack.pop();
            sameNum++;
        }
        ans+=(sameNum*(sameNum-1)/2);
        System.out.println(ans);
    }
}
