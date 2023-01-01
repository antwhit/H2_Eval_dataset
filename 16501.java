public class Revista extends Publicacao {

    public Revista(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public String exibirDetalhes() {
        return ("Revista: " + codigo + ", " + titulo);
    }
}
