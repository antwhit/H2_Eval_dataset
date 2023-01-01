/**
   * @author chitiore
   *
   */
public class Arreglo_Parqueo<E> implements Interfaz<E> {

    private Object[] Elementos = new String[10];

    private int cantidadElementos;

    public Arreglo_Parqueo() {
        cantidadElementos = 0;
        for (int i = 0; i < 10; i++) Elementos[i] = "";
    }

    public boolean empty() {
        if (cantidadElementos == 0) return true; else return false;
    }

    public boolean full() {
        if (cantidadElementos == 10) return true; else return false;
    }

    public void push(Object element) throws OverflowUnderflowException {
        if (full()) throw new OverflowUnderflowException("Stack Overflow"); else {
            Elementos[cantidadElementos] = element;
            cantidadElementos++;
        }
    }

    public E pop() throws OverflowUnderflowException {
        if (empty()) throw new OverflowUnderflowException("Stack Underflow"); else {
            cantidadElementos--;
            return (E) Elementos[cantidadElementos];
        }
    }

    public E top() throws OverflowUnderflowException {
        if (empty()) throw new OverflowUnderflowException("Stack Underflow"); else {
            return (E) Elementos[cantidadElementos - 1];
        }
    }

    public E devolver(int pos) {
        if (pos >= cantidadElementos) return (E) ("Dispo" + Integer.toString(pos + 1)); else return (E) Elementos[pos];
    }
}
