public class Building implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public String image;

    public int x, y;

    public int width, height;

    public Building(String nimage, int nwidth, int nheight) {
        image = nimage;
        width = nwidth;
        height = nheight;
    }
}
