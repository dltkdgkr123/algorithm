/*
* 매 roof(세대) 마다 "끝점 부터(리스트의 마지막 인덱스부터)" 연결된 직선의 방향을 + 1 % 4하여 리스트에 추가한다
* 세대가 0일 경우, 하나의 직선을 만든다 => 사각형을 무조건 0개 생성하므로 ans = 0으로 놓일 때 고려하지 않아도 된다.
* 드래곤 커브가 경계선을 넘는 경우는 주어지지 않는다고 했으므로 경계체크를 하지 않는다.
* 드래곤 커브는 서로 겹칠 수 있으므로 중복제거를 하지 않아도 된다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int[] dr = {0,-1,0,1}, dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException {

        visited = new boolean[101][101];
        int ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            curve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) ans++;
            }
        }

        System.out.println(ans);
    }

    public static void curve(int c, int r, int dir, int gener) {

        visited[r][c] = true;

        List<Integer> list = new ArrayList<>();
        list.add(dir);

        for(int i=0; i<gener; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add((list.get(j) + 1) % 4);
            }
        }

        for (Integer i : list) {
            r += dr[i];
            c += dc[i];

            visited[r][c] = true;
        }
    }
}