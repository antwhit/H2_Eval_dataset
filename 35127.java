public interface EntryManager {

    public abstract void addEntry(AuctionEntry ae);

    public abstract void delEntry(AuctionEntry ae);

    public abstract AuctionEntry getEntry(String id);
}
