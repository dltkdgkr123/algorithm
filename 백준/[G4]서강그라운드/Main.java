/*
*
* 오래 걸림
*
* 다익스트라 or 플로이드-와샬 문제
* => 플로이드-와샬 몰라서 다익스트라 정점 개수만큼 반복해서 풀었음
*
* compareTo에 return n.weight;로 잘못 적어서 그리디하게 못뽑아서 visited 주석처리해야 정답이였음
* 후에 compareTo잘못된거 찾아서 고치니까 visited 써도 정답 되었음
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node>{
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] cost = new int[N+1];
        int ans = 0;

        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] adjMat = new int[N+1][N+1];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjMat[from][to] = weight;
            adjMat[to][from] = weight;
        }

        for(int i=1; i<=N; i++) {

            boolean[] visited = new boolean[N+1];
            int[] value = new int[N+1];

            Arrays.fill(value, 3001);
            value[i] = 0;
            PriorityQueue<Node> pq = new PriorityQueue();
            pq.offer(new Node(i, 0));

            while(!pq.isEmpty()) {
                Node node = pq.poll();
                int vertex = node.vertex;
                int weight = node.weight;

                if(visited[vertex]) continue;
                visited[vertex] = true;

                for(int j=1; j<=N; j++) {
                    if(adjMat[vertex][j] != 0 && value[vertex] + adjMat[vertex][j] < value[j]) {
                        value[j] = value[vertex] + adjMat[vertex][j];
                        pq.offer(new Node(j, value[j]));
                    }
                }
            }

            int cur = 0;

            for(int idx=1; idx<=N; idx++) {
                if(value[idx] <= M) {
                    cur += cost[idx];
                }
            }
            ans = Math.max(ans, cur);
        }
            System.out.println(ans);
        }
    }

