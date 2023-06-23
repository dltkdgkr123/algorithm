/*
* stack 문제, N이 100만이므로 O(N^2)하면 시간 초과
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static class Node{

        int val, idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Node> stack = new Stack<>();
        int[] ans = new int[N];
        Arrays.fill(ans, -1);

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty()) {
                Node node = stack.peek();

                if(node.val < num) {
                    ans[node.idx] = num;
                    stack.pop();
                }
                else {
                    stack.add(new Node(num, i));
                    break;
                }
            }

            if(stack.isEmpty()) {
                stack.add(new Node(num, i));
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int i : ans) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
