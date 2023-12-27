import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 김태윤_1799 {
    static class Node{
        int r;
        int c;
        Node(){}
        Node(int r, int c){
            this.r=r;
            this.c=c;
        }
    }
    static int[][] board;
    static boolean[][] chess;
    static int[][] check;
    static int max;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        chess=new boolean[n][n];
        board=new int[n][n];
        check=new int[n][n];
        int blackLeft=0;
        int whiteLeft=0;
        
        ArrayList<Node> black=new ArrayList<>();
        ArrayList<Node> white=new ArrayList<>();
        
        int ans=0;
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int tmp=Integer.parseInt(st.nextToken());
                if(tmp==1) {
                    chess[i][j]=true;
                    if((i+j)%2==0) {
                    	whiteLeft++;
                    	white.add(new Node(i,j));
                    }
                    else {
                    	blackLeft++;
                    	black.add(new Node(i,j));
                    }
                }
            }
        }
        max=0;
        recursion(0,0,0, white, whiteLeft);
        ans+=max;
        max=0;
        recursion(0,0,0, black, blackLeft);
        ans+=max;
        System.out.println(ans);
    }
    public static void recursion(int num, int cnt, int sum, ArrayList<Node> list, int available){
        if(num==list.size()) {
            if(max<sum) max=sum;
            return;
        }
        int r=list.get(num).r;
        int c=list.get(num).c;
        if(available - cnt +sum<= max) return; // 둘 수 있는 자리 + 지금까지 둔 것이 최댓값보다 작으면 리턴
        if(check[r][c]>0) {
            recursion(num+1, cnt, sum, list, available); // 이미 비숍을 둬서 라인 걸치는 경우
            return;
        }
        int cannot=place(num, list);
        recursion(num+1, cnt+cannot, sum+1, list, available);
        remove(num, list);
        recursion(num+1, cnt, sum, list, available);
    }
    public static int place(int num, ArrayList<Node> list){
        int cannot=0;
        board[list.get(num).r][list.get(num).c]=1;

        for(int i=0;i<list.size();i++){
            int r=list.get(i).r;
            int c=list.get(i).c;
            if(Math.abs(r-list.get(num).r)==Math.abs(c-list.get(num).c)){
                check[r][c]++;
                if(check[r][c]==1) cannot++;
            }
        }
        return cannot;
    }

    public static void remove(int num, ArrayList<Node> list){

        board[list.get(num).r][list.get(num).c]=0;

        for(int i=0;i<list.size();i++){
            int r=list.get(i).r;
            int c=list.get(i).c;
            if(Math.abs(r-list.get(num).r)==Math.abs(c-list.get(num).c)){
                check[r][c]--;
            }
        }
    }
}
