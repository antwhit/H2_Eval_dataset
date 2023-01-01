class C {

    public static native int atol(String str);
}

class Win32 {

    public static native int CreateFile(String fileName, int desiredAccess, int shareMode, int[] secAttrs, int creationDistribution, int flagsAndAttributes, int templateFile);
}

class OneToOne {

    public static void main(String[] args) {
        System.out.println("atol(\"123\") = " + C.atol("123"));
        System.out.println("Creating a file called TestFile.tst in the current directory...");
        Win32.CreateFile("TestFile.tst", 0x40000000, 0, null, 2, 0x00000080, 0);
    }

    static {
        System.loadLibrary("OneToOne");
    }
}
