/*
*
* 30분쓰고 헷갈려서 해답 보고 2주 뒤 풀었다. 다시 풀 땐 8분 걸렸다.
*
* 각 색으로 칠하는 모든 경우를 메모이제이션 하면 지수적으로 케이스가 늘어난다.
*
* => 어떻게 칠하냐는 관심 없으므로 "현재 해당 색깔로 칠할 수 있는 최소 비용"만을 구하여 갱신한다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int red, green, blue;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];

        int idx = 0;
        while (idx < N) {
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<3; i++) {
                cost[idx][i] = Integer.parseInt(st.nextToken());
            }

            idx++;
        }

        // DP 초기 값 설정
        red = cost[0][0];
        green = cost[0][1];
        blue = cost[0][2];


        for(int i=1; i<N; i++) {

            // 현재 해당 색깔로 칠할 수 있는 비용 중 최소값
            int r = Math.min(green + cost[i][0], blue + cost[i][0]);
            int g = Math.min(red + cost[i][1], blue + cost[i][1]);
            int b = Math.min(red + cost[i][2], green + cost[i][2]);

            // 값 갱신
            red = r;
            green = g;
            blue = b;
        }

        System.out.println(Math.min(red, Math.min(green, blue)));
    }
}
