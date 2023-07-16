/*
*
* 30분 고민하고 접근방법을 못찾아서 이론을 보고 풀었다.
*
* 해당 문제에는 거리의 정의에 대한 언급이 없었다.
* => 거리를 |x1-x2| + |y1-y2|로 가정하는 문제만 풀다보니 이 문제 또한 그럴 것이라 가정하여 오래걸렸다.
*
* 두 점 사이의 거리 공식 (|x1-x2|^2 + |y1-y2|^2)√1/2 에서,
* 루트를 구하는 과정에서 Math.sqrt를 쓰면 부동소수점으로 인해 근사값이 구해져 예상 범위 밖의 결과가 나올 수 있다.
* => (|x1-x2|^2 + |y1-y2|^2)√1/2 = r1 + r2 식을 |x1-x2|^2 + |y1-y2|^2 = (r1 + r2)^2로 변경한다.
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int x1,y1,r1,x2,y2,r2;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());

            System.out.println(findAns());
        }
    }

    public static int findAns() {

        // 중점이 같으면서
        if(x1 == x2 && y1 == y2) {

            // 반지름이 같을 때
            if(r1 == r2)
                return -1;

            // 반지름이 다를 때
            else
                return 0;
        }

        // 두 점 사이의 거리를 제곱한 값
        double dis = Math.pow(Math.abs(x1-x2), 2) + Math.pow(Math.abs(y1-y2), 2);

        // 내접 할 때
        if(dis == Math.pow(r1 - r2, 2)) {
            return 1;
        }

        // 외접 할 때
        if(dis == Math.pow(r1 + r2, 2)) {
            return 1;
        }

        // 두 원의 반지름의 합 보다 두 원의 거리가 멀 때
        if(dis > Math.pow(r1 + r2, 2)) {
            return 0;
        }

        // 접점없이 원 안에 포함될 때
        if(dis < Math.pow(r1 - r2, 2)) {
            return 0;
        }

        // 이 외는 모두 접점이 2개
        return 2;
    }

}
