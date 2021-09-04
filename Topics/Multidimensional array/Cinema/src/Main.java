import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // rows <=20
        int m = scanner.nextInt(); // seats per row <=20
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int k = scanner.nextInt();
        int yourRow = 0;
        boolean stopIt = false;

        for (int i = 0; i < n && !stopIt; i++) {
            int count = 1;
            for (int j = 1; j < m; j++) {
                if (k > 1 && matrix[i][j] == 0 && matrix[i][j - 1] == 0) {
                    count++;
                    if (count == k) {
                        yourRow = i + 1;
                        stopIt = true;
                        break;
                    }
                } else if (k > 1 && matrix[i][j] == 1) {
                    count--;
                } else if (k == 1 && matrix[i][j - 1] == 0 || matrix[i][j] == 0 && count == k) {
                    yourRow = i + 1;
                    stopIt = true;
                    break;
                }
            }
        }
        System.out.println(yourRow);
    }
}