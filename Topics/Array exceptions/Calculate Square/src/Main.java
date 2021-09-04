class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        // write your code here
        if (array != null) {
            System.out.println(index < array.length && index >= 0 ? (int) Math.pow(array[index], 2) : "Exception!");
        } else {
            System.out.println("Exception!");
        }
    }
}