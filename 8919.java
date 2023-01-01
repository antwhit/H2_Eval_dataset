/**
 * Defines a single auction.
 * 
 * Docentes:
 * 	Joao Soares
 * 	Miguel Monteiro
 * 
 * @author David Serrano, 	n. 28597,	TP04,	P08
 * @author Diogo Cordeiro, 	n. 28633,	TP04,	P08
 */
public class Auction {

    public static final int STATUS_UNKNOWN = -1;

    public static final int STATUS_NEW = 0;

    public static final int STATUS_OPEN = 1;

    public static final int STATUS_CLOSED = 2;

    private String name;

    private ItemCollection items;

    private int status;

    /**
	 * Constructs an auction with a predefined
	 * name.
	 * @param auctionName
	 * 	The predefined name.
	 */
    public Auction(String auctionName, int auctionStatus) {
        status = auctionStatus;
        items = new ItemCollection();
        name = auctionName;
    }

    /**
	 * Adds an item to this auction.
	 * 
	 * @param name
	 * 	The new item's name.
	 * @param seller
	 * 	The new item's seller name.
	 * @param basePrice
	 * 	The new item's base price.
	 * @param bestBid
	 * 	The new item's best bid.
	 * @param bestBidder
	 * 	The new item's best bidder. It should be
	 * an existing customer.
	 * @param bidSum
	 * 	The sum of all bids made to the new item.
	 * @param bidCount
	 * 	The number of bids made to the new item.
	 */
    public void addItem(String name, String seller, int basePrice, int bestBid, String bestBidder, int bidSum, int bidCount) {
        items.addItem(name, seller, basePrice, bestBid, bestBidder, bidSum, bidCount);
    }

    /**
	 * Changes the auction's status to
	 * <code>Auction.STATUS_OPEN</code>
	 */
    public void open() {
        status = STATUS_OPEN;
    }

    /**
	 * Changes the auction's status to
	 * <code>Auction.STATUS_CLOSED</code>
	 */
    public void terminate() {
        status = STATUS_CLOSED;
        items.sort();
    }

    /**
	 * Places a bid to a certain item in this auction.
	 * 
	 * @param itemName
	 * 	The item's name.
	 * @param bidder
	 * 	The bidder's name.
	 * @param value
	 * 	The value of the bid (in euros).
	 */
    public void setItemBid(String itemName, String bidder, int value) {
        items.setBid(itemName, bidder, value);
    }

    /**
	 * Sees if a certain exists in this auction.
	 * 
	 * @param name
	 * 	The item's name.
	 * @return
	 * 	Returns true if the item exists in this
	 * auction. Returns false otherwise.
	 */
    public boolean itemExists(String name) {
        return items.exists(name);
    }

    /**
	 * Gets the data relative to the auction
	 * to save in a text file.
	 * 
	 * @return
	 *  The data relative to the auction
	 * to save in a text file.
	 */
    public String getData() {
        return "A\n" + getName() + "\n" + getStatus() + "\n" + items.getItemData() + "EA\n";
    }

    /**
	 * Gets an item's base price.
	 * 
	 * @param name
	 * 	The item's name.
	 * @return
	 * 	The item's base price.
	 */
    public int getItemBasePrice(String name) {
        return items.getBasePrice(name);
    }

    /**
	 * Gets the name of the best bidder of an item.
	 * 
	 * @param itemName
	 * 	The item's name.
	 * @return
	 * 	The best bidder's name of an item.
	 */
    public String getItemBestBidder(String itemName) {
        return items.getBestBidder(itemName);
    }

    /**
	 * Gets the maximum bid of an item.
	 * 
	 * @param itemName
	 * 	The item's name.
	 * @return
	 * 	The item's maximum bid.
	 */
    public int getItemMaxBid(String itemName) {
        return items.getMaxBid(itemName);
    }

    /**
	 * Gets all the items' reports.
	 * @return
	 * 
	 * 	This action's items' reports.
	 */
    public String getItemReport() {
        return items.getReport();
    }

    /**
	 * Gets the seller's name of
	 * a specific item.
	 * 
	 * @param name
	 * 	The name of the item.
	 * @return
	 * 	The seller name.
	 */
    public String getItemSellerName(String name) {
        return items.getSellerName(name);
    }

    /**
	 * Gets the name of the auction.
	 * @return
	 * 	The auction's name.
	 */
    public String getName() {
        return name;
    }

    /**
	 * Gets the status of this auction.
	 * 
	 * @return
	 * 	Returns one of the following constants:
	 * 	<ul>
	 *  <li><code>Auction.STATUS_NEW</code></li>
	 *  <li><code>Auction.STATUS_OPEN</code></li>
	 *  <li><code>Auction.STATUS_CLOSED</code></li>
	 *  </ul>
	 */
    public int getStatus() {
        return status;
    }
}
