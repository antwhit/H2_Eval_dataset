import java.util.Iterator;
import java.util.LinkedList;
import javax.mail.Message;
import objetos.CategoriaObj;
import objetos.ClienteObj;
import objetos.ItemVendaObj;
import objetos.MarcaObj;
import objetos.ProdutoObj;
import objetos.VendaObj;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.util.Loader;
import classes.CategoriaImpl;
import stubs.CarrinhoCompraStub;
import stubs.CategoriaStub;
import stubs.ClienteStub;
import stubs.MarcaStub;
import stubs.ProdutoStub;
import stubs.VendaStub;
import de.hunsicker.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) {
        try {
            ProdutoStub ps = new ProdutoStub();
            ClienteStub cs = new ClienteStub();
            CarrinhoCompraStub ccs = new CarrinhoCompraStub();
            CategoriaStub cats = new CategoriaStub();
            MarcaStub ms = new MarcaStub();
            VendaStub vs = new VendaStub();
            ProdutoObj dummyProduto = new ProdutoObj();
            ClienteObj dummyCliente = new ClienteObj();
            ItemVendaObj dummyItemVenda = new ItemVendaObj();
            CategoriaObj dummyCategoria = new CategoriaObj();
            MarcaObj dummyMarca = new MarcaObj();
            VendaObj dummyVenda = new VendaObj();
            dummyProduto.setId(2);
            dummyProduto.setAltura(60);
            dummyProduto.setAtivo(false);
            dummyProduto.setDescricao("produto pirata do paragua");
            dummyProduto.setLargura(80);
            dummyProduto.setMarca_id(1);
            dummyProduto.setModelo("Azia Red");
            dummyProduto.setNome("Produto de Teste Atualizado2");
            dummyProduto.setPeso(140);
            dummyProduto.setPreco(35.90f);
            dummyProduto.setProfundidade(10);
            System.out.println(ps.atualizarProduto(dummyProduto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
