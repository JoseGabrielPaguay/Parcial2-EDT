package co.edu.uniquindio.ejercicios;

import co.edu.uniquindio.estructuras.Pila;

/**
 * Ejercicio 2: sacar una parte del historial de acciones (un segmento de la pila).
 * Solo se usan las operaciones de la pila y pilas auxiliares.
 */
public class Ejercicio2 {

    /**
     * Devuelve una pila nueva con las acciones que estan entre las posiciones
     * inicio y fin (incluidas), contadas desde la cima empezando en 1.
     * La accion de la posicion inicio queda en la cima de la pila nueva.
     * La pila original queda igual, con los mismos elementos y en el mismo orden.
     * Si inicio < 1, inicio > fin o fin es mayor que el tamanio, devuelve una pila vacia.
     */
    public static Pila<String> obtenerSegmento(Pila<String> original, int inicio, int fin) {
        Pila<String> resultado = new Pila<>();

        if (inicio < 1 || inicio > fin || fin > original.tamanio()) {
            return resultado;
        }

        // 1. Pasar todo a una auxiliar. Queda al reves: el del fondo queda en la cima.
        Pila<String> auxiliar = new Pila<>();
        while (!original.estaVacia()) {
            auxiliar.apilar(original.desapilar());
        }

        // 2. Devolver todo a la original. Como en la auxiliar estan al reves,
        //    van saliendo desde la ultima posicion hasta la primera.
        //    Los que estan en el rango tambien se apilan en el resultado.
        //    El ultimo que se apila en el resultado es el de la posicion inicio,
        //    por eso queda en la cima.
        int posicion = auxiliar.tamanio();   // el primero que sale es el de la ultima posicion
        while (!auxiliar.estaVacia()) {
            String accion = auxiliar.desapilar();
            original.apilar(accion);
            if (posicion >= inicio && posicion <= fin) {
                resultado.apilar(accion);
            }
            posicion--;
        }

        return resultado;
    }

    /** Muestra la pila original, el segmento y otra vez la original para ver que no cambio. */
    public static void mostrarPrueba() {
        Pila<String> historial = new Pila<>();
        historial.apilar("A1");
        historial.apilar("A2");
        historial.apilar("A3");
        historial.apilar("A4");
        historial.apilar("A5");
        historial.apilar("A6");

        System.out.println("Original (del tope al fondo): " + historial);
        Pila<String> segmento = obtenerSegmento(historial, 2, 4);
        System.out.println("Resultado para inicio = 2 y fin = 4: " + segmento);
        System.out.println("Original despues de la funcion: " + historial);
    }
}
