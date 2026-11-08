package co.edu.uniquindio.estructuras;

/**
 * Un nodo del arbol binario de busqueda.
 * Guarda el codigo entero del equipo y los enlaces a sus dos hijos.
 */
public class NodoArbol {

    private final int codigo;          // la clave del nodo (codigo del equipo)
    private NodoArbol izquierdo;       // hijo con codigos MENORES
    private NodoArbol derecho;         // hijo con codigos MAYORES

    /** Crea un nodo hoja: sin hijos. */
    public NodoArbol(int codigo) {
        this.codigo = codigo;
        this.izquierdo = null;
        this.derecho = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }
}