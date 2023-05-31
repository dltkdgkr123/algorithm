/*
* 문제 내용
* 행과 열이 등차수열로 이동할 때 만들 수 있는 숫자 중 가장 큰 제곱수 (어떤 정수의 제곱으로 이뤄지는)를 찾는 문제
*
* 적용 이론
* arr[0][0]부터 arr[N-1][M-1]까지 탐색하며 오른쪽/밑쪽/오른쪽 및 대각선으로 만들 수 있는 수들을 구하고,
* 그 반대 숫자 또한 구하여 제곱수이면 답을 갱신한다. 재귀와 순열을 사용했다.
*
* 예외 케이스
* 1. 오른쪽 위 대각선/왼쪽 밑 대각선이 고려되지 않아 추가
* 2. 답이 0일 때도 있고, 만들지 못해 -1일 때도 존재 (0도 0의 제곱수이다.)
*
* 시간 복잡도
* 배열 전체탐색 => N^2
* 등차수열이 될 수 있는 행, 열을 정하여 탐색 => N^2
* 간격만큼 실행 => N 미만
* 반대쪽도 같은 동작 => *2
* 최대 N^5 N < 9
*
* N = 9이므로 최대 9자리수(10억 - 1) => String이 아니라 int로 해도 된다.
* q를 양수와 음수로 나눠 호출할 필요 없이, 반복을 q = -(M - 1) 부터 해도 된다.
* */

import java.util.Scanner;

public class Main {

    static int N, M, answer = Integer.MIN_VALUE;
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];

        for (int i=0; i<N; i++) {
            String s = sc.next();
            for(int j=0; j<M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int p=0; p<N; p++) {
                    for(int q=0; q<M; q++) {
                        makeVal(i, j, p, q, Integer.toString(arr[i][j]));
                    }
                    for(int q=-1; j+q>=0; q--) {
                        makeVal(i, j, p, q, Integer.toString(arr[i][j]));
                    }
                }
            }
        }

        System.out.println(answer == Integer.MIN_VALUE ? -1 : answer);
    }

    public static void makeVal(int i, int j, int p, int q, String now) {

        double sqrt = Math.sqrt(Integer.parseInt(now));

        StringBuilder sb = new StringBuilder(now);
        String rvsNow = sb.reverse().toString();

        double rvsSqrt = Math.sqrt(Integer.parseInt(rvsNow));

        if(sqrt == (int) sqrt) {
            answer = Math.max(Integer.parseInt(now), answer);
        }
        if(rvsSqrt == (int) rvsSqrt) {
            answer = Math.max(Integer.parseInt(rvsNow), answer);
        }

        if(i+p >= N || i+p < 0 || j+q >= M || j+q < 0 || (p == 0 && q == 0)) {
            return;
        }
        makeVal(i+p, j+q, p, q, now+arr[i+p][j+q]);
    }
}
