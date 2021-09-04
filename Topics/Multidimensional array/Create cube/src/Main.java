class ArrayOperations {
    public static int[][][] createCube() {
        // write your code here
        int n = 3;
        int m = 3;
        int l = 3;
        int[][][] cube = new int[n][m][l];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < l; k++) {
                    cube[i][j][k] = k + 3 * j;
                }
            }
        }
        return cube;

    }
}