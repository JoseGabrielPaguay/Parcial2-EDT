package co.edu.uniquindio.prueba;

import co.edu.uniquindio.ejercicios.Ejercicio1;
import co.edu.uniquindio.estructuras.Pila;
import co.edu.uniquindio.modelo.Caja;
import co.edu.uniquindio.util.Prueba;

/** Pruebas del ejercicio 1 (retirarCaja). */
public final class PruebaEjercicio1 {

    private PruebaEjercicio1() { }

    /** Arma la pila del enunciado: se apilan de C1 a C7. */
    private static Pila<Caja> pilaDelEnunciado() {
        Pila<Caja> pila = new Pila<>();
        pila.apilar(new Caja("C1", 2));
        pila.apilar(new Caja("C2", 4));
        pila.apilar(new Caja("C3", 6));
        pila.apilar(new Caja("C4", 8));
        pila.apilar(new Caja("C5", 3));
        pila.apilar(new Caja("C6", 9));
        pila.apilar(new Caja("C7", 5));
        return pila;
    }

    public static void ejecutar() {
        Prueba.titulo("Ejercicio 1 - retirarCaja");

        // Las 3 llamadas del enunciado, una detras de otra sobre la misma pila
        Pila<Caja> pila = pilaDelEnunciado();
        Prueba.verificar(pila.toString().equals("[C7:5 -> C6:9 -> C5:3 -> C4:8 -> C3:6 -> C2:4 -> C1:2]"),
                "Pila inicial");

        Prueba.verificar(Ejercicio1.retirarCaja(pila, "C3"), "Retirar C3 da true");
        Prueba.verificar(pila.toString().equals("[C7:5 -> C5:3 -> C2:4 -> C6:9 -> C4:8 -> C1:2]"),
                "Pila despues de retirar C3 (valor par)");

        Prueba.verificar(Ejercicio1.retirarCaja(pila, "C4"), "Retirar C4 da true");
        Prueba.verificar(pila.toString().equals("[C7:5 -> C2:4 -> C1:2 -> C5:3 -> C6:9]"),
                "Pila despues de retirar C4 (valor par)");

        Prueba.verificar(Ejercicio1.retirarCaja(pila, "C7"), "Retirar C7 da true");
        Prueba.verificar(pila.toString().equals("[C1:2 -> C6:9 -> C2:4 -> C5:3]"),
                "Pila despues de retirar C7 (valor impar)");

        // Un codigo que no existe: false y la pila queda igual
        Pila<Caja> otra = pilaDelEnunciado();
        String antes = otra.toString();
        Prueba.verificar(!Ejercicio1.retirarCaja(otra, "C99"), "Codigo que no existe da false");
        Prueba.verificar(otra.toString().equals(antes), "La pila queda igual si no la encuentra");

        // Pila vacia: false
        Pila<Caja> vacia = new Pila<>();
        Prueba.verificar(!Ejercicio1.retirarCaja(vacia, "C1"), "Pila vacia da false");

        // Pila con una sola caja: queda vacia y da true
        Pila<Caja> una = new Pila<>();
        una.apilar(new Caja("C1", 2));
        Prueba.verificar(Ejercicio1.retirarCaja(una, "C1"), "Pila de una caja da true");
        Prueba.verificar(una.estaVacia(), "La pila de una caja queda vacia");
    }

    public static void main(String[] args) {
        ejecutar();
        Prueba.resumen();
    }
}
