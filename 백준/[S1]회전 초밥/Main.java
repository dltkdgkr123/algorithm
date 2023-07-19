/*
* 걸린 시간 12분
*
* 예전에 풀었던 문제다. 두 포인터 문제인걸 알고 시작했다.
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        int[] visited = new int[D+1];

        for(int i=0; i<N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int cur = 1;
        visited[C]++;

        int start = 0;
        int end = start + K;

        for(int i=0; i<K; i++) {
            visited[sushi[i]]++;
            if(visited[sushi[i]] == 1) cur++;
        }
        int ans = cur;

        for(;start<N;start++, end++) {

            if(end == N) end = 0;

            visited[sushi[start]]--;
            if(visited[sushi[start]] == 0) cur--;

            visited[sushi[end]]++;
            if(visited[sushi[end]] == 1) cur++;

            ans = Math.max(cur, ans);
        }

        System.out.println(ans);
    }
}
