package search;

public class BinarySearchMissing extends BinarySearch {

    public static void main(String[] args) {
        int[] array = getArr(args);
        int target = Integer.parseInt(args[0]);
        int res = iterativeSearch(array, target);
        // int res = search.recursiveSearch(0, array.length, Integer.parseInt(args[0]));
        System.out.println((res == array.length) || (array[res] != target) ? -res - 1 : res);
    }

    

    
}