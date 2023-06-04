/*
* 처음엔 DFS로 생각했으나 시간초과가 날 듯 했다.
*
* 맵에 존재하는 땅의 최대/최소 값을 구하여 for문을 돌면서 현재 높이로 깎는다.
* => 맵에 존재하는 땅보다 더 깎거나, 더 높게 쌓았을 때 최적인 경우가 없으므로 가능하다.
* 인벤토리가 음수가 아니고 최소 시간이 걸렸다면 시간/높이를 갱신한다. 시간이 같다면 높이를 갱신한다.
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = input[0], M = input[1], B = input[2], max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, ans = Integer.MAX_VALUE, ansH = 0;

        int[][] map = new int[N][M];

        for(int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int val : map[i]) {
                max = Math.max(val, max);
                min = Math.min(val, min);
            }
        }

        for(int nowH = min; nowH<=max; nowH++) {
            int time = 0, inv = B;

            for(int[] arr : map) {
                for (int mapH : arr) {

                    // 가지치기
                    if(time > ans) {
                        break;
                    }

                    int dif = mapH - nowH;

                    // 맵의 지형 높이가 더 크므로, 깎아야 할 때
                    if(dif >= 0) {
                        time += dif * 2;
                        inv += dif;
                    }
                    // 현재 만드려는 높이가 더 크므로, 쌓아야 할 때
                    else {
                        time -= dif;
                        inv += dif;
                    }
                }
            }

                if(inv >= 0) {
                    if(time == ans) {
                        ansH = Math.max(nowH, ansH);
                    }
                    else if(time < ans) {
                        ans = time;
                        ansH = nowH;
                    }
                }
        }

        System.out.println(ans+" "+ansH);
    }
}