import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String text = reader.readLine().trim();
        int number = text.isEmpty() ? 0 : text.split("\\s+").length;
        System.out.println(number);
        reader.close();
    }
}