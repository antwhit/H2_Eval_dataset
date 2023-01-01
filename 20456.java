class TesteSimuladorDeCaixaDeBanco2 {

    public static int numeroDoCliente;

    private int numeroDoCaixa;

    TesteSimuladorDeCaixaDeBanco2(int n) {
        numeroDoCliente = 0;
        numeroDoCaixa = n;
        System.out.println("Caixa " + numeroDoCaixa + " iniciou operacao");
    }

    public void proximoAtendimento() {
        numeroDoCliente = numeroDoCliente + 1;
        System.out.print("Cliente c/senha numero " + numeroDoCliente + " favor");
        System.out.println(" dirigir-se ao caixa numero: " + numeroDoCaixa);
    }
}
