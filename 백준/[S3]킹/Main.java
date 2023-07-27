/*
* 걸린 시간 : 1시간
*
* 단순 구현 문제고 동작은 매우 간단하다.
*
* 하지만,
* 1. row 인덱스가 1부터 시작 + 내림차순
* 2. col은 문자여서 형변환(int/char/string간의 문자열 붙이기)
* 3. A8 -> row + col이 아닌 col + row순서
*
* 이것들을 고려하느라 오래 걸렸다.
* + case 람다식 백준 컴파일러(java11)에선 불가능하다.
*
* 접근 방법
* 일반적인 배열의 인덱스로 동작을 처리한 후,
* 체스판 식의 좌표가 담긴 배열을 새로 만들어 매칭하는 인덱스의 값을 출력한다.
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Pos {

        int r,c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String K = st.nextToken();
        String S = st.nextToken();

        int[] tmp = {7,6,5,4,3,2,1,0};

        int kr = tmp[K.charAt(1)-'1'];
        int kc = K.charAt(0) - 'A';

        int sr = tmp[S.charAt(1)-'1'];
        int sc = S.charAt(0) - 'A';

        int N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            String command = br.readLine();

            Pos kp = move(kr, kc, command);
            int dkr = kp.r;
            int dkc = kp.c;

            if(dkr < 0 || dkr > 7 || dkc < 0 || dkc > 7) continue;

            if (dkr == sr && dkc == sc) {
                Pos sp = move(sr, sc, command);
                int skr = sp.r;
                int skc = sp.c;

                if(skr < 0 || skr > 7 || skc < 0 || skc > 7) continue;

                kr = dkr;
                kc = dkc;
                sr = skr;
                sc = skc;
            }
            else {
                kr = dkr;
                kc = dkc;
            }
        }

        printAns(kr, kc);
        printAns(sr, sc);
    }

    public static  Pos move(int r, int c, String command) {

        switch (command) {
            case "R" : {
                c++;
                break;
            }
            case "L" : {
                c--;
                break;
            }
            case "B" : {
                r++;
                break;
            }
            case "T" : {
                r--;
                break;
            }
            case "RT" : {
                r--;
                c++;
                break;
            }
            case "LT" : {
                r--;
                c--;
                break;
            }
            case "RB" : {
                r++;
                c++;
                break;
            }
            case "LB" : {
                r++;
                c--;
                break;
            }
        }

        return new Pos(r,c);
    }

    static void printAns(int r, int c) {

        String[][] ans = new String[8][8];

        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {

                String ar = String.valueOf(8-i);
                char ac = (char) (65+j);

                String pos = ac + ar;
                ans[i][j] = pos;
            }
        }
        System.out.println(ans[r][c]);
    }

}
