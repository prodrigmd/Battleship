import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String first = reader.readLine();
        StringBuilder stringBuilder = new StringBuilder(first);
        StringBuilder reversed = stringBuilder.reverse();
        reader.close();
        System.out.println(reversed);
    }
}