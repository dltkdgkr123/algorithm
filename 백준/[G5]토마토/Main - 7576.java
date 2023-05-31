import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Tomato {
        int r, c;

        Tomato(int r, int c) {
            this.r = r;
            this.c = c;

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] dr = {1,0,-1,0};
        int[] dc = {0,1,0,-1};

        int ans = 0;
        int[][] map = new int[r][c];
        boolean[][] visited = new boolean[r][c];
        Queue<Tomato> queue = new ArrayDeque<>();

        int cnt = 0;

            for(int i=0; i<r; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<c; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 1) {
                        queue.offer(new Tomato(i,j));
                        visited[i][j] = true;
                    }
                    else if(map[i][j] == 0) {
                        cnt++;
                    }
                }
            }

        while(!queue.isEmpty()) {

            if(cnt == 0) {
                System.out.println(ans);
                return;
            }

            ans++;
            int size = queue.size();
            for(int s=0; s<size; s++) {

                Tomato tomato = queue.poll();

                int tr = tomato.r;
                int tc = tomato.c;


                for(int i=0; i<4; i++) {
                    int mr = tr + dr[i];
                    int mc = tc + dc[i];

                    if(mr >= 0 && mr < r && mc >= 0 && mc < c && !visited[mr][mc] && map[mr][mc] == 0) {
                        queue.offer(new Tomato(mr, mc));
                        visited[mr][mc] = true;
                        cnt--;
                    }
                }

            }
        }

        System.out.println(cnt == 0 ? ans : -1);
    }
}