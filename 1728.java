class StringBuffervsString {

    StringBuffer str1 = new StringBuffer("This is a random");

    String str2 = str1 + " string";

    void f() {
        StringBuffer stringbuffer = new StringBuffer("Testing");
        stringbuffer.append(" one two three");
        StringBuffer stringb2;
        stringb2.setLength(4);
        StringBuffer stringb3;
        stringb3.insert("jjj", 4);
    }
}
