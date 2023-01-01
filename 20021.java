class BagOfIntMain {

    public static void main(String[] argv) {
        int[] mine = new int[] { 0, 10, 20, 30, 40, 10 };
        BagOfInt b = new BagOfInt(mine);
        System.out.println("b.occurrences(10) == " + b.occurrences(10));
        int em1 = b.extractMin();
        int em2 = b.extractMin();
        int em3 = b.extractMin();
    }
}
