package co.edu.uniquindio.ejercicios;

import co.edu.uniquindio.estructuras.Cola;
import co.edu.uniquindio.modelo.Paquete;
import co.edu.uniquindio.modelo.TipoEntrega;

/**
 * Ejercicio 3: seleccion de paquetes para una jornada.
 * Todo el recorrido se hace con recursividad (no hay for, while ni do-while).
 */
public final class Ejercicio3 {

    private Ejercicio3() { }

    /**
     * Selecciona paquetes para el vehiculo y los retira de la cola de pendientes.
     * Primero se revisan los URGENTE y despues los NORMAL, cada grupo en su orden de registro.
     * Si la capacidad es 0 o menos, devuelve una cola vacia y no toca los pendientes.
     */
    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();
        if (capacidad <= 0) {
            return seleccionados;
        }
        System.out.println("Paquetes evaluados (capacidad " + capacidad + " kg):");
        // Primera vuelta: solo se consideran los URGENTE
        int restante = procesar(pendientes, pendientes.tamanio(), TipoEntrega.URGENTE,
                capacidad, seleccionados);
        // Segunda vuelta: solo se consideran los NORMAL (el tamanio ya cambio, se vuelve a pedir)
        restante = procesar(pendientes, pendientes.tamanio(), TipoEntrega.NORMAL,
                restante, seleccionados);
        System.out.println("Capacidad que quedo disponible: " + restante + " kg");
        return seleccionados;
    }

    /**
     * Da una vuelta completa a la cola de pendientes, revisando los paquetes del tipo indicado.
     * Devuelve la capacidad que queda al terminar la vuelta.
     *
     * @param cantidad cuantos paquetes faltan por sacar en esta vuelta
     */
    private static int procesar(Cola<Paquete> pendientes, int cantidad, TipoEntrega tipo,
                                int capacidad, Cola<Paquete> seleccionados) {
        // CASO BASE: ya se saco la cantidad de paquetes de esta vuelta; se devuelve lo que queda
        if (cantidad == 0) {
            return capacidad;
        }
        // CASO RECURSIVO: se saca un paquete, se decide que hacer con el y se sigue con cantidad - 1
        Paquete paquete = pendientes.desencolar();
        if (paquete.getTipo() != tipo) {
            // No es del tipo que se revisa en esta vuelta: vuelve al final, sin evaluarse
            pendientes.encolar(paquete);
        } else if (paquete.getPeso() <= capacidad) {
            // Cabe: se selecciona y se descuenta su peso
            seleccionados.encolar(paquete);
            capacidad = capacidad - paquete.getPeso();
            System.out.println("  " + paquete + " -> SELECCIONADO, quedan " + capacidad + " kg");
        } else {
            // No cabe: queda pendiente (vuelve al final) y se sigue con el siguiente
            pendientes.encolar(paquete);
            System.out.println("  " + paquete + " -> PENDIENTE (no cabe, quedan " + capacidad + " kg)");
        }
        return procesar(pendientes, cantidad - 1, tipo, capacidad, seleccionados);
    }

    /** Arma la cola de la prueba del enunciado (P1 a P7, en orden de llegada). */
    public static Cola<Paquete> crearColaPrueba() {
        Cola<Paquete> cola = new Cola<>();
        cola.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        cola.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        cola.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        cola.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        cola.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        cola.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        cola.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));
        return cola;
    }

    /** Imprime todo lo que pide el enunciado en "Muestre". Lo llama el Main. */
    public static void mostrarPrueba() {
        Cola<Paquete> pendientes = crearColaPrueba();
        System.out.println("Cola inicial (frente -> final): " + pendientes);
        Cola<Paquete> seleccionados = seleccionarPaquetes(pendientes, 14);
        System.out.println("Cola retornada: " + seleccionados);
        System.out.println("Cola de pendientes: " + pendientes);
    }
}