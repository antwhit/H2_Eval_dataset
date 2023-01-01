import org.jikesrvm.runtime.VM_Magic;

class NativeThreadsWorker extends Thread {

    String name;

    boolean isFinished;

    NativeThreadsWorker(String name) {
        this.name = name;
        say(name, "creating");
    }

    public void start() {
        say(name, "starting");
        super.start();
    }

    public void run() {
        int tid = VM_Magic.getThreadIdRegister() >> VM_ObjectLayoutConstants.OBJECT_THREAD_ID_SHIFT;
        int retval = 0;
        int loopcntr = 75;
        float fp = (float) 17.8;
        if (name == "pong") loopcntr = 75;
        for (int i = 0; i < loopcntr; ++i) {
            if ((name == "ping") || (name == "ping2")) {
                say(name, "calling nativeFoo");
                retval = tNativeThreads.nativeFoo(VM_Processor.getCurrentProcessor().id);
                say(name, "return from nativeFoo");
            } else {
                say(name, "about to sleep for 100 msec");
                try {
                    say(name, "sleeping");
                    sleep(100);
                } catch (InterruptedException e) {
                }
                say(name, "after sleep -calling javaFoo");
                retval = tNativeThreads.javaFoo(10);
                say(name, "return from javaFoo");
            }
            if (name == "pong") {
                say(name, "about to call gc");
                System.gc();
                say(name, "gc completed");
            }
        }
        say(name, "complete -bye");
        isFinished = true;
    }

    static synchronized void say(String who, String what) {
        int pid = VM_Processor.getCurrentProcessorId();
        VM_Scheduler.trace(who, what);
    }
}
