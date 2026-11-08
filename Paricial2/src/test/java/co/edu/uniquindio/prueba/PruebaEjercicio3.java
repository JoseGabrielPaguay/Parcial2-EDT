package co.edu.uniquindio.prueba;

import co.edu.uniquindio.ejercicios.Ejercicio3;
import co.edu.uniquindio.estructuras.Cola;
import co.edu.uniquindio.modelo.Paquete;
import co.edu.uniquindio.modelo.TipoEntrega;
import co.edu.uniquindio.util.Prueba;

/** Pruebas del ejercicio 3 (seleccionarPaquetes). */
public final class PruebaEjercicio3 {

    private PruebaEjercicio3() { }

    public static void ejecutar() {
        Prueba.titulo("Ejercicio 3 - seleccionarPaquetes");

        // Caso del enunciado: capacidad 14
        Cola<Paquete> pendientes = Ejercicio3.crearColaPrueba();
        Cola<Paquete> seleccionados = Ejercicio3.seleccionarPaquetes(pendientes, 14);
        Prueba.verificar(seleccionados.toString()
                        .equals("[P2 (URGENTE, 7 kg) -> P4 (URGENTE, 5 kg) -> P7 (NORMAL, 2 kg)]"),
                "Retorna P2, P4, P7");
        Prueba.verificar(pendientes.toString()
                        .equals("[P1 (NORMAL, 4 kg) -> P3 (NORMAL, 3 kg) -> P5 (NORMAL, 6 kg) -> P6 (URGENTE, 4 kg)]"),
                "Pendientes quedan P1, P3, P5, P6 en su orden");

        // Capacidad 0: cola vacia y pendientes sin cambios
        Cola<Paquete> p0 = Ejercicio3.crearColaPrueba();
        String antes0 = p0.toString();
        Cola<Paquete> r0 = Ejercicio3.seleccionarPaquetes(p0, 0);
        Prueba.verificar(r0.estaVacia(), "Capacidad 0 devuelve cola vacia");
        Prueba.verificar(p0.toString().equals(antes0), "Capacidad 0 no cambia los pendientes");

        // Capacidad negativa
        Cola<Paquete> pn = Ejercicio3.crearColaPrueba();
        String antesN = pn.toString();
        Cola<Paquete> rn = Ejercicio3.seleccionarPaquetes(pn, -5);
        Prueba.verificar(rn.estaVacia(), "Capacidad negativa devuelve cola vacia");
        Prueba.verificar(pn.toString().equals(antesN), "Capacidad negativa no cambia los pendientes");

        // Cola vacia
        Cola<Paquete> vacia = new Cola<>();
        Cola<Paquete> rv = Ejercicio3.seleccionarPaquetes(vacia, 10);
        Prueba.verificar(rv.estaVacia() && vacia.estaVacia(), "Cola vacia devuelve cola vacia");

        // Todo cabe: se seleccionan todos, primero los urgentes
        Cola<Paquete> todos = Ejercicio3.crearColaPrueba();
        Cola<Paquete> rt = Ejercicio3.seleccionarPaquetes(todos, 100);
        Prueba.verificar(rt.toString().equals("[P2 (URGENTE, 7 kg) -> P4 (URGENTE, 5 kg) -> P6 (URGENTE, 4 kg)"
                        + " -> P1 (NORMAL, 4 kg) -> P3 (NORMAL, 3 kg) -> P5 (NORMAL, 6 kg) -> P7 (NORMAL, 2 kg)]"),
                "Si todo cabe, urgentes primero y luego normales");
        Prueba.verificar(todos.estaVacia(), "Si todo cabe, no quedan pendientes");

        // Ningun paquete cabe: todo queda pendiente en el mismo orden
        Cola<Paquete> nada = Ejercicio3.crearColaPrueba();
        String antesNada = nada.toString();
        Cola<Paquete> rnada = Ejercicio3.seleccionarPaquetes(nada, 1);
        Prueba.verificar(rnada.estaVacia(), "Si nada cabe, se retorna cola vacia");
        Prueba.verificar(nada.toString().equals(antesNada), "Si nada cabe, los pendientes no cambian de orden");
    }

    public static void main(String[] args) {
        ejecutar();
        Prueba.resumen();
    }
}