import java.io.*;
import java.util.*;

public class Main {
    static class Room {
        int t;
        int a;
        int h;

        public Room(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int HAtk = Integer.parseInt(st.nextToken());
        long start = 1, end = 0;

        Room[] rooms = new Room[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            rooms[i] = new Room(t, a, h);
            if (t == 1) end += ((long) a * h);
        }

        while (start <= end) {
            long mid = (start + end) / 2;
            long HCurHp = mid;
            long tempHAtk = HAtk;
            boolean success = true;
            for (int i = 0; i < N; i++) {
                if (rooms[i].t == 1) {
                    if (rooms[i].h % tempHAtk == 0)
                        HCurHp -= rooms[i].a * ((rooms[i].h / tempHAtk) - 1);
                    else HCurHp -= rooms[i].a * (rooms[i].h / tempHAtk);
                    if (HCurHp <= 0) {
                        success = false;
                        break;
                    }
                } else if (rooms[i].t == 2) {
                    tempHAtk += rooms[i].a;
                    HCurHp += rooms[i].h;
                    if (HCurHp > mid) HCurHp = mid;
                }
            }

            if (success) end = mid - 1;
            else start = mid + 1;
        }

        System.out.println(start);
    }
}
