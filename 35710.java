public class stacktrace {

    public static void main(String args[]) {
        try {
            new stacktrace().a();
        } catch (TopException e) {
        }
    }

    public void a() throws TopException {
        try {
            b();
        } catch (MiddleException e) {
            throw new TopException(e);
        }
    }

    public void b() throws MiddleException {
        c();
    }

    public void c() throws MiddleException {
        try {
            d();
        } catch (BottomException e) {
            throw new MiddleException(e);
        }
    }

    public void d() throws BottomException {
        e();
    }

    public void e() throws BottomException {
        throw new BottomException();
    }
}

class TopException extends Exception {

    TopException(Throwable cause) {
        super(cause);
    }
}

class MiddleException extends Exception {

    MiddleException(Throwable cause) {
        super(cause);
    }
}

class BottomException extends Exception {

    BottomException() {
        StackTraceElement stack[] = this.getStackTrace();
        for (int i = 0; i < stack.length; i++) {
            String className = stack[i].getClassName();
            String methodName = stack[i].getMethodName();
            System.out.println(className + "." + methodName);
        }
    }
}
