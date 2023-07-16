/*
* DP 기본문제 => DP배열 사이즈 체크 (128MB 이하), 최외곽일 때 분기처리, 인덱스 관리만 해주면 된다.
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = N;
        int[][] dp = new int[500][500];
        int row = 0;

        while(N-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int col = 0;
            while(st.hasMoreTokens()) {
                dp[row][col++] = Integer.parseInt(st.nextToken());
            }
            row++;
        }


        for(int i=1; i<M; i++) {
            for(int j=0; j<=i; j++) {

                if(j == 0) {
                    dp[i][j] += dp[i-1][j];
                }
                else if(j == i) {
                    dp[i][j] += dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i][j] + dp[i-1][j-1], dp[i][j] + dp[i-1][j]);
                }
            }
        }

        int ans = 0;

        for(int i : dp[M-1]) {
            ans = Math.max(ans, i);
        }
        System.out.println(ans);
    }

}