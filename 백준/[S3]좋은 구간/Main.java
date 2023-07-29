/*
*
* 걸린 시간 : 30분
* 수학 + 정렬 + 브루트포스 문제이다. (브루트포스 일부를 점화식으로 바꿀 수 있다. 고 생각했다.)
*
* 정답 비율이 낮은 문제였다 (27%)
*
* S에 있는 수들을 구간에 포함하지않고 n이 포함된 구간의 개수를 구하는 문제이다.
* 배열을 오름차순으로 정렬하고,
* 1. n과 S에 있는 수가 같다면 => 이런 경우는 없다. 답은 0이다.
* 2-1. n이 S의 최솟값보다 작다면 => 1(왼쪽)부터 S-1(오른쪽)까지 n이 포함된 경우의 개수를 구한다.
* 2-2. n이 S의 최소값보다 크다면 => (n보다 작은 최고값 + 1(왼쪽)) 부터 (n보다 큰 최소값 -1(오른쪽)) 까지 탐색하며 n이 포함된 경우의 개수를 구한다.
*
*
* 구간을 브루트포스로 탐색할 필요 없이
* 왼쪽이 n보다 작거나 같은 경우의 수 만큼 탐색하며 오른쪽보다 n번이 더 작은 경우의 수를 덧셈 중첩하면 완화 가능하다. (해당 문제에서 유의미한 차이는 없어서 그냥 ans++했다.)
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        n = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        for(int i : arr) {
            if(i == n) {
                System.out.println(0);
                return;
            }
        }

        if(n < arr[0]) {
            findAns(1, arr[0]-1);
        }

        int l = 0, r = 0;

        for(int i=0; i<N; i++) {

            if(arr[i] > n) {
                l = arr[i-1]+1;
                r = arr[i]-1;
                break;
            }
        }

        findAns(l, r);
    }

    public static void findAns(int l, int r) {

        int ans = 0;

        for(int i=l; i<r; i++) {

            if(i>n) break;
            for(int j=i+1; j<=r; j++) {
                if(j>=n) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
        exit(0);
    }
}
