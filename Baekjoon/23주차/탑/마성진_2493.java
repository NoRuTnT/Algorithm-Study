import java.io.*;
import java.util.*;

public class Main {

    static class Top {
        int num;
        int height;

        Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Stack<Top> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()) {
                sb.append("0").append(" ");
                stack.push(new Top(i, height));
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        sb.append("0").append(" ");
                        stack.push(new Top(i, height));
                        break;
                    }

                    Top top = stack.peek();

                    if (top.height > height) {
                        sb.append(top.num).append(" ");
                        stack.push(new Top(i, height));
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}
