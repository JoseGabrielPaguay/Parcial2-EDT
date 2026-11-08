package co.edu.uniquindio.prueba;

import co.edu.uniquindio.estructuras.ArbolBinarioBusqueda;

/**
 * Pruebas del arbol binario de busqueda (sin JUnit).
 * Si ya tienen el ayudante Prueba del parcial 1, pueden cambiar el metodo verificar
 * por las llamadas de ese ayudante. Devuelve cuantas pruebas fallaron.
 */
public class PruebaArbol {

    private static int fallas = 0;

    public static int ejecutar() {
        fallas = 0;
        System.out.println("== Pruebas del arbol binario de busqueda ==");

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        verificar("arbol nuevo esta vacio", true, arbol.estaVacio());
        verificar("recorrido de arbol vacio", "", arbol.inorden());
        verificar("amplitud de arbol vacio", "", arbol.amplitud());

        int[] datos = {50, 30, 70, 20, 40};
        for (int d : datos) {
            arbol.insertar(d);
        }
        verificar("arbol con datos no esta vacio", false, arbol.estaVacio());
        verificar("inorden", "20, 30, 40, 50, 70", arbol.inorden());
        verificar("preorden", "50, 30, 20, 40, 70", arbol.preorden());
        verificar("postorden", "20, 40, 30, 70, 50", arbol.postorden());
        verificar("amplitud", "50, 30, 70, 20, 40", arbol.amplitud());
        verificar("insertar repetido da false", false, arbol.insertar(40));
        verificar("insertar nuevo da true", true, arbol.insertar(60));
        verificar("el repetido no cambia el arbol", "20, 30, 40, 50, 60, 70", arbol.inorden());
        verificar("buscar existente", true, arbol.buscar(60));
        verificar("buscar inexistente", false, arbol.buscar(99));
        verificar("raiz", 50, arbol.getRaiz().getCodigo());

        System.out.println("Fallas: " + fallas);
        return fallas;
    }

    private static void verificar(String nombre, Object esperado, Object obtenido) {
        boolean bien = esperado.equals(obtenido);
        if (!bien) {
            fallas++;
        }
        System.out.println((bien ? "  OK    " : "  FALLA ") + nombre
                + (bien ? "" : " -> esperado [" + esperado + "] obtenido [" + obtenido + "]"));
    }

    public static void main(String[] args) {
        ejecutar();
    }
}