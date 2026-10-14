import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			int N = Integer.parseInt(br.readLine().trim());
			int M = Integer.parseInt(br.readLine().trim());
			
			List<Integer>[] graph = new List[N+1];
			List<Integer>[] bgraph = new List[N+1];
			
			for (int i = 0; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
				bgraph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int small = Integer.parseInt(st.nextToken());
				int big = Integer.parseInt(st.nextToken());
				
				graph[small].add(big);
				bgraph[big].add(small);
			}
			
			for (int i = 1; i <= N; i++) {
				count = 0;
				boolean[] visited = new boolean[graph.length];
				visited[i] = true;
				dfs(i, graph, visited);
				
				visited = new boolean[graph.length];
				visited[i] = true;
				dfs(i, bgraph, visited);
				if (count == N-1) answer++;
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}
	
	private static void dfs(int startV, List<Integer>[] graph, boolean[] visited) {
		for (int i : graph[startV]) {
			if (visited[i]) continue;
			visited[i] = true;
			dfs(i, graph, visited);
			count++;
		}
	}
}