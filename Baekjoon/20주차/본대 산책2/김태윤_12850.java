package Week20;

import java.util.Scanner;

public class 김태윤_12850 {
    static long[][] adjArr = {
            {0,1,1,0,0,0,0,0}, //정보과학관 0
            {1,0,1,1,0,0,0,0}, // 전산관 1
            {1,1,0,1,1,0,0,0}, // 미래관 2
            {0,1,1,0,1,1,0,0}, // 신양관 3
            {0,0,1,1,0,1,1,0}, //한경직기념관 4
            {0,0,0,1,1,0,0,1}, // 진리관 5
            {0,0,0,0,1,0,0,1}, // 형남공학관 6
            {0,0,0,0,0,1,1,0}, // 학생회관 7
    };
    static final int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        long[][] ans = solve(n);
        System.out.println(ans[0][0]);
    }
    static long[][] solve(int num){
        if(num == 1) return adjArr;
        long[][] arr1 = solve(num/2);
        if(num%2 == 0) return calc(arr1, arr1);
        else {
            long[][] arr2 = calc(arr1, adjArr);
            return calc(arr1, arr2);
        }
    }

    static long[][] calc(long[][] arr1, long[][] arr2){
        long[][] matrix = new long[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                for(int k=0; k<8; k++){
                    matrix[i][j] += (arr1[i][k]*arr2[k][j])%MOD;
                    matrix[i][j]%=MOD;
                }
            }
        }
        return matrix;
    }
}
