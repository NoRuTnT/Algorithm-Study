package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 김태윤_2533 {
    static int n;
    static ArrayList<Integer>[] adjList;
    static int[][] dp;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        init();
        solve(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    public static void init() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        adjList = new ArrayList[n+1];
        dp=new int[n+1][2];
        isVisited=new boolean[n+1];
        for(int i=1;i<=n;i++){
            adjList[i]=new ArrayList<>();
        }
        StringTokenizer st;
        for(int i=1;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int num1=Integer.parseInt(st.nextToken());
            int num2=Integer.parseInt(st.nextToken());
            adjList[num1].add(num2);
            adjList[num2].add(num1);
        }
        br.close();
    }
    public static void solve(int curr){
        dp[curr][1]=1; // 내가 얼리어답터
        isVisited[curr]=true;
        for(int i=0;i<adjList[curr].size();i++){
            int next=adjList[curr].get(i);
            if(isVisited[next]) continue;
            solve(next);
            dp[curr][0]+=dp[next][1];
            dp[curr][1]+=Math.min(dp[next][0], dp[next][1]);
        }
    }
}
