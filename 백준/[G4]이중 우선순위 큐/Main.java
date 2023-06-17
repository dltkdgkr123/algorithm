
/*
* N = 백만, 시간제한 6초
* 6(N^2) 미만으로 풀어야 한다. => 최적화하면 N^2로 풀어도 되긴 할 것 같다고 예상했다.
*
* 명령의 순서에따라 결과가 바뀌므로, 모든 원소를 받아놓고 한번만 정렬하는 것은 불가능하며, 받을 때 마다 정렬하면 시간초과이다.
*
* 우선순위로 정렬이 가능하며, 우선순위가 같은 원소의 개수를 저장할 수 있어야한다 => TreeMap or PriorityQueue
* 이 중, 확실히 통과하려면 첫 원소와 마지막 원소의 접근이 O(1)이나 O(logN)에 가능해야 한다. PriorityQueue에서 마지막 원소의 접근은 O(N)이고,
* 자바에 PriorityDeque라는 자료형은 없다고 한다. 따라서, TreeMap을 사용해야 한다.
*
* 다른 풀이
* 최대값순/최소값순 PriorityQueue를 각각 선언 후 원소를 둘 다 집어넣는다.
* 최소/최대값을 빼야할 때 각각의 pq에서 poll한다.
* 중복 값이 가능해서 각 큐에서는 원소가 사라지더라도 다른 큐에서 삭제할지 여부를 알 수 없으므로,
* Map으로 각 원소의 개수를 카운트해서 0이되면 반대편 큐에서도 삭제시킨다.
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        while(N-- > 0) {
            int M = Integer.parseInt(br.readLine());

            TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
            for(int i=0; i<M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                char order = st.nextToken().charAt(0);
                int key = Integer.parseInt(st.nextToken());
                switch (order) {
                    case 'I': {
                        map.put(key, map.getOrDefault(key, 0) + 1);
                    break;
                    }
                    case 'D': {
                        if (map.isEmpty()) continue;

                        // 최대값을 지워야 하면
                        if (key == 1) {

                            // 한개 존재하면 맵에서 삭제
                            if (map.get(map.firstKey()) == 1) {
                                map.remove(map.firstKey());
                            }
                            // 한개 초과 존재하면 value--;
                            else {
                                map.put(map.firstKey(), map.get(map.firstKey()) - 1);
                            }
                        }
                        // 최소값을 지워야 하면 => 위와 같은 동작
                        else {
                            if (map.get(map.lastKey()) == 1) {
                                map.remove(map.lastKey());
                            } else {
                                map.put(map.lastKey(), map.get(map.lastKey()) - 1);
                            }
                        }
                        break;
                    }
                }

            }
            System.out.println(map.isEmpty()? "EMPTY" : map.firstKey() + " " + map.lastKey());
        }

    }
}
