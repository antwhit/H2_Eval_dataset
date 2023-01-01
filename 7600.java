interface Eventifiable {

    public EventSet createEventSet(DocumentSet ds);
}

abstract class EventDriver implements Eventifiable {

    public abstract EventSet createEventSet(DocumentSet ds);
}
