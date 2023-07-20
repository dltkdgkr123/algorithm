/*
* 접근 방식
* |Ai + T - Bi|를 0으로 만드는 T의 값 = 각 수열의 Ai - Bi 값을 배열에 담는다.
* 배열을 정렬한 후,
* 배열의 사이즈가
* 홀수면, 중간의 T값이 최적해(1개)이므로 1을 출력한다.
* 짝수면, 중간기준 왼쪽과 오른쪾 인덱스의 값 내의 모든 수들이 최적해이므로 그 개수를 출력한다.
*
*
* 테케에서 각 수열을 0으로 만드는 T만큼의 범위가 정답인 것은 찾았으나, 연결하지 못해서 혼자 못풀었다.
* 25분 생각하고 해답을 얼핏보다가 힌트를 얻었다. (문제 분류 정렬인 것 + 홀수 일 때는 1개이다 << 이거 보고 풀었다.)
* 힌트를 얻고 풀 때도 수학적인 접근은 못했다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];


        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        if(N % 2 == 1) {
            System.out.println(1);
        }
        else {
            int ml = N/2-1;
            int mr = N/2;
            System.out.println(arr[mr]-arr[ml]+1);
        }
    }
}
