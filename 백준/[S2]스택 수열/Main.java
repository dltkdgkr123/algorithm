/*
* NO가 아니라 No 출력해서 오래걸렸다.
*
* 다루는 값은 정수의 오름차순이므로, stack1대신 int를 사용해서 복잡도를 줄일 수 있다.
* */

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        StringBuilder sb = new StringBuilder();

        for(int i=N; i>0; i--) {
            stack1.push(i);
        }

        for(int i=0; i<N; i++) {
            int val = Integer.parseInt(sc.nextLine());

            while (stack2.isEmpty() || stack2.peek() < val) {
                if(stack1.isEmpty() && val != stack2.peek()) {
                    System.out.println("NO");
                    return;
                }
                stack2.push(stack1.pop());
                sb.append('+').append('\n');
            }

            stack2.pop();
            sb.append('-').append('\n');
        }

        System.out.println(sb);
    }
}