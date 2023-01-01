public class R1 {

    private short valor;

    private UC uc;

    private ULA ula;

    public R1(UC uc, ULA ula) {
        this.uc = uc;
        this.ula = ula;
    }

    public void atualizarValor() {
        String control = uc.getSinais("R1");
        if (Integer.parseInt(control) == 1) valor = ula.getValor();
    }

    public void setValor(short valor) {
        this.valor = valor;
    }

    public short getValor() {
        return valor;
    }
}
