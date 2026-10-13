import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
			
			for (int i = 0 ; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int oper = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (oper == 0) {
					union(a, b);
				} else if (oper == 1) {
					sb.append(find(a) == find(b) ? 1 : 0);
				}
			}
			
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if (A == B) return false;
		parents[A] = B;
		return true;
	}
	
	private static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
}
