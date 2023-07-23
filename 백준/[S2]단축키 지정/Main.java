/*
* 소요 시간 : 1시간
*
* Stirng split 반환형 자체가 String[]이다.
* => String[] ex = str.split(" "); 하면 끝이다.
*
* 몰라서 steam쓰다가 Object[] -> String[] 캐스팅 오류 등 떠서 오래 걸렸다.
*
* 외부 루프 A안에 내부 루프 B가 있을 때 B를 만족하면 A도 Break시키는 방법
* => label(자바에는 goto문이 없다.), flag변수 사용
* flag변수 사용이 별로라고 생각해서 찾아봤는데 이게 권장되는 방법이라고 한다. 계속 사용해야 겠다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Set<Character> set = new HashSet<>();

        for (int i=0; i<N; i++) {
            String s = br.readLine();

            List<String> list = new ArrayList<>(Arrays.asList(s.split(" ")));
            boolean used = false;

            for(int j=0; j<list.size(); j++) {
                String str = list.get(j);
                String uStr = str.toUpperCase();
                if(!set.contains(uStr.charAt(0))) {
                    set.add(uStr.charAt(0));
                    list.set(j, transStr(str, 0));
                    used = true;
                    break;
                }
            }

            if(!used) {
                boolean end = false;
                for(int j=0; j<list.size(); j++) {

                    String str = list.get(j);
                    String uStr = str.toUpperCase();
                    for(int k=0; k<str.length(); k++) {

                        if(!set.contains(uStr.charAt(k))) {
                            set.add(uStr.charAt(k));
                            list.set(j, transStr(str, k));
                            end = true;
                            break;
                        }
                    }

                    if(end) {
                        break;
                    }
                }
            }

            stackStr(list);
        }

        System.out.println(sb);
    }

    public static String transStr(String str, int targetIdx) {
        StringBuilder sb = new StringBuilder();
        sb.append(str, 0, targetIdx).append("[").append(str.charAt(targetIdx)).append("]");

        if(targetIdx != str.length() -1) {
            sb.append(str.substring(targetIdx+1));
        }

        return sb.toString();
    }

    public static void stackStr(List<String> list) {

        for(String s : list) {
            sb.append(s).append(" ");
        }
        sb.append("\n");
    }
}
