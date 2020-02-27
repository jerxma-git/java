import search.*;

public class Test {
    public static void main(String[] args) {
        int[] a = new int[]{5, 4, 3, 2, 1};
        System.out.println(BinarySearchSpan.iterativeSearchUpperBound(a, 6));
        System.out.println(BinarySearchSpan.iterativeSearchUpperBound(a, 5));
        System.out.println(BinarySearchSpan.iterativeSearchUpperBound(a, 4));
        System.out.println(BinarySearchSpan.iterativeSearchUpperBound(a, 3));
        System.out.println(BinarySearchSpan.iterativeSearchUpperBound(a, 2));
        System.out.println(BinarySearchSpan.iterativeSearchUpperBound(a, 1));
        System.out.println(BinarySearchSpan.iterativeSearchUpperBound(a, 0));

    }
}