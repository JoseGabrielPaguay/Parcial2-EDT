package co.edu.uniquindio.estructuras;

/**
 * Pila propia (LIFO): el ultimo que entra es el primero que sale.
 * Se hace con nodos enlazados. No usa ninguna coleccion de Java.
 * Solo se puede trabajar con el elemento de la cima.
 */
public class Pila<T> {

    /** Nodo interno: guarda un valor y apunta al que esta debajo. */
    private static class Nodo<T> {
        private final T valor;
        private Nodo<T> siguiente;

        private Nodo(T valor) {
            this.valor = valor;
        }
    }

    private Nodo<T> cima;  // el de arriba, el que sale primero
    private int tamanio;

    /** Pone el elemento en la cima. */
    public void apilar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se puede apilar null");
        }
        Nodo<T> nuevo = new Nodo<>(elemento);
        // El nuevo queda encima del que era la cima
        nuevo.siguiente = cima;
        cima = nuevo;
        tamanio++;
    }

    /** Saca y devuelve el elemento de la cima. */
    public T desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        T valor = cima.valor;
        // La nueva cima es el que estaba debajo
        cima = cima.siguiente;
        tamanio--;
        return valor;
    }

    /** Devuelve el elemento de la cima sin sacarlo. */
    public T cima() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        return cima.valor;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int tamanio() {
        return tamanio;
    }

    /** Texto del tope al fondo, por ejemplo "[C7 -> C6 -> C5]". Solo sirve para mostrar. */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("[");
        Nodo<T> actual = cima;
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
