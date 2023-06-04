/*
* 푼 후 문제 분류를 보니 이분탐색이였다. 나는 인덱스 비교로 풀이하였다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Pair implements Comparable<Pair>{
        int val;
        int idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.val, o.val);
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Pair[] A = new Pair[N];
        StringTokenizer st = new StringTokenizer(br.readLine());


        for(int i=0; i<N; i++) {
            A[i] = new Pair(Integer.parseInt(st.nextToken()), i);
        }

        int M = Integer.parseInt(br.readLine());
        Pair[] B = new Pair[M];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<M; i++) {
            B[i] = new Pair(Integer.parseInt(st.nextToken()), i);
        }

        int[] ans = new int[M];
        Arrays.sort(A);
        Arrays.sort(B);

        int idxA = 0;
        int idxB = 0;

        while(idxA < A.length && idxB < B.length) {

            if(A[idxA].val == B[idxB].val) {
                Pair pair = B[idxB];
                ans[pair.idx] = 1;
                idxB++;
            }
            else if(A[idxA].val > B[idxB].val) {
                idxB++;
            }
            else if(A[idxA].val < B[idxB].val) {
                idxA++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i : ans) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
}