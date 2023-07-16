/*
*
* 그리디 문제 시간 : 12분
*
* 이론 : 배열의 각 인덱스를 곱하여 더한 값이 최소이려면, 배열을 각각 내림차순, 오름차순으로 정렬 후 곱해야 한다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        Arrays.sort(A);
        Arrays.sort(tmp);

        int[] B = new int[N];

        for(int i=0; i<N; i++) {
            B[i] = tmp[N-1-i];
        }

        int ans = 0;

        for(int i=0; i<N; i++) {
            ans += A[i]*B[i];
        }

        System.out.println(ans);
    }
}
