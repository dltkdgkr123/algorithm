/*
*
* 해답 봤다. 수학적 개념이라 너무 어렵게 풀었다.
*
* A * B % C == (A%C) * (B%C) %C 모듈러 법칙으로 풀이한다
*
* 짝수랑 홀수랑 다르게 처리해야 한다.
* 재귀할 때 => pow(b) == pow(b/2) * pow(b/2)로 처리하면 시간초과 => 변수로 바꿔서 재귀를 줄인다.
*
* tmp * tmp * a % c == tmp * tmp % c * a % c는 같은 동작이지만,
* tmp가 Integer.MAX_VALUE일 때, tmp*tmp*n (n>3) 이면 long의 범위를 넘어버려서 처리해야 한다.
*
* 같은 맥락으로 tmp * tmp % c == tmp % c * tmp % c 이다.
*
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int mod, a, c;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        mod = a%c;

        System.out.println(pow(b));
    }


    public static long pow(long b) {

        if(b == 1) return mod;

        long tmp = pow(b/2);

        return b % 2 == 0 ? tmp * tmp % c : (tmp * tmp % c) * a % c;
    }
}
