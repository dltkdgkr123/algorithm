import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static class User {
        int age;
        String name;
        int pk;

        public User(int age, String name, int pk) {
            this.age = age;
            this.name = name;
            this.pk = pk;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        User[] arr = new User[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new User(Integer.parseInt(st.nextToken()), st.nextToken(), i);
        }

        Arrays.sort(arr, (o1, o2) -> {
            if(o1.age == o2.age) {
                return Integer.compare(o1.pk, o2.pk);
            }

            return Integer.compare(o1.age, o2.age);
        });

        for(User u : arr) {
            System.out.println(u.age + " " + u.name);
    }


    }
}