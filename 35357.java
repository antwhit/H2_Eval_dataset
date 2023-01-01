public class Possession implements ColorPacket {

    private static final long serialVersionUID = 1L;

    public int playerID;

    public int itemID;

    public boolean pickup;

    public Possession(int nplayerID, boolean npickup, int nitemID) {
        playerID = nplayerID;
        itemID = nitemID;
        pickup = npickup;
    }

    public int getPacketType() {
        return PACK_POSSESSION;
    }
}

;
