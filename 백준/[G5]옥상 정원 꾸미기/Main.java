/*
* 해답을 봤다.
*
* 설명에 빌딩(인덱스)별로 볼 수 있는 옥상 개수가 표시되어 있었다. => 3+0+1+0+1
* 총합만 구해도 되는 문제인데 빌딩별로 볼 수 있는 옥상 개수를 구해야하는 줄 착각해서 오래걸렸다.
*
* 매 순간 stack.size()-1를 구해준다. -1은 방금 들어온 빌딩을 제외하기 위함이며 스택에 남아있는 빌딩들의 볼 수 있는 옥상 개수를 각각(인덱스 별로) +1해주는 것과 같은 동작이다.
* 답의 최대값은 80,000 * 1,000,000,000이므로 long을 써야한다.
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long ans = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(br.readLine()));

        for(int i=1; i<N; i++) {

            int val = Integer.parseInt(br.readLine());

            while(!stack.isEmpty() && val >= stack.peek()) {
                stack.pop();
            }
            stack.push(val);

            ans += stack.size()-1;
        }

        System.out.println(ans);
    }
}