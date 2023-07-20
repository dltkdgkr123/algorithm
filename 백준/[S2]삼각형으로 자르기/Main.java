/*
*
* 걸린 시간 : 35분
*
* 삼각형을 만들 때 마다 점은 1개씩 사라진다. 결국 세 점만 남게된다. 해당 점으로 만든 삼각형의 넓이가 최대일 때 넓이를 구하는 문제이다.
*
* 적용 이론
* 어떤 점들을 선택하던 3개를 선택했을 때 삼각형을 만들 수 있다. 따라서, 원하는 점 3개를 제외하고 나머지 점은 삼각형을 만들어 모두 없애버리는 것이 가능하다.
* => 삼각형을 만드는 것을 고려할 필요 없이 3개의 점만 선택하여 넓이를 구하면 된다.
*
* 점을 구하는 것은 조합이고 35C3 = N(6545)이였다.
* 따라서, 브루트포스로 3개의 점을 뽑았을 때 넓이 중 최대값을 구한다.
*
* 넓이는 헤론의 공식을 검색해서 사용하였다.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] pick = new int[3][2], vertex;
    static double ans;
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        vertex = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            vertex[i][0] = Integer.parseInt(st.nextToken());
            vertex[i][1] = Integer.parseInt(st.nextToken());
        }

        comb(0,0);
        System.out.println(ans);
    }

    static void comb (int start, int dep) {

        if(dep == 3) {
            ans = Math.max(ans, findArea(pick[0][0],pick[0][1],pick[1][0],pick[1][1],pick[2][0],pick[2][1]));
            return;
        }

        for(int i=start; i<N; i++) {
            pick[dep][0] = vertex[i][0];
            pick[dep][1] = vertex[i][1];
            comb(i+1, dep+1);
        }
    }

    static double findArea(int x1, int y1, int x2, int y2, int x3, int y3) {

        double a = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
        double b = Math.sqrt(Math.pow(x2-x3, 2) + Math.pow(y2-y3, 2));
        double c = Math.sqrt(Math.pow(x3-x1, 2) + Math.pow(y3-y1, 2));
        double s = (a+b+c)/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }
}
