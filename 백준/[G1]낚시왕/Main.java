/*
*
* R*C = 10000 , M <= R*C
* 1만*1만 = 1억으로 선형시간의 로직이여야 통과 가능하다고 유추했다. 근데 델타써도 600ms고 mod지워서 2500ms 나와도 통과해준다.
*
* 생각
* (배열의(행or열)의 크기 -1) *2만큼 이동하면 같은 방향으로 같은 자리에 서게된다. mod로 복잡도를 줄여준다.
* => 했을 때 600ms, 안했을 때 2500ms
*
* 우선순위 큐나 큐를 쓰면 배열과 같이 관리하기 까다로울 것 같았다. (낚시했을 때, 잡아먹힐 때 뺴야 함)
* => 배열이 100*100이라 그냥 썼다.
*
* 잡아먹는 동작은 순서와 상관없으나 이동하는 동작은 인덱스대로 탐색하면 이미 움직였는데 다음 인덱스에 탐색될 때 문제가 발생한다.
* => 새 tmp배열을 만들어 깊은복사했다. 상어 클래스 안에 움직였음을 체크하는 boolean변수를 넣으면 같은 인덱스에 움직인 상어와 안움직인 상어가 있을 때 또 처리해야한다.
*
* 시간복잡도 터질까봐 델타가 아닌 상어의 이동 로직을 생각하는데 오래걸렸다. mod연산을 했을 때 생각해야할 경우는 6가지였다. (로직은 3개)
* 왼쪽방향 상어 기준으로
* 1. 왼쪽으로 이동할 때
* 2. 왼쪽으로 이동할 때 벽에 부딫힌 경우
* 3. 왼쪽으로 이동할 때 벽에 부딫히고 오른쪽 벽에도 부딫힌 경우
* 4/5/6. 오른쪽도 왼쪽과 같은 3가지의 경우
*
* 구현하다가 가로/세로/왼/위일 때 다 바뀌는게 머리아파서 해답을 참고했는데 전부 델타로 하길래 델타로 바꿨다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, sSize;
    static Shark[][] map;
    static int ans;
    static class Shark {
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sSize = Integer.parseInt(st.nextToken());

        map = new Shark[R][C];

        for(int i=0; i<sSize; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = new Shark(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int fc=0; fc<C; fc++) {

            int fr = 0;

            while(fr < R) {
                if(map[fr][fc] != null) {
                    Shark shark = map[fr][fc];
                    ans += shark.z;
                    map[fr][fc] = null;
                    break;
                }

                fr++;
            }

            Shark[][] tmpMap = new Shark[R][C];

            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {

                    if(map[i][j] != null) {
                        Shark shark = map[i][j];

                        int s = shark.d == 3 || shark.d == 4 ? shark.s % ((C-1) * 2) : shark.s % ((R-1) * 2);

                        while(s > 0) {

                            if(shark.d == 4) {
                                if(shark.c == 0) {
                                    shark.d = 3;
                                    continue;
                                }
                                s--;
                                shark.c--;
                            }
                            else if(shark.d == 3) {
                                if(shark.c == C-1) {
                                    shark.d = 4;
                                    continue;
                                }
                                s--;
                                shark.c++;
                            }
                            else if(shark.d == 2) {
                                if(shark.r == R-1) {
                                    shark.d = 1;
                                    continue;
                                }
                                s--;
                                shark.r++;
                            }
                            else if(shark.d == 1) {
                                if(shark.r == 0) {
                                    shark.d = 2;
                                    continue;
                                }
                                s--;
                                shark.r--;
                            }
                        }

                        if(tmpMap[shark.r][shark.c] == null || tmpMap[shark.r][shark.c].z < shark.z) {
                            tmpMap[shark.r][shark.c] = shark;
                        }

                    }
                }
            }

            map = deepCopy(tmpMap);
        }

        System.out.println(ans);
    }

    static Shark[][] deepCopy(Shark[][] tmpMap) {

        Shark[][] map = new Shark[R][C];

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }

        return map;
    }
}
