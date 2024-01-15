//bfs로 풀었어요


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 김태윤_1943 {
    static int n;
    static int total;
    static int[][] money;

    public static void main(String[] args) {
        int t = 3;
        Scanner sc = new Scanner(System.in);
        for (int tc = 1; tc <= 3; tc++) {
            n = sc.nextInt();
            money = new int[n][2];
            total = 0;
            for (int i = 0; i < n; i++) {
                money[i][0] = sc.nextInt();
                money[i][1] = sc.nextInt();
                total += money[i][0] * money[i][1];
            }
            if (total % 2 == 1) {
                System.out.println(0);
                continue;
            }

            if (solve(total / 2)) System.out.println(1);
            else System.out.println(0);

        }
    }

    public static boolean solve(int target) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] check = new boolean[target + 1];
        //queue 초기화
        for (int j = 0; j <= money[0][1] && money[0][0] * j <= target; j++) {
            queue.offer(money[0][0] * j);
            check[money[0][0] * j] = true;
        }


        for (int i = 1; i < n; i++) {
            int value = money[i][0];
            int len = queue.size();
            for (int k = 0; k < len; k++) {
                int curr = queue.poll();
                for (int j = 1; j <= money[i][1]; j++) {
                    int next = curr + value * j;
                    if (next > target || check[next]) continue;
                    queue.offer(next);
                    check[next] = true;
                }
                queue.offer(curr);
            }
        }
        if (check[target]) return true;
        return false;
    }
}
