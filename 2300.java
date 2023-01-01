import java.awt.Color;

/**
 * A falat reprezentáló oszály.
 * 
 */
public class Wall implements IAffectObject {

    /**
         * A fal X poziciója
         */
    private int x;

    /**
         * A fal Y poziciója
         */
    private int y;

    /**
         * Új fal x-y koordináták alapján
        *  @param x X koordináta
        *  @param y Y koordináta
        */
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
         * Új fal FieldElement alapján
         * @param fe A fal pályaeleme
        *  @param x X koordináta
        *  @param y Y koordináta
        */
    public Wall(int x, int y, FieldElement fe) {
        fe.setAffectObject(this);
        this.x = x;
        this.y = y;
    }

    /**
         * Hatás kiváltása az ütköző obektumon.
         */
    public void affect(IAffectable affectable) {
        affectable.runIntoWall();
    }

    /**
         * X koordináta lekérdezése
         * @return X koordináta
        */
    public int getX() {
        return this.x;
    }

    /**
         * Y koordináta lekérdezése
         * @return Y koordináta
        */
    public int getY() {
        return this.y;
    }
}
