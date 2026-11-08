package co.edu.uniquindio.prueba;

import co.edu.uniquindio.ejercicios.Ejercicio4;
import co.edu.uniquindio.estructuras.ArbolBinarioBusqueda;

/**
 * Pruebas del ejercicio 4a (contarEquiposEnRango), sin JUnit.
 * Devuelve cuantas pruebas fallaron.
 */
public class PruebaEjercicio4 {

    private static int fallas = 0;

    public static int ejecutar() {
        fallas = 0;
        System.out.println("== Pruebas del ejercicio 4a ==");

        // Arbol del 4b (el segundo 60 no entra porque los codigos son unicos)
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        int[] datos = {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 43, 44};
        for (int d : datos) {
            arbol.insertar(d);
        }

        verificar("rango 20 a 47", 6, Ejercicio4.contarEquiposEnRango(arbol.getRaiz(), 20, 47));
        verificar("rango 1 a 10", 1, Ejercicio4.contarEquiposEnRango(arbol.getRaiz(), 1, 10));
        verificar("rango 55 a 59", 0, Ejercicio4.contarEquiposEnRango(arbol.getRaiz(), 55, 59));
        verificar("rango 0 a 100", 13, Ejercicio4.contarEquiposEnRango(arbol.getRaiz(), 0, 100));
        verificar("extremos incluidos 41 a 41", 1, Ejercicio4.contarEquiposEnRango(arbol.getRaiz(), 41, 41));
        verificar("inferior mayor que superior", 0, Ejercicio4.contarEquiposEnRango(arbol.getRaiz(), 50, 20));
        verificar("arbol vacio", 0,
                Ejercicio4.contarEquiposEnRango(new ArbolBinarioBusqueda().getRaiz(), 1, 10));

        System.out.println("Fallas: " + fallas);
        return fallas;
    }

    private static void verificar(String nombre, int esperado, int obtenido) {
        boolean bien = esperado == obtenido;
        if (!bien) {
            fallas++;
        }
        System.out.println((bien ? "  OK    " : "  FALLA ") + nombre
                + (bien ? "" : " -> esperado " + esperado + " obtenido " + obtenido));
    }

    public static void main(String[] args) {
        ejecutar();
    }
}