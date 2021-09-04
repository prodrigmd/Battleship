import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[][] matrix = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int iI = scanner.nextInt();
        int jJ = scanner.nextInt();

        int[] tempNums = new int[x];

        for (int i = 0; i < x; i++) {
            tempNums[i] = matrix[i][iI];
            matrix[i][iI] = matrix[i][jJ];
            matrix[i][jJ] = tempNums[i];
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}