/*
* 일반 BFS 문제
* 브루트포스 6^100 => 불가
* 뱀과 사다리가 나누어져있다. 둘의 경우를 따로 처리해야하나 했지만
* => 뱀을 탔을 때 최적인 경우는 있으나, 뱀으로 인해 방문했던 칸에 다시 떨어졌을 때 최적인 경우는 없으므로 배제했다.
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int idx, dep;

        public Node(int idx, int dep) {
            this.idx = idx;
            this.dep = dep;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] visited = new boolean[101];
        int N = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Queue<Node> queue = new ArrayDeque<>();
        visited[1] = true;
        queue.offer(new Node(1, 0));

        while (true) {
            Node node = queue.poll();

            if (node.idx == 100) {
                System.out.println(node.dep);
                return;
            }

            for(int i=1; i<7; i++) {
                int next = node.idx + i;
                for (int j=0; j<N; j++) {
                    if(next == arr[j][0]) {
                        next = arr[j][1];
                        break;
                    }
                }

                if (next > 100 || visited[next]) continue;
                visited[next] = true;
                queue.offer(new Node(next, node.dep + 1));
            }
        }
    }
}