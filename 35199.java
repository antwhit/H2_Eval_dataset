import sintatico.Sintatico;

public class Principal {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        StringBuffer cadeia = new StringBuffer("I(KKI)(IIKSI)/");
        Sintatico sintatico = new Sintatico(cadeia);
        try {
            if (sintatico.fazerAnalise()) {
                System.out.println("Arquivo C gerado com sucesso");
            } else {
                System.out.println("Erro na compilação");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
