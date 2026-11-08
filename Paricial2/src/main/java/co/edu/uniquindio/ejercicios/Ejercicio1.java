package co.edu.uniquindio.ejercicios;

import co.edu.uniquindio.estructuras.Pila;
import co.edu.uniquindio.modelo.Caja;

/**
 * Ejercicio 1: retirar una caja de la pila y reorganizar las que quedan.
 * Solo se usan las operaciones de la pila (apilar, desapilar, estaVacia)
 * y pilas auxiliares.
 */
public class Ejercicio1 {

    /**
     * Busca la caja con ese codigo, la saca y reorganiza las demas.
     * Las posiciones se cuentan desde la cima empezando en 1, ya sin la caja retirada.
     * - Si el valor de la caja retirada es par: arriba van las de posicion impar
     *   y debajo las de posicion par.
     * - Si es impar: arriba van las de posicion par y debajo las de posicion impar.
     * Retorna true si la encontro y la saco; false si la pila esta vacia o no la encontro.
     */
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila.estaVacia()) {
            return false;
        }

        // 1. Buscar la caja: se van pasando las de arriba a una pila auxiliar
        //    hasta encontrarla o hasta que la pila quede vacia.
        Pila<Caja> auxiliar = new Pila<>();
        Caja retirada = null;
        while (!pila.estaVacia() && retirada == null) {
            Caja actual = pila.desapilar();
            if (actual.getCodigo().equals(codigo)) {
                retirada = actual;          // esta es la que se saca, no se guarda
            } else {
                auxiliar.apilar(actual);
            }
        }

        // 2. Devolver a la pila las que se pasaron a la auxiliar.
        //    Al pasar de una pila a otra el orden se invierte, entonces al
        //    devolverlas quedan otra vez en su orden original.
        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }

        // Si no se encontro, la pila ya quedo igual que al principio
        if (retirada == null) {
            return false;
        }

        // 3. Separar las cajas que quedaron segun su posicion (impar o par),
        //    contando desde la cima y empezando en 1.
        Pila<Caja> impares = new Pila<>();
        Pila<Caja> pares = new Pila<>();
        int posicion = 1;
        while (!pila.estaVacia()) {
            Caja actual = pila.desapilar();
            if (posicion % 2 != 0) {
                impares.apilar(actual);
            } else {
                pares.apilar(actual);
            }
            posicion++;
        }
        // Ojo: en "impares" y "pares" las cajas quedaron al reves
        // (la ultima que entro esta en la cima).

        // 4. Armar la pila otra vez. Primero se apila el grupo que va abajo
        //    y despues el grupo que va arriba. Como cada grupo esta al reves,
        //    al pasarlo a la pila vuelve a quedar en su orden original.
        if (retirada.getValor() % 2 == 0) {
            // Valor par: abajo las de posicion par, arriba las de posicion impar
            pasarTodo(pares, pila);
            pasarTodo(impares, pila);
        } else {
            // Valor impar: abajo las de posicion impar, arriba las de posicion par
            pasarTodo(impares, pila);
            pasarTodo(pares, pila);
        }
        return true;
    }

    /** Pasa todos los elementos de una pila a otra (el orden se invierte). */
    private static void pasarTodo(Pila<Caja> origen, Pila<Caja> destino) {
        while (!origen.estaVacia()) {
            destino.apilar(origen.desapilar());
        }
    }

    /** Arma la pila del enunciado y muestra todo lo que pide la parte "Muestre". */
    public static void mostrarPrueba() {
        Pila<Caja> pila = new Pila<>();
        pila.apilar(new Caja("C1", 2));
        pila.apilar(new Caja("C2", 4));
        pila.apilar(new Caja("C3", 6));
        pila.apilar(new Caja("C4", 8));
        pila.apilar(new Caja("C5", 3));
        pila.apilar(new Caja("C6", 9));
        pila.apilar(new Caja("C7", 5));

        System.out.println("Pila inicial (del tope al fondo): " + pila);

        String[] codigos = {"C3", "C4", "C7"};
        for (String codigo : codigos) {
            boolean resultado = retirarCaja(pila, codigo);
            System.out.println("Retirar " + codigo + " -> retorna " + resultado
                    + " | pila: " + pila);
        }

        System.out.println("Pila final (de la cima al fondo): " + pila);
    }
}
