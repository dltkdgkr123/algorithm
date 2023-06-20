import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int r,c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int time = 0;
        int[][][] map = new int[N][M][2];
        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if(map[i][j][0] == 1) cnt++;
            }
        }

        if(cnt == 0) {
            System.out.println(0);
            return;
        }

        while(cnt > 0) {
            time++;
            Queue<Node> queue = new ArrayDeque<>();

            boolean[][] visited = new boolean[N][M];
            queue.offer(new Node(0,0));
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                Node node = queue.poll();

                for(int i=0; i<4; i++) {
                    int mr = node.r + dr[i];
                    int mc = node.c + dc[i];

                    if (mr < 0 || mr >= N || mc < 0 || mc >= M) {
                        continue;
                    }

                    map[mr][mc][1]++;

                    if (visited[mr][mc] || map[mr][mc][0] == 1) {
                        continue;
                    }

                    visited[mr][mc] = true;
                    queue.offer(new Node(mr, mc));
                }
            }

            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if(map[i][j][0] == 1 && map[i][j][1] > 1) {
                        cnt--;
                        map[i][j][0] = 0;
                    }
                    map[i][j][1] = 0;
                }
            }
        }


        System.out.println(time);
    }
}
