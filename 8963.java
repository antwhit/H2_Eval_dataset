import org.gmod.chado.chadxtogame.ChadoSaxReader;
import org.gmod.chado.chadxtogame.GenFeat;

public class CSR {

    public static void main(String args[]) {
        ChadoSaxReader pd = new ChadoSaxReader();
        String fn = "/users/smutniak/pinglei/dump_scaffold_local_id.xml";
        pd.parse(fn);
        GenFeat topnode = pd.getTopNode();
        topnode.Display(0);
    }
}
