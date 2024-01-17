import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_17143 {
    static class Shark{
        int r;
        int c;
        int s; // 속도
        int d; // 방향
        int z; // 크기
        Shark(){}
        Shark(int r, int c, int s, int d, int z){
            this.r=r;
            this.c=c;
            this.s=s;
            this.d=d;
            this.z=z;
        }
    }
    static int[] dr={0,-1,1,0,0};
    static int[] dc={0,0,0,1,-1};
    static int r;
    static int c;
    static int n;
    static Shark[][] arr;
    static Queue<Shark> queue= new LinkedList<>();
    static int ans=0;

    public static void main(String[] args) throws IOException {
        input();
        for(int i=0;i<c;i++){
            fish(i);
            move();
        }
        System.out.println(ans);
    }
    public static void input() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        n=Integer.parseInt(st.nextToken());
        arr=new Shark[r][c];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int row=Integer.parseInt(st.nextToken());
            int col=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            int z=Integer.parseInt(st.nextToken());
            arr[row-1][col-1]=new Shark(row-1,col-1,s,d,z);
            queue.offer(arr[row-1][col-1]);
        }
    }

    public static void fish(int col){
        for(int i=0;i<r;i++){
            if(arr[i][col]!=null){
                ans+=arr[i][col].z;
                Shark captured=arr[i][col];
                arr[i][col]=null;
                while(true){
                    Shark shark = queue.poll();
                    if(shark.z==captured.z) break;
                    queue.offer(shark);
                }
                return;
            }
        }
    }

    public static void move(){
        arr=new Shark[r][c];
        while(!queue.isEmpty()) {
            Shark shark = queue.poll();
            int nextR = shark.r + dr[shark.d] * shark.s;
            int nextC = shark.c + dc[shark.d] * shark.s;
            int nextS = shark.s;
            int nextD = shark.d;
            int nextZ = shark.z;
            if (nextR > r - 1) {
                if ((nextR / (r - 1)) % 2 == 0)
                    nextR = nextR % (r - 1);
                else {
                    nextR = r - 1 - (nextR % (r - 1));
                    nextD = 3 - shark.d;
                }
            }
            if (nextR < 0) {
                if ((-nextR / (r - 1)) % 2 == 1)
                    nextR = (r - 1) - ((-nextR) % (r - 1));
                else {
                    nextR = (-nextR) % (r - 1);
                    nextD = 3 - shark.d;
                }
            }
            if (nextC > c - 1) {
                if ((nextC / (c - 1)) % 2 == 0)
                    nextC = nextC % (c - 1);
                else {
                    nextC = c - 1 - (nextC % (c - 1));
                    nextD = 7 - shark.d;
                }
            }
            if (nextC < 0) {
                if ((-nextC / (c - 1)) % 2 == 1)
                    nextC = (c - 1) - ((-nextC) % (c - 1));
                else {
                    nextC = (-nextC) % (c - 1);
                    nextD = 7 - shark.d;
                }
            }
            Shark next = new Shark(nextR, nextC, nextS, nextD, nextZ);
            if (arr[nextR][nextC] != null && arr[nextR][nextC].z > next.z)
                continue;
            arr[nextR][nextC] = next;
        }

        //queue 다시 채우기
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(arr[i][j]!=null) queue.offer(arr[i][j]);
            }
        }
    }
}
