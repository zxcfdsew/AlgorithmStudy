import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[문제]
N*N개의 벌통에 서로 다른 양의 꿀이 들어있다.
다음과 같은 과정으로 벌꿀을 채취하여 최대한 많은 수익을 얻으려고 한다.
1. 두명의 일꾼이 있다. 꿀을 채취할 수 있는 벌통의 수 M이 주어질때,
	각각의 일꾼은 가로로 연속되도록 M개의 벌통을 선택해서 꿀을 채취한다. (두명의 일꾼이 겹치면 안됨)
2. 두 명의 일꾼은 선택한 벌통에서 꿀을 채취하여 용기에 담는다.
	- 서로 다른 벌통에서 채취한 꿀이 섞이면 안되고, 하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야한다.
	- 각 일꾼이 이 채취할 수 있는 꿀의 최대 양은 C이다.(C를 넘으면 채취를 못하는 벌통이 생김)
3. 수익은 각 용기에 있는 꿀의 양의 제곱만큼 생김

두 일꾼이 꿀을 채취하여 얻을 수 있는 최대수익을 구하는 코드를 작성

[제약조건]
N : 3~10
M : 1~5 (N <= M)
C : 10~30

[풀이]
1. 서로 다른 두 범위를 찾기
- 가장 앞의 값을 대표값으로 생각하고 고를 수 있는 모든 경우를 배열로 저장
	- 저장할때 미리 값을 계산해서 저장해두고 나중에 활용
- 나온 2차원 배열에서 2개를 선택(조합)
	- 2개를 선택했을때 중복되는 범위는 제외함
2. 선택된 범위에서 최대값을 구함
- dfs를 돌리되 범위가 벌꿀통을 선택못하는 경우도 고려 필요(부분집합)
- C를 넘으면 가지치기

*/

public class Solution {
	
	private static int N;  // 크기
	private static int M;  // 선택할 수 있는 벌통의 개수
	private static int C;  // 꿀을 채취할 수 있는 최대 양
	private static int[][] board;
	private static int[][] profits;
	private static int maxProfit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int endIdx = N - M + 1;  // 범위를 생각했을 경우의 마지막 인덱스
			
			profits = new int[N][endIdx];
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < endIdx; c++) {
					// 현재 범위에서 최선의 값을 미리 계산하고 저장
					maxProfit = 0;
					dfs(r, c, c + M, 0, 0);
					profits[r][c] = maxProfit;
				}
			}
			
			int answer = 0;
			// N*N 격자에서 2칸 선택(조합)
			for (int i = 0; i < N * endIdx; i++) {
			    int r1 = i / endIdx;
			    int c1 = i % endIdx;
			    for (int j = i + 1; j < N * endIdx; j++) {
			        int r2 = j / endIdx;
			        int c2 = j % endIdx;
			        // 범위가 겹치는 경우는 넘어감
			        if (r1 == r2 && c1 + M > c2) continue;
			        
			        answer = Math.max(answer, profits[r1][c1] + profits[r2][c2]);
			    }
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}
	
	// 선택된 범위에서 최대이익을 구함(부분집합)
	private static void dfs(int r, int c, int endIdx, int honey, int profit) {
		if (honey > C) return;
		if (c == endIdx) {
			maxProfit = Math.max(maxProfit, profit);
			return;
		}
		
		dfs(r, c + 1, endIdx, honey + board[r][c], profit + board[r][c] * board[r][c]);
		dfs(r, c + 1, endIdx, honey, profit);
	}
}