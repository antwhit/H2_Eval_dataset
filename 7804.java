import SampleEvent2;
import iwork.eheap.*;
import iwork.IWRoomConfigValues;

class Catch2 {

    public static String getMyName() {
        return "Catch2";
    }

    public static void main(String args[]) {
        try {
            String heapMachine = IWRoomConfigValues.DEFAULT_HEAP_MACHINE;
            String heapName = IWRoomConfigValues.DEFAULT_HEAP_NAME;
            int heapPort = IWRoomConfigValues.DEFAULT_HEAP_PORT;
            int targetID = 99;
            int i;
            Event newSampleEvent;
            Integer numAccesses;
            Integer sampleIntValue;
            String sampleStringValue;
            for (i = 0; i < args.length; i++) {
                if (args[i].equals("-m")) heapMachine = args[++i]; else if (args[i].equals("-n")) heapName = args[++i]; else if (args[i].equals("-p")) heapPort = Integer.parseInt(args[++i]); else if (args[i].equals("-h")) {
                    System.out.println("Usage: " + getMyName() + " [-m <heapMachine>] " + "[-n <heapName>]\n" + "\t[-p <heapPort>] [-h] [<ourEventHeapID>]");
                    System.exit(1);
                } else break;
            }
            if (i < args.length) targetID = Integer.parseInt(args[i]);
            System.out.println("Starting multi-threaded event heap example");
            System.out.println("Connecting to Event Heap:\n" + "\tEHeap Host Machine:\t" + heapMachine + "\n" + "\tEHeap Name:\t\t" + heapName + "\n" + "\tEHeap Port:\t\t" + heapPort + "\n\n" + "\tOur ID:\t\t\t" + targetID);
            EventHeap theEventHeap = new EventHeap(targetID, heapName, heapMachine, heapPort);
            SampleEvent2 sampleTemplate = new SampleEvent2();
            sampleTemplate.setFieldValue(Event.TARGETID, targetID);
            while (true) {
                System.out.println("Waiting for sample event");
                if ((newSampleEvent = theEventHeap.waitForEvent(sampleTemplate)) != null) {
                    numAccesses = (Integer) newSampleEvent.getFieldValue(Event.NUMACCESSES);
                    sampleIntValue = (Integer) newSampleEvent.getFieldValue(SampleEvent2.SAMPLEINT);
                    sampleStringValue = (String) newSampleEvent.getFieldValue(SampleEvent2.SAMPLESTRING);
                    System.out.println("Retrieved sample event:\n" + "\tTargetID:\t" + targetID + "\n" + "\tNumAccesses:\t" + numAccesses + "\n" + "\tSampleInt:\t" + sampleIntValue + "\n" + "\tSampleString:\t" + sampleStringValue);
                } else System.out.println("Failed to get sample event!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
