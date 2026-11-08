package co.edu.uniquindio.prueba;

import co.edu.uniquindio.ejercicios.Ejercicio2;
import co.edu.uniquindio.estructuras.Pila;
import co.edu.uniquindio.util.Prueba;

/** Pruebas del ejercicio 2 (obtenerSegmento). */
public final class PruebaEjercicio2 {

    private PruebaEjercicio2() { }

    /** Arma la pila del ejemplo: A6 en la cima y A1 en el fondo. */
    private static Pila<String> historial() {
        Pila<String> pila = new Pila<>();
        pila.apilar("A1");
        pila.apilar("A2");
        pila.apilar("A3");
        pila.apilar("A4");
        pila.apilar("A5");
        pila.apilar("A6");
        return pila;
    }

    public static void ejecutar() {
        Prueba.titulo("Ejercicio 2 - obtenerSegmento");
        String original = "[A6 -> A5 -> A4 -> A3 -> A2 -> A1]";

        // Caso del ejemplo: inicio 2 y fin 4
        Pila<String> pila = historial();
        Pila<String> resultado = Ejercicio2.obtenerSegmento(pila, 2, 4);
        Prueba.verificar(resultado.toString().equals("[A5 -> A4 -> A3]"), "Inicio 2 y fin 4 da A5, A4, A3");
        Prueba.verificar(pila.toString().equals(original), "La original queda igual");

        // inicio y fin iguales
        resultado = Ejercicio2.obtenerSegmento(pila, 3, 3);
        Prueba.verificar(resultado.toString().equals("[A4]"), "Inicio 3 y fin 3 da solo A4");

        // Todo el historial
        resultado = Ejercicio2.obtenerSegmento(pila, 1, 6);
        Prueba.verificar(resultado.toString().equals(original), "Inicio 1 y fin 6 da toda la pila");

        // Posiciones que no sirven: pila vacia
        Prueba.verificar(Ejercicio2.obtenerSegmento(pila, 0, 3).estaVacia(), "Inicio 0 da pila vacia");
        Prueba.verificar(Ejercicio2.obtenerSegmento(pila, 5, 2).estaVacia(), "Inicio mayor que fin da pila vacia");
        Prueba.verificar(Ejercicio2.obtenerSegmento(pila, 2, 7).estaVacia(), "Fin 7 (mayor que el tamanio) da pila vacia");
        Prueba.verificar(pila.toString().equals(original), "La original sigue igual despues de los casos malos");

        // Pila vacia
        Prueba.verificar(Ejercicio2.obtenerSegmento(new Pila<>(), 1, 1).estaVacia(), "Pila vacia da pila vacia");
    }

    public static void main(String[] args) {
        ejecutar();
        Prueba.resumen();
    }
}
