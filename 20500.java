import java.io.FileOutputStream;
import java.io.PrintWriter;
import junit.framework.TestCase;

public class FirmaTest extends TestCase {

    Firma firma;

    protected void setUp() throws Exception {
        super.setUp();
        firma = new Firma("cevik dvd kiralama");
    }

    public void testMusteriEkle() {
        int testMusteriID, testMusteriID2;
        testMusteriID = firma.musteriEkle("ad", "adres", "tel");
        assertEquals(firma.musteriSayisi(), 1);
        testMusteriID2 = firma.musteriEkle("ad1", "adres1", "tel1");
        assertEquals(firma.musteriSayisi(), 2);
        assertEquals(firma.isimGetir(testMusteriID), "ad");
        assertEquals(firma.isimGetir(testMusteriID2), "ad1");
    }

    public void testFilmEkle() {
        int testFilmID, testFilmID2;
        testFilmID = firma.filmEkle("cevik matrix", "aksiyon", 2010);
        assertEquals(firma.filmSayisi(), 1);
        testFilmID2 = firma.filmEkle("cevik star wars", "daha aksiyon", 1970);
        assertEquals(firma.filmSayisi(), 2);
    }

    public void testFilmAra() {
        int testFilmID, testFilmID2;
        testFilmID = firma.filmEkle("cevik matrix", "aksiyon", 2010);
        testFilmID2 = firma.filmEkle("cevik star wars", "daha aksiyon", 1970);
        assertEquals(firma.filmIDGetir("cevik matrix"), testFilmID);
        assertEquals(firma.filmIDGetir("cevik star wars"), testFilmID2);
        assertEquals(firma.filmIDGetir("cevik recep"), -1);
    }

    public void testKirayaVerme() {
        int musteriID, musteri2ID, musteri3ID, olmayanMusteriID = 5;
        musteriID = firma.musteriEkle("isim", "adres", "tel");
        musteri2ID = firma.musteriEkle("ad", "evadres", "telefon");
        musteri3ID = firma.musteriEkle("adsoyad", "isadres", "ceptel");
        int filmID, film2ID, film3ID, film4ID, film5ID, olmayanFilmID = 13;
        filmID = firma.filmEkle("cevik matrix", "aksiyon", 2010);
        film2ID = firma.filmEkle("cevik lotr", "fantastik", 2002);
        film3ID = firma.filmEkle("cevik sar� zeybek", "belgesel", 1902);
        film4ID = firma.filmEkle("cevik liar liar", "fantastik", 2002);
        film5ID = firma.filmEkle("cevik batman", "Macera", 2010);
        assertEquals(0, firma.kiralamaSayisi());
        firma.kirala(musteriID, filmID);
        assertEquals(1, firma.kiralamaSayisi());
        assertEquals(2, firma.kirala(musteri2ID, film2ID));
        assertEquals("cevik matrix", firma.kiralamaBul(1).getFilm().getAd());
        assertEquals(-1, firma.kirala(olmayanMusteriID, filmID));
        assertEquals(-1, firma.kirala(musteri3ID, olmayanFilmID));
        assertEquals(-2, firma.kirala(musteri2ID, filmID));
        assertEquals(3, firma.kirala(musteri2ID, film3ID));
        assertEquals(4, firma.kirala(musteri2ID, film4ID));
        assertEquals(-3, firma.kirala(musteri2ID, film5ID));
    }

    public void testKiraBitirme() {
        int musteriID;
        musteriID = firma.musteriEkle("isim", "adres", "tel");
        int filmID;
        filmID = firma.filmEkle("cevik matrix", "aksiyon", 2010);
        int kiraID = firma.kirala(musteriID, filmID);
        assertEquals(2.0, firma.kiralamaBitir(kiraID));
        assertEquals(-1.0, firma.kiralamaBitir(42));
    }

    public void testSubeEkleme() {
        MockLoader loader = new MockLoader();
        loader.addExpectedLine("Istanbul,Kadikoy Subesi");
        loader.addExpectedLine("Izmir,Konak Subesi");
        loader.addExpectedLine("Burdur,Cevik Subesi");
        firma.setSubelerLoader(loader);
        Sube sube1 = new Sube("Istanbul", "Kadiky Subesi");
        Sube sube2 = new Sube("Konak Subesi");
        Sube sube3 = new Sube("Burdur", "Cevik Subesi");
        firma.subeEkle(sube1);
        firma.subeEkle(sube2);
        firma.subeEkle(sube3);
        loader.verify();
    }
}
