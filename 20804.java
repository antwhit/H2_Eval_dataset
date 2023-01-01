/**
 *
 * Implementação de uma Fila circular genérica
 */
public class Fila<Tipo> {

    private Tipo[] elementos;

    private int n_elementos;

    private int primeiro, ultimo;

    Fila(int tamanho) {
        elementos = (Tipo[]) new Object[tamanho];
        n_elementos = primeiro = 0;
        ultimo = -1;
    }

    public synchronized void insere(Tipo el) throws InterruptedException {
        if (n_elementos == elementos.length) this.wait();
        ultimo++;
        if (ultimo == elementos.length) ultimo = 0;
        elementos[ultimo] = el;
        n_elementos++;
        this.notify();
    }

    public synchronized Tipo tira() throws InterruptedException {
        if (n_elementos <= 0) this.wait();
        Tipo el = elementos[primeiro];
        n_elementos--;
        primeiro++;
        if (primeiro == elementos.length) primeiro = 0;
        this.notify();
        return el;
    }
}
