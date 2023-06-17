/*
* 브루트포스 시간복잡도 O(4^9999) -> 불가
* 알파벳 수가 가장 적은게 답(최단경로) -> BFS
* 입력으로 출력을 만들 수 있음이 보장됨 -> queue.isEmpty() 필요 없을 듯
* 알파벳 개수만 같으면 무슨 명령어던지 상관없음 -> 그냥 배열 덮어씌우면 됨
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String[] cmd = new String[10000];
            Arrays.fill(cmd, "");
            boolean[] visited = new boolean[10000];
            int start = Integer.parseInt(st.nextToken());
            int goal = Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            visited[start] = true;

            while (!queue.isEmpty() && queue.peek() != goal) {
                int val = queue.poll();
                int newVal = 0;
                String newCmd = "";
                for(int i=0; i<4; i++) {
                    switch (i) {
                        case 0: {
                            newVal = val * 2 % 10000;
                            newCmd = "D";
                            break;
                        }
                        case 1: {
                            newVal = val == 0? 9999 : val - 1;
                            newCmd = "S";
                            break;
                        }
                        case 2: {
                            newVal = (val % 1000 * 10) + val / 1000;
                            newCmd = "L";
                            break;
                        }
                        case 3: {
                            newVal = (val / 10) + val % 10 * 1000;
                            newCmd = "R";
                            break;
                        }
                    }

                    if(visited[newVal]) {
                        continue;
                    }

                    visited[newVal] = true;
                    queue.offer(newVal);
                    cmd[newVal] = cmd[val] + newCmd;
                }
            }

            System.out.println(cmd[queue.peek()]);
        }
    }
}