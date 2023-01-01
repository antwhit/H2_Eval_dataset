public class Atributo {

    public static final int TIPO_STRING = 1;

    public static final int TIPO_INT = 2;

    public static final int TIPO_CHAR = 3;

    public static final int TIPO_DOUBLE = 4;

    public static final int TIPO_DATA = 5;

    private String nome;

    private int tipo;

    private boolean chavePrimaria;

    private boolean unique;

    private boolean notNull;

    public static int stringToTipo(String str) {
        if (str.equals("String")) return 1; else if (str.equals("Integer")) return 2; else if (str.equals("Char")) return 3; else if (str.equals("Double")) return 4; else if (str.equals("Date")) return 5; else return -1;
    }

    public Atributo(String nome, int tipo, boolean isChavePrimaria, boolean isUnique, boolean isNotNull) {
        this.nome = nome;
        this.tipo = tipo;
        this.chavePrimaria = isChavePrimaria;
        this.unique = isUnique;
        this.notNull = isNotNull;
    }

    public String getNome() {
        return nome;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean isChavePrimaria() {
        return chavePrimaria;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setChavePrimaria(boolean chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}
