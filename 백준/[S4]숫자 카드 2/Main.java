/*
* 키가 있으면 밸류++, 없으면 밸류 = 1 후 조회하여 출력
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> A = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            int key = Integer.parseInt(st.nextToken());
            A.put(key , A.getOrDefault(key, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();

        for (int i : arr) {
            sb.append(A.getOrDefault(i, 0)).append(" ");
        }

        System.out.println(sb);
    }
}