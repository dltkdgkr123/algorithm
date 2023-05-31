import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Tomato {
        int h, r, c;

        Tomato(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] dr = {1,0,-1,0};
        int[] dc = {0,1,0,-1};
        int[] dh = {1,-1};

        int ans = 0;
        int[][][] map = new int[h][r][c];
        boolean[][][] visited = new boolean[h][r][c];
        Queue<Tomato> queue = new ArrayDeque<>();

        int cnt = 0;

        for(int i=0; i<h; i++) {
            for(int j=0; j<r; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<c; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());

                    if(map[i][j][k] == 1) {
                        queue.offer(new Tomato(i,j,k));
                        visited[i][j][k] = true;
                    }
                    else if(map[i][j][k] == 0) {
                        cnt++;
                    }
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

                int th = tomato.h;
                int tr = tomato.r;
                int tc = tomato.c;


                for(int i=0; i<4; i++) {
                    int mr = tr + dr[i];
                    int mc = tc + dc[i];

                    if(mr >= 0 && mr < r && mc >= 0 && mc < c && !visited[th][mr][mc] && map[th][mr][mc] == 0) {
                        queue.offer(new Tomato(th, mr, mc));
                        visited[th][mr][mc] = true;
                        cnt--;
                    }
                }

                for(int i=0; i<2; i++) {
                    int mh = th + dh[i];
                    if(mh >= 0 && mh < h && !visited[mh][tr][tc] && map[mh][tr][tc] == 0) {
                        queue.offer(new Tomato(mh, tr, tc));
                        visited[mh][tr][tc] = true;
                        cnt--;
                    }
                }

            }
        }

        System.out.println(cnt == 0 ? ans : -1);
    }
}