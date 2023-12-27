import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_5427 {
    static class Node{
        int r;
        int c;
        Node(){}
        Node(int r, int c){
            this.r=r;
            this.c=c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        for(int tc=0;tc<t;tc++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int w=Integer.parseInt(st.nextToken());
            int h=Integer.parseInt(st.nextToken());
            int ans=-1;
            char[][] arr=new char[h][w];
            boolean[][] check=new boolean[h][w];
            Queue<Node> fireList=new LinkedList<>();
            Queue<Node> sangkeun=new LinkedList<>();
            for(int i=0;i<h;i++){
                arr[i]=br.readLine().toCharArray();
                for(int j=0;j<w;j++){
                    if(arr[i][j]=='*'){
                        fireList.add(new Node(i,j));
                    }
                    else if(arr[i][j]=='@'){
                        sangkeun.add(new Node(i,j));
                        check[i][j]=true;
                    }
                }
            }
            int move=0;
            int[] dr={1,0,-1,0};
            int[] dc={0,1,0,-1};
            while(!sangkeun.isEmpty() && ans<0){
                move++;
                //1. 불 퍼뜨리기
                int len=fireList.size();
                for(int i=0;i<len;i++){
                    Node curr=fireList.poll();
                    for(int k=0;k<4;k++){
                        Node next=new Node(curr.r+dr[k], curr.c+dc[k]);
                        if(next.r<0 || next.r>=h || next.c<0 || next.c>=w) continue;
                        if(arr[next.r][next.c]=='.' || arr[next.r][next.c]=='@'){
                            fireList.add(next);
                            arr[next.r][next.c]='*';
                        }
                    }
                }

                //2. 상근이 움직이기
                len = sangkeun.size();
                for(int i=0;i<len;i++){
                    Node curr= sangkeun.poll();
                    if(curr.r==0 || curr.r==h-1 || curr.c==0 || curr.c==w-1){
                        ans=move;
                        break;
                    }
                    for(int k=0;k<4;k++){
                        Node next=new Node(curr.r+dr[k], curr.c+dc[k]);
                        if(check[next.r][next.c] || arr[next.r][next.c]=='#' || arr[next.r][next.c]=='*') continue;
                        sangkeun.add(next);
                        check[next.r][next.c]=true;
                    }
                }
            }
            if(ans>0) System.out.println(ans);
            else System.out.println("IMPOSSIBLE");
        }
    }
}
