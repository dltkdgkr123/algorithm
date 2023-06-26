/*
*  N = 10만 O(N^2)이면 시간초과
*
* DP로 풀어야한다 (+슬라이딩 윈도우)
*
* 메모리 제한 4MB이 의도된 것 같으나, 256MB로 완화되어있다.
*
* DP를 위해 map[N][3][3]을 선언했다. => map[N][3][0] 원래 값, map[N][3][1] 최소 값, map[N][3][2] 최대 값
* 맞춘 후 해설을 보니 모든 배열마다 최소/최대 값을 저장할 필요 없이 하나의 메모이제이션을 위한 max[3], min[3]만 있으면 된다.
*
* 위의 방식으로 메모리를 줄일 수 있다. (내 코드는 5MB나왔으며, 해당 방식으로 푼 코드는 4~5MB 언저리인 것 같다.)
* 비트를 이용해 1MB로 해결한 풀이가 존재했다.
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
        int[][][] map = new int[N][3][3];


        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<3; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<3; j++) {
                map[i][j][1] = Integer.MAX_VALUE;
                map[i][j][2] = Integer.MIN_VALUE;
            }
        }

        // DP 초기값 설정
        map[0][0][1] =  map[0][0][0];
        map[0][0][2] =  map[0][0][0];
        map[0][1][1] =  map[0][1][0];
        map[0][1][2] =  map[0][1][0];
        map[0][2][1] =  map[0][2][0];
        map[0][2][2] =  map[0][2][0];

        for(int i=1; i<N; i++) {

            for(int j=0; j<3; j++) {

                for(int k=-1; k<2; k++) {

                    int idx = j-k;

                    if(idx >= 0 && idx < 3) {
                        map[i][idx][1] = Math.min(map[i][idx][1], map[i-1][j][1] + map[i][idx][0]);
                        map[i][idx][2] = Math.max(map[i][idx][2], map[i-1][j][2] + map[i][idx][0]);
                    }
                }
            }
        }

        System.out.println(Math.max(Math.max(map[N-1][0][2], map[N-1][1][2]), map[N-1][2][2]) +
                " " + Math.min(Math.min(map[N-1][0][1], map[N-1][1][1]), map[N-1][2][1]));

    }
}
