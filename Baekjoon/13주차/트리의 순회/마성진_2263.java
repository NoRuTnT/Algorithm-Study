import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static StringBuilder sb = new StringBuilder();
	public static int[] inorder, inorderIndex, postorder;
	public static int N;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		inorder = new int[N];
		postorder = new int[N];
		inorderIndex = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			inorderIndex[inorder[i]] = i;
		}
		
		getPreOrder(0, N - 1, 0, N - 1);
		System.out.println(sb);
	}

	public static void getPreOrder(int inStart, int inEnd, int pStart, int pEnd) {
		if (inStart > inEnd || pStart > pEnd) {
			return;
		}

		int rootNode = postorder[pEnd];
		sb.append(rootNode + " ");

		int rootIndex = inorderIndex[rootNode];
		int leftNodeLength = rootIndex - inStart;

		getPreOrder(inStart, rootIndex - 1, pStart, pStart + leftNodeLength - 1);

		getPreOrder(rootIndex + 1, inEnd, pStart + leftNodeLength, pEnd - 1);
	}
}
