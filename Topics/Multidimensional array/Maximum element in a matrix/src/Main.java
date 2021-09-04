import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // rows <= 100
        int m = scanner.nextInt(); // rows <= 100
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int max = matrix[0][0];
        int maxN = 0;
        int maxM = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxN = i;
                    maxM = j;
                }
            }
        }
        System.out.println(maxN + " " + maxM);
    }
}