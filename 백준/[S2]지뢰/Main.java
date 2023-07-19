/*
* 걸린 시간 : 1시간 20분
*
* 어렵고 비효율적이게 풀었다.
* 문자열 처리로 풀면 시간초과이다.
* 인덱스와 사이즈 관리를 위해 여러개의 자료구조를 쓰고, idx+1과 idx를 구분하느라 헷갈렸다.
*
* 그리디 + 정렬 문제이다.
* 작은 지뢰를 터트려도 더 큰 지뢰는 따로 터트려야 하므로 적은 횟수를 위해선 가장 큰 지뢰를 항상 먼저 터트려야 한다. => 그리디
* 지뢰를 큰 순서로 정렬하고 터트리지 않았다면 터트리고 터트렸다면 다음 인덱스로 넘어간다. 지뢰가 다 터질 때 까지 반복한다.
*
*
* 모범 답안 (그리디o, 정렬x)
* 인덱스를 순차로 탐색하면서,
* 0번째 지뢰는 1번째와 비교하여 크거나 같으면 터트린다.
* N-1번째 지뢰는 N-2번째와 비교하여 크거나 같으면 터트린다.
* 이외의 지뢰 n은 n-1/n+1과 비교하여 크거나 같으면 터트린다.
* => 양 옆에 하나라도 더 큰 지뢰가 있으면 어차피 연쇄로 터지기 때문에 넘어간다. 아니면 따로 터트려야 하기 때문이다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Mine implements Comparable<Mine>{

        int idx, size;

        public Mine(int idx, int size) {
            this.idx = idx;
            this.size = size;
        }

        @Override
        public int compareTo(Mine m) {
            return m.size - this.size;
        }

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Mine> list = new ArrayList<>();

        int[] arr = new int[N];
        boolean[] visited = new boolean[N];
        int cnt = N;

        for(int i=0; i<N; i++) {
            int size = Integer.parseInt(br.readLine());

            arr[i] = size;
            list.add(new Mine(i, size));
        }

        Collections.sort(list);
        Queue<Integer> queue = new ArrayDeque<>();

        int cur = 0;
        List<Integer> answer = new ArrayList<>();

        while(cur < N) {
            // 이미 터트린 지뢰면 다음 크기 지뢰로
            if(visited[list.get(cur).idx]) {
                cur++;
                continue;
            }

            // 터트림 표시 후 스택에 넣음
            visited[list.get(cur).idx] = true;
            answer.add(list.get(cur).idx+1);
            queue.offer(list.get(cur).idx);
            cnt--;

            if(cnt == 0) {
                break;
            }

            // 인접한 지뢰들 파괴
            while(!queue.isEmpty()) {

                int now = queue.poll();

                if(now > 0 && !visited[now-1] && arr[now-1] < arr[now]) {
                    cnt--;
                    visited[now-1] = true;
                    queue.offer(now-1);
                }
                if(now < N-1 && !visited[now+1] && arr[now+1] < arr[now]) {
                    cnt--;
                    visited[now+1] = true;
                    queue.offer(now+1);
                }
            }

            cur++;
        }

        Collections.sort(answer);

        for(int i : answer) {
            System.out.println(i);
        }
    }
}
