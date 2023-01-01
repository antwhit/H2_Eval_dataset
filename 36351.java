/**
 * 
 * Vers�o Giovani 10-Jun-09
 * FABIANO TUBIANA
 */
public class Filme {

    public Filme(int codigo, String nome) {
        setCodigo(codigo);
        setNome(nome);
    }

    private int codigo;

    /**
 * Getter of the property <tt>codigo</tt>
 *
 * @return Returns the codigo.
 * 
 */
    public int getCodigo() {
        return codigo;
    }

    /**
 * Setter of the property <tt>codigo</tt>
 *
 * @param codigo The codigo to set.
 *
 */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    private String nome;

    /**
 * Getter of the property <tt>nome</tt>
 *
 * @return Returns the nome.
 * 
 */
    public String getNome() {
        return nome;
    }

    /**
 * Setter of the property <tt>nome</tt>
 *
 * @param nome The nome to set.
 *
 */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
