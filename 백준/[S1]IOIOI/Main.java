/*
* M = 100만 O(M^2)하면 시간초과
* 인덱스 순차탐색하며 I가 나오면 뒤에 나오는 OI 개수를 새서 cnt에 저장 아니면 인덱스++
* cnt = 5, M = 3일 때 PN이 3개 포함됨 => 점화식(cnt - N + 1)을 ans에 중첩
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int cur = 0, ans = 0;

        while(cur < s.length()) {
            if(s.charAt(cur) == 'I') {

                int cnt = 0;
                while (cur < s.length()) {
                    if(s.startsWith("OI", cur+1)) {
                        cnt++;
                        cur += 2;
                    }
                    else { cur++; break; }
                }

                if(cnt >= N) {
                    ans += cnt - N + 1;
                }
            }
            else { cur++; }

        }

        System.out.println(ans);
    }
}
