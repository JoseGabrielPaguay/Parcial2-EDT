package co.edu.uniquindio.prueba;

import co.edu.uniquindio.estructuras.Cola;
import co.edu.uniquindio.modelo.Paquete;
import co.edu.uniquindio.modelo.TipoEntrega;
import co.edu.uniquindio.util.Prueba;

/** Pruebas de la Cola propia y de la clase Paquete (sin JUnit, con el ayudante Prueba). */
public final class PruebaCola {

    private PruebaCola() { }

    public static void ejecutar() {
        Prueba.titulo("Cola propia");

        // Encolar 1, 2, 3 y desencolar debe dar 1, 2, 3 (FIFO)
        Cola<Integer> cola = new Cola<>();
        Prueba.verificar(cola.estaVacia(), "Cola nueva esta vacia");
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        Prueba.verificar(cola.tamanio() == 3, "Tamanio 3 despues de encolar 3");
        Prueba.verificar(cola.toString().equals("[1 -> 2 -> 3]"), "toString del frente al final");
        Prueba.verificar(cola.desencolar() == 1, "Desencolar da 1");
        Prueba.verificar(cola.desencolar() == 2, "Desencolar da 2");
        Prueba.verificar(cola.desencolar() == 3, "Desencolar da 3");
        Prueba.verificar(cola.estaVacia() && cola.tamanio() == 0, "Queda vacia al final");

        // frente mira sin sacar
        cola.encolar(7);
        cola.encolar(8);
        Prueba.verificar(cola.frente() == 7, "Frente da 7");
        Prueba.verificar(cola.tamanio() == 2, "Frente no saca el elemento");

        // Se puede seguir usando despues de vaciarla
        cola.desencolar();
        cola.desencolar();
        cola.encolar(9);
        Prueba.verificar(cola.frente() == 9 && cola.tamanio() == 1, "Reutilizable despues de vaciarse");

        // Errores con la cola vacia y con null
        Cola<Integer> vacia = new Cola<>();
        Prueba.esperarExcepcion(IllegalStateException.class, vacia::desencolar,
                "Desencolar con la cola vacia lanza excepcion");
        Prueba.esperarExcepcion(IllegalStateException.class, vacia::frente,
                "Frente con la cola vacia lanza excepcion");
        Prueba.esperarExcepcion(IllegalArgumentException.class, () -> vacia.encolar(null),
                "Encolar null lanza excepcion");

        Prueba.titulo("Paquete");
        Paquete p = new Paquete("P2", TipoEntrega.URGENTE, 7);
        Prueba.verificar(p.getCodigo().equals("P2"), "Codigo del paquete");
        Prueba.verificar(p.getTipo() == TipoEntrega.URGENTE, "Tipo del paquete");
        Prueba.verificar(p.getPeso() == 7, "Peso del paquete");
        Prueba.verificar(p.toString().equals("P2 (URGENTE, 7 kg)"), "toString del paquete");
        Prueba.esperarExcepcion(IllegalArgumentException.class,
                () -> new Paquete("P9", TipoEntrega.NORMAL, 0), "Peso 0 lanza excepcion");
        Prueba.esperarExcepcion(IllegalArgumentException.class,
                () -> new Paquete("P9", TipoEntrega.NORMAL, -3), "Peso negativo lanza excepcion");
        Prueba.esperarExcepcion(IllegalArgumentException.class,
                () -> new Paquete("", TipoEntrega.NORMAL, 3), "Codigo vacio lanza excepcion");
        Prueba.esperarExcepcion(IllegalArgumentException.class,
                () -> new Paquete("P9", null, 3), "Tipo null lanza excepcion");
    }

    public static void main(String[] args) {
        ejecutar();
        Prueba.resumen();
    }
}