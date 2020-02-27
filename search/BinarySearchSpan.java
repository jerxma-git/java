package search;

public class BinarySearchSpan {

    // pred = args.length >= 1 && i >= 0 && ((i < args.length) --> (Integer.parseInt(args[i]) doesn't throw Exception)) && numbers in args are sorted in non ascending order
    public static void main(String[] args) {
        // pred
        int[] array = new int[args.length - 1];
        // pred && (i >= 0 && i < array.length) --> (array[i] == 0)
        for (int i = 1; i < args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
        }
        // a[1..args.length - 1] = integers from args : a[i] >= a[i + 1]
        int target = Integer.parseInt(args[0]);
        // pred && target = integer in args[0] && a[1..args.length - 1] - integers : a[i] >= a[i + 1]
        int lb = search(array, target, true);
        // pred && (lb == arr.length || arr[lb] <= target) && (lb == 0 || a[lb - 1] > target)
        int ub = search(array, target, false);
        // pred && (lb == arr.length || arr[lb] <= target) && (lb == 0 || a[lb - 1] > target)
        //      && (ub == arr.length) || ((arr[ub] > target) && (arr[ub - 1] <= target))
        System.out.println(lb + " " + (ub - lb));
           
    }

    // cond2 = (arr.length > 0) && ((i >= 0 && i < arr.length - 2) --> (arr[i] >= arr[i + 1]))
    // pred: cond2
    // post: R - returned val; (R == arr.length) || ((arr[R] > target) && (arr[R - 1] <= target))
    protected static int iterativeSearchUpperBound(int[] arr, int target) { // upper_bound
        // cond2
        int l = -1;
        int r = arr.length;
        // cond2 && l == -1 && r == arr.length
        // inv = ((i <= l && i >= 0) --> (a[i] >= target)) && ((i >= r && i < arr.length) --> (a[i] < target))
        // var = r - l
        while (r - l != 1) {
            // cond2 && inv && r > l + 1
            int m = (r + l) / 2;
            // cond2 && inv && r > l + 1 && r > m > l
            if (arr[m] >= target) {
                // cond2 && inv && r > l + 1 && r > m > l && arr[m] >= target
                // => (i <= m && i >= 0) --> (arr[i] >= target)
                l = m;
                // cond2 && inv && r > l + 1 && r > m > l && arr[m] >= target && l' == m > l 
                // var = r - l' < r - l
            } else {
                // cond2 && inv && r > l + 1 && r > m > l && arr[m] < target
                // => (i >= m && i < arr.length) --> (arr[i] < target)
                r = m;
                // cond2 && inv && r > l + 1 && r > m > l && arr[m] >= target && r' == m < r
                // var = r' - l < r - l 
            }
            // cond2 && inv && r > l + 1 && r > m > l && arr[m] < target
        }
        // (r == arr.length) || ((arr[r] > target) && (arr[r - 1] <= target))
        return r;
    }

    // cond1 = (i < arr.length - 2 && i >= 0) --> (a[i] >= a[i + 1]) // array is sorted int non ascending order 
    
    // pre: cond1
    // post: R - returned val; (R == arr.length || arr[R] <= target) && (R == 0 || a[R - 1] > target) && lowerBound 
    //                         ||  (R == arr.length) || ((arr[R] > target) && (arr[R - 1] <= target)) && !lowerBound
    public static int search(int[] arr, int target, boolean lowerBound) {
        // cond1
        if (arr.length == 0) {
            // R = 0
            return 0;
        }
        // cond1 && arr.length > 0
        return lowerBound ? recursiveSearchLowerBound(arr, -1, arr.length, target)
                          : iterativeSearchUpperBound(arr, target);
    }

    // inv = ((i <= l) --> (i <= -1 || arr[i] > target)) && ((i >= r) --> (i >= arr.length || arr[i] <= target))
    // var: r - l
    // pre: cond1 && arr.length > 0 && r > l && inv
    // post: R - returned val; (R == arr.length || arr[R] <= target) && (R == 0 || a[R - 1] > target)
    protected static int recursiveSearchLowerBound(int[] arr, int l, int r, int target) {
        // cond1 && arr.length > 0 && r > l && inv
        if (r - l == 1) {
            // cond1 && arr.length > 0 && r - l == 1 && inv 
            // => (r == arr.length || arr[r] <= target) && (r == 0 || a[r - 1] > target)
            return r;
        }
        // cond1 && arr.length > 0 && r > l + 1 && inv
        int m = (r + l) / 2;
        // cond1 && arr.length > 0 && r > l + 1 && inv && l < m < r
        if (arr[m] <= target) {
            // cond1 && && arr.length > 0 && m > l && ((i <= l) --> (i == -1 || arr[i] > target)) && ((i >= m) --> (i == arr.length || arr[i] <= target))
            // var' = m - l < r - l = var
            return recursiveSearchLowerBound(arr, l, m, target);
        } else {
            // cond1 && && arr.length > 0 && r > m && ((i <= m) --> (i == -1 || arr[i] > target)) && ((i >= r) --> (i == arr.length || arr[i] <= target))
            // var' == r - m < r - l == var
            return recursiveSearchLowerBound(arr, m, r, target);
        }
    }
}