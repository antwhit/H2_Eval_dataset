import iwork.eheap2.*;
import iwork.patchpanel.*;

/**
* This program measures the time for event heap round trip
 *
 * @author Rafael Ballagas
 */
public class formal {

    public static void main(String[] argv) {
        try {
            EventHeap eh;
            if (argv.length > 0) eh = new EventHeap(argv[0]); else eh = new EventHeap("localhost");
            Event event = new Event("CompareEvent");
            event.addField("num", Integer.class, FieldValueTypes.FORMAL, FieldValueTypes.FORMAL);
            event.addField("otherNum", Integer.class, FieldValueTypes.FORMAL, FieldValueTypes.FORMAL);
            Event[] newEventList = new Event[1];
            newEventList[0] = new Event("BenchmarkOutputEvent");
            newEventList[0].addField("numTimesOther", "=(int)in.num * in.otherNum");
            Event eventToSend = new Event("CompareEvent");
            eventToSend.addField("num", new Integer(3));
            eventToSend.addField("otherNum", new Integer(4));
            PatchPanel pp = new PatchPanel(eh);
            pp.setMapping(new Mapping(event, newEventList));
            eh.putEvent(eventToSend);
            Event template = new Event("BenchmarkOutputEvent");
            Event received = eh.waitForEvent(template);
            if (received.getPostValueString("numTimesOther").equals("12") && received.getFieldType("numTimesOther").equals("int")) {
                System.out.println("test passed");
            } else {
                System.out.println("test failed");
            }
            Event[] secondEventList = new Event[1];
            secondEventList[0] = new Event("GotItAlso");
            pp.setMapping(new Mapping(eventToSend, secondEventList));
            eh.putEvent(eventToSend);
            Event received2 = eh.waitForEvent(template);
            Event received3 = eh.waitForEvent(secondEventList[0]);
            if (received2.getPostValueString("numTimesOther").equals("12") && received2.getFieldType("numTimesOther").equals("int")) {
                if (received3.getEventType().equals("GotItAlso")) {
                    System.out.println("test passed");
                } else {
                    System.out.println("test failed");
                }
            } else {
                System.out.println("test failed");
            }
            Event eventToSend2 = new Event("CompareEvent");
            eh.setServerTimeout(5000);
            eh.putEvent(eventToSend2);
            Event shouldBnothing = eh.waitForEvent(template);
            if (shouldBnothing == null) {
                System.out.println("test passed");
            } else {
                System.out.println("test failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
