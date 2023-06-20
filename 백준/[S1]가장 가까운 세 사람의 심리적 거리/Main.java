/*
* 브루트포스 + 비둘기집 이론 문제
*
* MBTI는 16개이다. 3개를 뽑을 때, 33명 이상이라면 같은 MBTI가 3명이 뽑히는 경우 (거리가 0인 경우)가 반드시 존재한다.
*
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            // 비둘기집 이론 가지치기
            if(M>32) {
                System.out.println(0);
                continue;
            }

            boolean[][] mbti = new boolean[M][4];
            for(int i=0; i<M; i++) {
                String s = st.nextToken();
                mbti[i][0] = s.charAt(0) != 'E';
                mbti[i][1] = s.charAt(1) != 'N';
                mbti[i][2] = s.charAt(2) != 'T';
                mbti[i][3] = s.charAt(3) != 'J';
            }

            int ans = Integer.MAX_VALUE;

            for(int i=0; i<M; i++) {
                for (int j=i+1; j<M; j++) {
                    for(int k=j+1; k<M; k++) {
                        int dis = 0;
                        for(int p=0; p<4; p++) {
                            dis = mbti[i][p] == mbti[j][p] ? dis : dis+1;
                            dis = mbti[j][p] == mbti[k][p] ? dis : dis+1;
                            dis = mbti[k][p] == mbti[i][p] ? dis : dis+1;
                        }
                        ans = Math.min(dis, ans);
                    }
                }
            }
            System.out.println(ans);
        }
    }
}