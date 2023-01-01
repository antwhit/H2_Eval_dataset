/**
 * @author unascribed
 */
class TestDispatch {

    public static int threadCount = 2;

    public static TestDispatchWorker[] workers = new TestDispatchWorker[threadCount];

    public static void main(String args[]) throws Exception {
        System.out.println("TestDispatch");
        for (int i = 0; i < threadCount; i++) {
            String name = "    worker " + i;
            for (int j = 0; j < i; j++) name = "                    " + name;
            workers[i] = new TestDispatchWorker(name);
        }
        for (int i = 0; i < threadCount; i++) workers[i].start();
        boolean done = false;
        while (!done) {
            Thread.currentThread().yield();
            done = true;
            for (int i = 0; i < threadCount; i++) if (!workers[i].isFinished) done = false;
        }
        TestDispatchWorker.say("main", "bye");
    }
}
