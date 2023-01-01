class TestAll {

    public static void main(String args[]) throws Throwable {
        runTest();
    }

    public static void runTest() throws Throwable {
        SystemOut.println("TestAll");
        TestConstants.runTest();
        TestStackAccess.runTest();
        TestFieldAccess.runTest();
        TestArrayAccess.runTest();
        TestReturn.runTest();
        TestCompare.runTest();
        TestConversions_toHex.runTest();
        TestArithmetic_toHex.runTest();
        TestMath.runTest();
        TestMath_toHex.runTest();
        TestSwitch.runTest();
        TestStaticCall.runTest();
        TestVirtualCall.runTest();
        TestInterfaceCall.runTest();
        TestSpecialCall.runTest();
        TestClassInitializer.runTest();
        TestThrow.runTest();
        TestFinally.runTest();
        TestInstanceOf.runTest();
        TestClone.runTest();
        TestMonitorUnwind.runTest();
        TestGC.runTest();
    }
}
