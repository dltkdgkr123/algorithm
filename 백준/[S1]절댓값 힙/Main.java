import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static class Value implements Comparable<Value>{
        int originVal;
        int absVal;

        public Value(int originVal) {
            this.originVal = originVal;
            this.absVal = Math.abs(originVal);
        }

        @Override
        public int compareTo(Value o) {

            if(o.absVal == this.absVal) {
                return this.originVal - o.originVal;
            }

            return this.absVal - o.absVal;
        }

        @Override
        public String toString() {
            return originVal + "";
        }
    }

    static PriorityQueue<Value> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            int val = Integer.parseInt(br.readLine());

            if(val == 0) {
                print();
            }
            else {
                pq.offer(new Value(val));
            }
        }

        System.out.println(sb);
    }

    public static void print() {

        if (pq.isEmpty()) {
            sb.append(0);
        }
        else {
            sb.append(pq.poll());
        }

        sb.append("\n");
    }

}
