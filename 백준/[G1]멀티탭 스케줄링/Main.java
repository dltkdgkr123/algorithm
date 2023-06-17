/*
* 시간복잡도 0(K^(K-N) => 브루트포스 불가
*
* 여러 경우에 따라 다른 처리를 하는 (일부)그리디 문제이다.
*
* 콘센트에 같은 전자제품이 두개 꽃혀져 있는 경우는 존재하지 않는다.
* 이걸 생각못하고 카운팅하기위해 boolean arr 대신 Map을 사용하면서 어렵게 풀었다.
*
* 콘센트에 있는 것을 탐색하며 후에 사용되지 않는 기기가 있다면 우선으로 뽑는다.
* => 있을 때 뽑을 필요 없이 중복체크 없이 기기를 리스트에 넣으며 모두 후에 사용되는지만 체크 후 모두 사용된다면 (리스트의 사이즈가 콘센트와 같다면)
* 마지막 인덱스의 기기만 뽑으면 된다. 이것도 마찬가지로 어렵게 풀었다.
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = N;
        int idx = 0;
        int ans = 0;
        int[] cmd = new int[M];
        Map<Integer, Integer> use = new HashMap<>();

        st  = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            cmd[i] = Integer.parseInt(st.nextToken());
        }

        // 꽃을 수 있는 만큼 일단 꽃음
        while(cnt > 0 && idx < M) {

            if(use.containsKey(cmd[idx])) {
                idx++;
                continue;
            }

            use.put(cmd[idx], 1);
            cnt--;
            idx++;
        }

        for(; idx<M; idx++) {

            // 이미 꽃혀있는 기기라면 뺼 필요 없이 다음 인덱스로
            if(use.containsKey(cmd[idx])) {
                continue;
            }

            // 꽃혀있지 않은 기기라면
            int cur = M-1;
            Set<Integer> set = new HashSet<>();

            // 후에 사용하는 기기 목록을 만들고
            while(cur > idx) {
                set.add(cmd[cur]);
                cur--;
            }
            boolean b = false;

            // 꽃혀있는 기기들 중에
            for(int i : use.keySet()) {
                // 후에 사용되지 않는 기기가 있는 경우 뽑고 break
                if(!set.contains(i)) {
                    ans++;
                    use.put(cmd[idx], use.getOrDefault(cmd[idx], 0) + 1);
                    b = true;

                    if(use.get(i) == 1) {
                        use.remove(i);
                    }
                    else {
                        use.put(i, use.get(i) - 1);
                    }
                    break;
                }
            }

            // 사용되는 기기 뽑았으면 다음 인덱스로
            if(b) {
                continue;
            }

            // 후에 사용되지 않는 기기가 하나도 없을 경우 맨 마지막으로 사용되는 기기를 뽑음

            // 각 기기들의 가장 먼저 사용되는 인덱스를 Map에 저장
            Map<Integer, Integer> cntMap = new HashMap<>();
            for(int i=idx+1; i<M; i++) {
                for(int k : use.keySet()) {
                    if(k == cmd[i]) {
                        if(cntMap.containsKey(k)) {
                            cntMap.put(k, Math.min(cntMap.get(k), i));
                        }
                        else {
                            cntMap.put(k, i);
                        }
                    }
                }
            }

            // 기기들 중 가장 먼저 사용되는 인덱스가 가장 큰 기기를 찾아서 뺌
            int key = 0, value = 0;
            for(int i : use.keySet()) {
                if(cntMap.get(i) > value) {
                    key = i;
                    value = cntMap.get(i);
                }
            }

            ans++;
            use.put(cmd[idx], use.getOrDefault(cmd[idx], 0) + 1);

            if(use.get(key) == 1) {
                use.remove(key);
            }
            else {
                use.put(key, use.get(key) - 1);
            }
        }

        System.out.println(ans);
    }
}