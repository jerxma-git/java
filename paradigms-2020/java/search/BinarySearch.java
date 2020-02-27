package search;

public class BinarySearch {
    public static void main(String[] args) {
    System.out.println(binSearch(args));        
    }

    protected static int binSearch(String[] args) {
        int[] array = getArr(args);
        int target = Integer.parseInt(args[0]);
        return iterativeSearch(array, target);
    }

    protected static int[] getArr(String[] args) {
        int[] array = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
        }
        return array;
    }

    // protected static int getTarget(String arg0) {
    //     return Integer.parseInt(arg0);
    // }

    public static int iterativeSearch(int[] arr, int target) {
        // for i = 0..arr.length - 2 a[i]>=a[i+1]  -   cond1
        int l = 0;
        int r = arr.length;
        // pre: r!= l && cond1
        while (r != l) {
            // 
            if (arr[(r + l) / 2] <= target) {
                r = (r + l) / 2;
            } else {
                l = (r + l) / 2 + 1;
            };
        }
        return r;
    }

    public static int recursiveSearch(int[] arr, int l, int r, int target) {
        if (r == l) {
            return r;
        }
        if (arr[(r + l) / 2] <= target) {
            return recursiveSearch(arr, l, (r + l) / 2, target);
        }
        return recursiveSearch(arr, (r + l) / 2 + 1, r, target);
    }
}