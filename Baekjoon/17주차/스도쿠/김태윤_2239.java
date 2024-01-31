import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 김태윤_2239 {
    static int[][][] check = new int[9][9][10]; // (r, c, number) -> (r,c)에서 몇번이 있을 수 있는지 없는지
    static int[][] board = new int[9][9]; //r, c -> 값은 들어가 있는 수
    static boolean findAns;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<9;i++){
            String s=br.readLine();
            for(int j=0;j<9;j++){
                board[i][j]=s.charAt(j)-'0';
                if(board[i][j]>0) check(i,j, board[i][j]);
            }
        }
        sudoku(0,0);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    public static void sudoku(int r, int c){
        if(r==9){ // 정답 찾음
            findAns=true;
            return;
        }
        if(board[r][c]>0) { // 이미 차 있는 경우
            if(c==8) sudoku(r+1, 0);
            else sudoku(r, c+1);
        }
        else {
            for (int num = 1; num <= 9; num++) {
                if (check[r][c][num] > 0)
                    continue; // 못 놓는 숫자면 패스
                check(r, c, num); // 두고 나서
                board[r][c] = num;
                if (c == 8)
                    sudoku(r + 1, 0);
                else
                    sudoku(r, c + 1);
                if (findAns)
                    return; // 답 찾은 경우 무조건 리턴
                board[r][c] = 0;
                uncheck(r, c, num); // 여기까지 오면 중간에 아니어서 빠꾸 당한거니까 uncheck
            }
        }
    }
    public static void check(int r, int c, int num){
        for(int i=0;i<9;i++){
            //1. 세로 방향 못 둬요
            check[i][c][num]++;
            //2. 가로 방향 못 둬요
            check[r][i][num]++;
        }
        //3. 사각형 안에 못해요
        int squareR=(r/3)*3;
        int squareC=(c/3)*3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                check[squareR+i][squareC+j][num]++;
            }
        }
    }
    public static void uncheck(int r, int c, int num){
        for(int i=0;i<9;i++){
            //1. 세로
            check[i][c][num]--;
            //2. 가로
            check[r][i][num]--;
        }
        //3. 사각형 안
        int squareR=(r/3)*3;
        int squareC=(c/3)*3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                check[squareR+i][squareC+j][num]--;
            }
        }
    }
}
