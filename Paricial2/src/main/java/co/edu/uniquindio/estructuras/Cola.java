package co.edu.uniquindio.estructuras;

/**
 * Cola propia (FIFO): el primero que entra es el primero que sale.
 * Se hace con nodos enlazados. No usa ninguna coleccion de Java.
 */
public class Cola<T> {

    /** Nodo interno: guarda un valor y apunta al siguiente. */
    private static class Nodo<T> {
        private final T valor;
        private Nodo<T> siguiente;

        private Nodo(T valor) {
            this.valor = valor;
        }
    }

    private Nodo<T> frente; // el que sale primero
    private Nodo<T> fin;    // el ultimo que entro
    private int tamanio;

    /** Pone el elemento al final de la cola. */
    public void encolar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se puede encolar null");
        }
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) {
            // Si estaba vacia, el nuevo es a la vez frente y fin
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        tamanio++;
    }

    /** Saca y devuelve el elemento del frente. */
    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola esta vacia");
        }
        T valor = frente.valor;
        frente = frente.siguiente;
        tamanio--;
        if (frente == null) {
            // Si ya no quedan elementos, tambien se limpia el fin
            fin = null;
        }
        return valor;
    }

    /** Devuelve el elemento del frente sin sacarlo. */
    public T frente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola esta vacia");
        }
        return frente.valor;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int tamanio() {
        return tamanio;
    }

    /** Texto del frente al final, por ejemplo "[P1 -> P2 -> P3]". Solo sirve para mostrar. */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("[");
        Nodo<T> actual = frente;
        while (actual != null) {
            texto.append(actual.valor);
            if (actual.siguiente != null) {
                texto.append(" -> ");
            }
            actual = actual.siguiente;
        }
        return texto.append("]").toString();
    }
}