public class TestProcessDestroy {

    public static void main(String[] argv) {
        try {
            final Process proc = Runtime.getRuntime().exec(new String[] { "cat" });
            new Thread() {

                public void run() {
                    try {
                        Thread.sleep(3000);
                        proc.destroy();
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            int exitCode = proc.waitFor();
            System.out.println("Process exited with code " + exitCode);
            System.out.println("TestProcessDestroy SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestProcessDestroy FAILURE");
        }
    }
}
