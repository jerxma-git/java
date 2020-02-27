public class Test {
    private static int biba = 2;
    private int biba2 = -2;
    public static void main(String[] args) {
        Test test = new Test();
        test.biba2 -= 2;
        Test.biba += 2;
        System.out.println(test.biba2);
        System.out.println(Test.biba);
    }
}