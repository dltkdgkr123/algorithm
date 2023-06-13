/*
* 다익스트라 기본문제 => 인접배열로 풀면 20000 * 300000 * 4byte = 2.4gb 메모리초과
* 인접 리스트로 풀어야 함
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static class Node {

        int vertex, distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int first = Integer.parseInt(br.readLine());

        List<List<Node>> adjList = IntStream.range(0, R+1).mapToObj(value -> new ArrayList<Node>()).collect(Collectors.toList());

        boolean[] visited = new boolean[R+1];
        int[] disArr = new int[R+1];

        Arrays.fill(disArr, Integer.MAX_VALUE);
        disArr[first] = 0;

        for(int i=0; i<C; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            adjList.get(from).add(new Node(to, dis));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.distance));

        pq.offer(new Node(first, 0));

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();

            if(visited[curNode.vertex]) continue;

            visited[curNode.vertex] = true;

            for(Node node : adjList.get(curNode.vertex)) {

                int newDis = node.distance + disArr[curNode.vertex];

                if(newDis < disArr[node.vertex]) {
                    disArr[node.vertex] = newDis;
                }

                pq.offer(new Node(node.vertex, disArr[node.vertex]));
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=R; i++) {
            int dis = disArr[i];
            sb.append(dis == Integer.MAX_VALUE? "INF" : dis).append("\n");
        }

        System.out.println(sb);
    }
}
