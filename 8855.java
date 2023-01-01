public interface Queue {

    public void put(Object item) throws Exception;

    public Object take() throws Exception;

    public boolean isEmpty();

    public Integer remainingCapacity();
}
