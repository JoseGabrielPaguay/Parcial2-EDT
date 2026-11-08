package co.edu.uniquindio.prueba;

import co.edu.uniquindio.estructuras.Pila;
import co.edu.uniquindio.modelo.Caja;
import co.edu.uniquindio.util.Prueba;

/** Pruebas de la Pila propia y de la clase Caja (sin JUnit, con el ayudante Prueba). */
public final class PruebaPila {

    private PruebaPila() { }

    public static void ejecutar() {
        Prueba.titulo("Pila propia");

        // Apilar 1, 2, 3 y desapilar debe dar 3, 2, 1 (LIFO)
        Pila<Integer> pila = new Pila<>();
        Prueba.verificar(pila.estaVacia(), "Pila nueva esta vacia");
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        Prueba.verificar(pila.tamanio() == 3, "Tamanio 3 despues de apilar 3");
        Prueba.verificar(pila.toString().equals("[3 -> 2 -> 1]"), "toString del tope al fondo");
        Prueba.verificar(pila.desapilar() == 3, "Desapilar da 3");
        Prueba.verificar(pila.desapilar() == 2, "Desapilar da 2");
        Prueba.verificar(pila.desapilar() == 1, "Desapilar da 1");
        Prueba.verificar(pila.estaVacia() && pila.tamanio() == 0, "Queda vacia al final");

        // cima mira sin sacar
        pila.apilar(7);
        pila.apilar(8);
        Prueba.verificar(pila.cima() == 8, "Cima da 8");
        Prueba.verificar(pila.tamanio() == 2, "Cima no saca el elemento");

        // Errores con la pila vacia y con null
        Pila<Integer> vacia = new Pila<>();
        Prueba.esperarExcepcion(IllegalStateException.class, vacia::desapilar,
                "Desapilar con la pila vacia lanza excepcion");
        Prueba.esperarExcepcion(IllegalStateException.class, vacia::cima,
                "Cima con la pila vacia lanza excepcion");
        Prueba.esperarExcepcion(IllegalArgumentException.class, () -> vacia.apilar(null),
                "Apilar null lanza excepcion");

        Prueba.titulo("Caja");
        Caja c = new Caja("C3", 6);
        Prueba.verificar(c.getCodigo().equals("C3"), "Codigo de la caja");
        Prueba.verificar(c.getValor() == 6, "Valor de la caja");
        Prueba.verificar(c.toString().equals("C3:6"), "toString de la caja");
        Prueba.esperarExcepcion(IllegalArgumentException.class,
                () -> new Caja("", 5), "Codigo vacio lanza excepcion");
    }

    public static void main(String[] args) {
        ejecutar();
        Prueba.resumen();
    }
}
