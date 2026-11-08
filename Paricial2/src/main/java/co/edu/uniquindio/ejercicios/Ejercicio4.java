package co.edu.uniquindio.ejercicios;

import co.edu.uniquindio.estructuras.ArbolBinarioBusqueda;
import co.edu.uniquindio.estructuras.NodoArbol;

/**
 * Ejercicio 4a: consulta de equipos por rango en un arbol binario de busqueda.
 */
public class Ejercicio4 {

    /**
     * Cuenta cuantos equipos tienen un codigo entre inferior y superior (ambos incluidos).
     * Aprovecha el orden del ABB para NO recorrer los subarboles que no pueden aportar.
     *
     * CASO BASE: el nodo es null o el limite inferior supera al superior -> 0.
     * CASO RECURSIVO:
     *   - codigo < inferior: este nodo y todo su lado izquierdo son menores -> solo busca a la derecha.
     *   - codigo > superior: este nodo y todo su lado derecho son mayores -> solo busca a la izquierda.
     *   - codigo dentro del rango: cuenta 1 y busca a los dos lados.
     */
    public static int contarEquiposEnRango(NodoArbol nodo, int inferior, int superior) {
        if (nodo == null || inferior > superior) {                  // CASO BASE
            return 0;
        }
        if (nodo.getCodigo() < inferior) {                          // CASO RECURSIVO: poda la izquierda
            return contarEquiposEnRango(nodo.getDerecho(), inferior, superior);
        }
        if (nodo.getCodigo() > superior) {                          // CASO RECURSIVO: poda la derecha
            return contarEquiposEnRango(nodo.getIzquierdo(), inferior, superior);
        }
        // Esta dentro del rango: cuenta este nodo y mira los dos lados.
        return 1
                + contarEquiposEnRango(nodo.getIzquierdo(), inferior, superior)
                + contarEquiposEnRango(nodo.getDerecho(), inferior, superior);
    }

    /** Arma el arbol del ejercicio 4b, imprime los recorridos y varios conteos. */
    public static void mostrarPrueba() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        int[] datos = {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 60, 43, 44};
        for (int codigo : datos) {
            boolean inserto = arbol.insertar(codigo);
            if (!inserto) {
                System.out.println("El codigo " + codigo + " ya existe: no se inserta.");
            }
        }
        System.out.println("Inorden:   " + arbol.inorden());
        System.out.println("Preorden:  " + arbol.preorden());
        System.out.println("Postorden: " + arbol.postorden());
        System.out.println("Amplitud:  " + arbol.amplitud());
        System.out.println();
        imprimirConteo(arbol, 20, 47);
        imprimirConteo(arbol, 1, 10);
        imprimirConteo(arbol, 55, 59);
        imprimirConteo(arbol, 0, 100);
        imprimirConteo(arbol, 50, 20);
        System.out.println("Arbol vacio, rango 1 a 10: "
                + contarEquiposEnRango(new ArbolBinarioBusqueda().getRaiz(), 1, 10));
    }

    private static void imprimirConteo(ArbolBinarioBusqueda arbol, int inferior, int superior) {
        System.out.println("Equipos con codigo entre " + inferior + " y " + superior + ": "
                + contarEquiposEnRango(arbol.getRaiz(), inferior, superior));
    }
}