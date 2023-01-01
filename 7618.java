import iwork.eheap2.*;

class SimpleLogger {

    static class OurCallback implements EventCallback {

        public boolean returnEvent(Event[] retEvents) {
            try {
                System.out.println("Got event with type '" + retEvents[0].getPostValue(Event.EVENTTYPE) + "' from source '" + retEvents[0].getPostValue(Event.SOURCE) + "'.");
                return true;
            } catch (Exception e) {
            }
            return true;
        }
    }

    public static void main(String args[]) {
        String heapMachine = args[0];
        try {
            EventHeap theEventHeap = new EventHeap(heapMachine);
            EventRegistration theRegistration = theEventHeap.registerForAll(new OurCallback());
            while (true) ;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
