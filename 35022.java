class RoboSimples2 {

    private String nomeDoRobo;

    private int posicaoXAtual;

    private int posicaoYAtual;

    private char direcaoAtual;

    /**
	* Construtor para a classe RoboSimples2, que recebe argumentos para
	* inicializar todos o campos da classe.
	*/
    RoboSimples2(String nomeDoRobo, int posicaoXAtual, int posicaoYAtual, char direcaoAtual) {
        this.nomeDoRobo = nomeDoRobo;
        this.posicaoXAtual = posicaoXAtual;
        this.posicaoYAtual = posicaoYAtual;
        this.direcaoAtual = direcaoAtual;
    }

    /**
	* Construtor para a classe RoboSimples2,que recebe somente o nome do Rob� e
	* assume que este Rob� est� na posi��o (0,0) e dire��o Norte.
	*/
    RoboSimples2(String nomeDoRobo) {
        this(nomeDoRobo, 0, 0, 'N');
    }

    /**
	* Construtor para a classe RoboSimples2, que n�o recebe argumentos e assume que
	* o Rob� n�o tem nome(String vazia), e que este Rob� est� na posi��o (0,0) e dire��o Norte.
	*/
    RoboSimples2() {
        this("", 0, 0, 'N');
    }

    /**
	* m�todo move -modifica a posi��o do Rob� em uma unidade na dire��o em que o 
	* o Rob� est�. 
	*/
    public void move() {
        move(1);
    }

    /**
	* m�todo move -modifica a posi��o do Rob� em um n�mero de unidades na 
	* dire��o em que o rob� est�. 
	* @param passos - n�mero de passos para o rob�. 
	*/
    public void move(int passos) {
        if (direcaoAtual == 'N') posicaoYAtual = posicaoYAtual + passos;
        if (direcaoAtual == 'S') posicaoYAtual = posicaoYAtual - passos;
        if (direcaoAtual == 'E') posicaoXAtual = posicaoXAtual + passos;
        if (direcaoAtual == 'O') posicaoXAtual = posicaoXAtual - passos;
    }

    /**
	* m�todo mudaDirecao permite que a dire��o do rob� seja mudada depois de ele ter 
	* sido criado 
	* @param novaDirecao - nova dire��o para o rob�. 
	*/
    public void mudaDirecao(char novaDirecao) {
        direcaoAtual = novaDirecao;
    }

    public String toString() {
        String posicao = "Nome do robo:" + nomeDoRobo + "\n";
        posicao = posicao + "Posicao do robo: (" + posicaoXAtual + "," + posicaoYAtual + ")\n";
        posicao = posicao + "Direcao do robo:" + direcaoAtual;
        return posicao;
    }
}
