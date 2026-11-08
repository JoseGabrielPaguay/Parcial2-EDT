package co.edu.uniquindio.estructuras;

/**
 * Arbol binario de busqueda (ABB) propio.
 * Regla: los codigos menores van a la izquierda y los mayores a la derecha.
 * Los codigos son unicos: no se permiten repetidos.
 * Casi todo es recursivo: el metodo publico llama a uno privado que recibe el nodo.
 */
public class ArbolBinarioBusqueda {

    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // ------------------------------------------------------------------
    // INSERTAR
    // ------------------------------------------------------------------

    /**
     * Inserta un codigo en el arbol.
     * Si el codigo ya existe NO lo inserta y devuelve false.
     * Si lo inserta devuelve true.
     */
    public boolean insertar(int codigo) {
        if (buscar(codigo)) {
            return false;                       // repetido: no se inserta
        }
        raiz = insertar(raiz, codigo);
        return true;
    }

    /**
     * Recursivo. Devuelve la raiz del subarbol ya con el codigo insertado.
     * CASO BASE: el nodo es null -> aqui va el nuevo nodo.
     * CASO RECURSIVO: menor -> sigue por la izquierda; mayor -> sigue por la derecha.
     */
    private NodoArbol insertar(NodoArbol nodo, int codigo) {
        if (nodo == null) {                                         // CASO BASE
            return new NodoArbol(codigo);
        }
        if (codigo < nodo.getCodigo()) {                            // CASO RECURSIVO
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), codigo));
        } else {
            nodo.setDerecho(insertar(nodo.getDerecho(), codigo));
        }
        return nodo;
    }

    // ------------------------------------------------------------------
    // BUSCAR
    // ------------------------------------------------------------------

    /** Dice si el codigo esta en el arbol. */
    public boolean buscar(int codigo) {
        return buscar(raiz, codigo);
    }

    /**
     * Recursivo.
     * CASO BASE 1: nodo null -> no esta.
     * CASO BASE 2: el codigo del nodo es el buscado -> esta.
     * CASO RECURSIVO: busca solo en el lado donde podria estar.
     */
    private boolean buscar(NodoArbol nodo, int codigo) {
        if (nodo == null) {                                         // CASO BASE 1
            return false;
        }
        if (codigo == nodo.getCodigo()) {                           // CASO BASE 2
            return true;
        }
        if (codigo < nodo.getCodigo()) {                            // CASO RECURSIVO
            return buscar(nodo.getIzquierdo(), codigo);
        }
        return buscar(nodo.getDerecho(), codigo);
    }

    // ------------------------------------------------------------------
    // RECORRIDOS (devuelven los codigos separados por coma)
    // ------------------------------------------------------------------

    /** Inorden: izquierdo, raiz, derecho. En un ABB sale ordenado de menor a mayor. */
    public String inorden() {
        return inorden(raiz);
    }

    private String inorden(NodoArbol nodo) {
        if (nodo == null) {                                         // CASO BASE
            return "";
        }
        String izq = inorden(nodo.getIzquierdo());                  // CASO RECURSIVO
        String der = inorden(nodo.getDerecho());
        return unir(unir(izq, String.valueOf(nodo.getCodigo())), der);
    }

    /** Preorden: raiz, izquierdo, derecho. */
    public String preorden() {
        return preorden(raiz);
    }

    private String preorden(NodoArbol nodo) {
        if (nodo == null) {                                         // CASO BASE
            return "";
        }
        String izq = preorden(nodo.getIzquierdo());                 // CASO RECURSIVO
        String der = preorden(nodo.getDerecho());
        return unir(unir(String.valueOf(nodo.getCodigo()), izq), der);
    }

    /** Postorden: izquierdo, derecho, raiz. */
    public String postorden() {
        return postorden(raiz);
    }

    private String postorden(NodoArbol nodo) {
        if (nodo == null) {                                         // CASO BASE
            return "";
        }
        String izq = postorden(nodo.getIzquierdo());                // CASO RECURSIVO
        String der = postorden(nodo.getDerecho());
        return unir(unir(izq, der), String.valueOf(nodo.getCodigo()));
    }

    /**
     * Amplitud (por niveles): primero la raiz, luego sus hijos, luego los nietos...
     * Usa una cola propia: se saca un nodo, se anota y se encolan sus hijos.
     */
    public String amplitud() {
        String resultado = "";
        if (raiz == null) {
            return resultado;
        }
        Cola<NodoArbol> cola = new Cola<>();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            NodoArbol actual = cola.desencolar();
            resultado = unir(resultado, String.valueOf(actual.getCodigo()));
            if (actual.getIzquierdo() != null) {
                cola.encolar(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                cola.encolar(actual.getDerecho());
            }
        }
        return resultado;
    }

    /** Une dos textos con ", " sin dejar comas sobrando cuando uno esta vacio. */
    private String unir(String a, String b) {
        if (a.isEmpty()) {
            return b;
        }
        if (b.isEmpty()) {
            return a;
        }
        return a + ", " + b;
    }
}