import java.util.*;

public class TestMethod {

    public List m_List;

    public TestMethod() {
    }

    public List myMethod(String x, List y) {
        return m_List;
    }

    public String getData(String x, String y) {
        return (x + y);
    }

    public String getDataAsXml(String x, String y) {
        return "<Test>" + (x + y) + "</Test>";
    }

    public TestStatus testMethod1(String str, int code, float myfloat, double mydouble) {
        str = "Return";
        code = 10;
        myfloat = 10;
        mydouble = 10.0;
        TestStatus x = new TestStatus();
        x.aResult = true;
        x.aWDIErrSeq = new TestError[4];
        for (int i = 0; i < 4; i++) {
            x.aWDIErrSeq[i] = new TestError();
            x.aWDIErrSeq[i].code = i + 1;
            x.aWDIErrSeq[i].reason = "Test";
        }
        return x;
    }

    public TestStatus testMethod2(String str, int[] code, float myfloat, double mydouble) {
        str = "Return";
        for (int i = 0; i < code.length; i++) code[i] *= 2;
        myfloat *= 2;
        mydouble *= 2;
        TestStatus x = new TestStatus();
        x.aResult = true;
        x.aWDIErrSeq = new TestError[4];
        for (int i = 0; i < 4; i++) {
            x.aWDIErrSeq[i] = new TestError();
            x.aWDIErrSeq[i].code = i + 1;
            x.aWDIErrSeq[i].reason = "Test";
        }
        return x;
    }

    public TestStatus[] testMethod3(String str, int[] code, float myfloat, double mydouble) {
        str = "Return";
        for (int i = 0; i < code.length; i++) code[i] *= 2;
        myfloat *= 2;
        mydouble *= 2;
        TestStatus[] x = new TestStatus[2];
        for (int i = 0; i < 2; i++) {
            x[i] = new TestStatus();
            x[i].aResult = true;
            x[i].aWDIErrSeq = new TestError[i + 2];
            for (int j = 0; j < x[i].aWDIErrSeq.length; j++) {
                x[i].aWDIErrSeq[j] = new TestError();
                x[i].aWDIErrSeq[j].code = (i + 1) * (j + 1);
                x[i].aWDIErrSeq[j].reason = "Test";
            }
        }
        return x;
    }

    public TestStatus testMethod4(TestError errSeq, TestEvent testevent) {
        TestStatus x = new TestStatus();
        x.aResult = true;
        x.aWDIErrSeq = new TestError[4];
        for (int i = 0; i < 4; i++) {
            x.aWDIErrSeq[i] = new TestError();
            x.aWDIErrSeq[i].code = i + 1;
            x.aWDIErrSeq[i].reason = "Test";
        }
        testevent.servItemID = 2;
        errSeq.code = 2;
        return x;
    }

    public TestStatus testMethod5(TestError[] errSeq, TestEvent testevent) {
        TestStatus x = new TestStatus();
        x.aResult = true;
        x.aWDIErrSeq = new TestError[4];
        for (int i = 0; i < 4; i++) {
            x.aWDIErrSeq[i] = new TestError();
            x.aWDIErrSeq[i].code = i + 1;
            x.aWDIErrSeq[i].reason = "Test";
        }
        testevent.servItemID = 2;
        errSeq[0].code = 2;
        errSeq[0].reason = "Return";
        return x;
    }

    public static void main(String[] args) {
        TestMethod x = new TestMethod();
        System.out.println(x.getData("Rohit", "Mital"));
        int y = 2;
        x.testMethod1("Rohit", y, 10, 10);
        System.out.println(y);
    }
}
