import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import com.meufinanceiro.business.exception.BusinessException;
import com.meufinanceiro.business.search.GrupoMovimentSearch;
import com.meufinanceiro.entity.GrupoMoviment;

@Name("actionTeste")
public class ActionTeste {

    @In(create = true)
    GrupoMovimentSearch grupoMovimentSearch;

    private List<GrupoMoviment> abas;

    public List<GrupoMoviment> getAbas() {
        return abas;
    }

    public void setAbas(List<GrupoMoviment> abas) {
        this.abas = abas;
    }

    @Create
    public void onCreate() {
        try {
            abas = new ArrayList<GrupoMoviment>();
            for (GrupoMoviment grupoMoviment : grupoMovimentSearch.findAll()) {
                abas.add(grupoMoviment);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
