/*
* 그리디 문제 시간 : 10분
*
* 기타줄의 종류는 상관 없으므로 패키지와 낱개 각각 가장 비용이 적은 것들을 구한다.
* 패키지만 샀을 때 기타줄이 남더라도 비용이 더 적은 경우가 있으므로 고려하여 패키지(6개)로 나눈 값에 +1 해준다
* 패키지 개수를 하나씩 줄여가며 낱개만큼 더 필요하다면 낱개로 샀을 때의 값을 더하며 답을 구한다.
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
        int M = Integer.parseInt(st.nextToken());

        int pMin = Integer.MAX_VALUE;
        int nMin = Integer.MAX_VALUE;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            pMin = Math.min(pMin, Integer.parseInt(st.nextToken()));
            nMin = Math.min(nMin, Integer.parseInt(st.nextToken()));
        }

        int max = N % 6 == 0 ? N / 6 : N / 6 + 1;
        int ans = Integer.MAX_VALUE;

        for(int i=max; i>=0; i--) {
            int cur = 0;
            cur += i * pMin;

            int remain = N - i * 6;

            if(remain > 0) {
                cur += remain * nMin;
            }

            ans = Math.min(cur, ans);
        }

        System.out.println(ans);
    }
}
