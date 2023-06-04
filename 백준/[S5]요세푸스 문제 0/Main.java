import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=1; i<=N; i++) {
            queue.offer(i);
        }

        StringBuilder sb = new StringBuilder("<");

        while (!queue.isEmpty()) {
            for(int i=0; i<K - 1; i++) {
                queue.offer(queue.poll());
            }
            sb.append(queue.poll()).append(", ");
        }

        sb.replace(sb.length()-2,sb.length()-1, ">");
        System.out.println(sb);
    }
}