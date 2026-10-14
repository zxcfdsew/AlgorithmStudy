import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] arr = new int[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			for (int i = 1; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[N+1];
			for (int i = 1; i < dp.length; i++) {
				dp[i] = 1;
			}
			
			for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < i; j++) {
					if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			
			int answer = 0;
			for (int i = 1; i < dp.length; i++) {
				answer = Math.max(answer, dp[i]);
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
}
