/*
* 브루트포스 문제 => 채널 범위 무한대이므로 기저조건 필요
*
* 기저조건
* 목표채널이 999일 때, 1000이 정답일 수 있으나 10000(한 자리 수 더 있는 경우) 정답인 반례가 없다는 가정하에 목표채널길이 +1 까지만 탐색
*
* 예외케이스
* N=0일 때, 입력을 아예 받지 않으므로 br.readLine() 안해야 함
* 목표채널 1이고 1번 못누를 때 => 0번 누르고 +버튼 눌러야 하므로 답은 2지만 처음 코드에서 시작 value 0이므로 답이 1로 나옴
* => 버튼 한번도 안눌렀을 때는 답이 아니게 처리 => 어차피 시작이 정답인 경우는 ans = Math.abs(goal - start);에서 걸러짐
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int ans, max, len, goal, start = 100;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        goal = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        max = getLen(goal);

        ans = Math.abs(goal - start);

        Set<Integer> tmp = new HashSet<>(Set.of(1,2,3,4,5,6,7,8,9,0));

        if(N != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (N-- > 0) {
                tmp.remove(Integer.parseInt(st.nextToken()));
            }
        }

        arr = tmp.stream().mapToInt(value -> value).toArray();
        len = arr.length;

        getAns(0, 0);
        System.out.println(ans);
    }

    public static void getAns(int val, int curLen) {

        if(curLen != 0) {
            ans = Math.min(Math.abs(goal - val) + curLen, ans);
        }

        if(curLen > max) {
            return;
        }

        for(int i=0; i<len; i++) {
            getAns(val * 10 + arr[i], curLen+1);
        }
    }



    // 목표 채널 길이 + 1 반환
    public static int getLen(int i) {
        int len = 1;

        while(i > 0) {
            i /= 10;
            len++;
        }

        return len;
    }

}