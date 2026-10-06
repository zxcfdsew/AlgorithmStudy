import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[풀이]
사람수만큼의 순열을 구해서, 그 순열대로 사람의 확률을 계산한다음 가장 높은값을 정답으로 출력
 */

public class Solution {
	private static double answer;
	private static int N;
	private static int[][] tasks;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			tasks = new int[N][N];
			answer = Double.MIN_VALUE; 

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					tasks[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			permutation(0, new int[N], new boolean[N], 1);
			sb.append('#').append(tc).append(' ').append(String.format("%.6f", answer*100)).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void permutation(int depth, int[] result, boolean[] used, double state) {
		if (depth == N) {
			answer = Math.max(state, answer);
			return;
		}
		
		if (state < answer) return;
		
		for (int i = 0; i < N; i++) {
			if (used[i]) continue;
			used[i] = true;
			result[depth] = i;
			permutation(depth+1, result, used, state*(0.01*tasks[depth][i]));
			used[i] = false;
		}
	}
}