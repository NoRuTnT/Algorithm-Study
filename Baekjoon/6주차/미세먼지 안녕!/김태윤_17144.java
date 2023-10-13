import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class dust {
	int r;
	int c;
	int num;

	dust() {
	}

	dust(int r, int c, int num) {
		this.r = r;
		this.c = c;
		this.num = num;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		LinkedList<dust> queue = new LinkedList<>();
		dust air = new dust();
		boolean flag = true;
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > 0) {
					queue.add(new dust(i, j, arr[i][j]));
				} else if (arr[i][j] == -1 && flag) {
					air.r = i;
					air.c = j;
					flag = false;
				}
			}
		}
		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		for (int time = 0; time < t; time++) {
			// 1) 확산
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				dust now = queue.poll();
				int diffuse = now.num / 5;
				if(diffuse==0) {
					queue.add(now);
					continue; //diffuse 0이면 변화 x.
				}
				int diffuseCnt = 0;
				for (int k = 0; k < 4; k++) {
					if (now.r + dr[k] < 0 || now.r + dr[k] >= n || now.c + dc[k] < 0 || now.c + dc[k] >= m
							|| arr[now.r + dr[k]][now.c + dc[k]] == -1)
						continue; // 경계조건 확인 + 공기청정기로 확산 방지
					diffuseCnt++;
					dust next = new dust(now.r + dr[k], now.c + dc[k], diffuse);
					queue.add(next);
					arr[next.r][next.c] += next.num;
				}
				queue.add(new dust(now.r, now.c, now.num - diffuse * diffuseCnt));
				arr[now.r][now.c] -= diffuse * diffuseCnt;
			} // 1) end
			//2) queue의 갱신 필요(mass) 문제 -> check 배열의 활용
				// 2) 공기청정 => dust의 이동
				// 이동 조건 : 공기청정기와 동일한 r를 지니고 있거나, r,c가 경계에 있는 경우
				// 이동 방향은 각각 조건에 맞게
			len = queue.size();
			boolean[][] check=new boolean[n][m];
			for (int i = 0; i < len; i++) {
				dust now = queue.poll();
				if(check[now.r][now.c]) continue;
				check[now.r][now.c]=true;
				now=new dust(now.r, now.c, arr[now.r][now.c]); // mass에 맞게 갱신 완료
				if (now.r == air.r || now.r == air.r+1) {
					if(now.c!=m-1) {
						dust next=new dust(now.r, now.c+1, now.num);
						if(next.c!=air.c) {
							queue.add(next);
						}
						continue;
					}
				}
				if (now.r == 0 || now.r == n - 1) {
					if(now.c!=0) {
						dust next=new dust(now.r, now.c-1, now.num);
						queue.add(next);
						continue;
					}
				}
				if (now.c == 0) {
					dust next;
					if(now.r<=air.r) next=new dust(now.r+1, now.c, now.num);
					else next=new dust(now.r-1, now.c, now.num);
					if(air.c==0 && (next.r==air.r || next.r==air.r+1)) {
						continue;
					}
					queue.add(next);
					continue;
				}
				if (now.c == m - 1) {
					dust next; 
					if(now.r<=air.r) next=new dust(now.r-1, now.c, now.num);
					else next=new dust(now.r+1, now.c, now.num);
					if(air.c==m-1 && (next.r==air.r || next.r==air.r+1)) {
						continue;
					}
					queue.add(next);
					continue;
				}
				//위의 조건에 해당 안 될 때 그냥 다시 넣어줌
				queue.add(now);
			}//2) end
			//3) queue에 든 값으로 arr 갱신
			arr=new int[n][m];
			arr[air.r][air.c]=-1;
			arr[air.r+1][air.c]=-1;
			len=queue.size();
			for(int i=0;i<len;i++) {
				dust now=queue.poll();
				arr[now.r][now.c]=now.num;
				queue.add(now);
			}//3)end
		}// time end
		int sum=0;
		while(!queue.isEmpty()) {
			sum+=queue.poll().num;
		}
		System.out.println(sum);
	}
}
