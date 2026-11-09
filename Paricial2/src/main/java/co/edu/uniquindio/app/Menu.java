package co.edu.uniquindio.app;

import co.edu.uniquindio.ejercicios.Ejercicio1;
import co.edu.uniquindio.ejercicios.Ejercicio2;
import co.edu.uniquindio.ejercicios.Ejercicio3;
import co.edu.uniquindio.ejercicios.Ejercicio4;
import co.edu.uniquindio.estructuras.ArbolBinarioBusqueda;
import co.edu.uniquindio.estructuras.Cola;
import co.edu.uniquindio.estructuras.Pila;
import co.edu.uniquindio.modelo.Caja;
import co.edu.uniquindio.modelo.Paquete;
import co.edu.uniquindio.modelo.TipoEntrega;

import java.util.Scanner;

/**
 * Menu por consola para la sustentacion.
 * Sirve para correr cada ejercicio con los datos del enunciado o con datos nuevos
 * (por ejemplo, si el profe pide retirar otra caja u otro segmento).
 *
 * Las estructuras de cada ejercicio se guardan mientras el menu esta abierto,
 * asi se pueden hacer varias operaciones seguidas sobre la misma pila o cola.
 */
public class Menu {

    private static final Scanner teclado = new Scanner(System.in);

    // Datos con los que se trabaja en cada ejercicio
    private static Pila<Caja> cajas = new Pila<>();
    private static Pila<String> historial = new Pila<>();
    private static Cola<Paquete> paquetes = new Cola<>();
    private static ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("===== PARCIAL 2 - ESTRUCTURAS DE DATOS =====");
            System.out.println("1. Ejercicio 1 - Retiro de cajas (pila)");
            System.out.println("2. Ejercicio 2 - Segmento del historial (pila)");
            System.out.println("3. Ejercicio 3 - Seleccion de paquetes (cola)");
            System.out.println("4. Ejercicio 4 - Equipos por rango (arbol)");
            System.out.println("0. Salir");
            opcion = leerEntero("Escoja una opcion: ");

            switch (opcion) {
                case 1:
                    menuEjercicio1();
                    break;
                case 2:
                    menuEjercicio2();
                    break;
                case 3:
                    menuEjercicio3();
                    break;
                case 4:
                    menuEjercicio4();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Esa opcion no existe.");
            }
        }
    }

    // ------------------------------------------------------------------
    // EJERCICIO 1: retirar cajas
    // ------------------------------------------------------------------

    private static void menuEjercicio1() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("--- Ejercicio 1: retiro de cajas ---");
            System.out.println("Pila actual (del tope al fondo): " + cajas);
            System.out.println("1. Cargar las cajas del enunciado (C1 a C7)");
            System.out.println("2. Agregar una caja");
            System.out.println("3. Retirar una caja");
            System.out.println("4. Vaciar la pila");
            System.out.println("5. Correr la prueba del enunciado (C3, C4 y C7)");
            System.out.println("0. Volver");
            opcion = leerEntero("Escoja una opcion: ");

            switch (opcion) {
                case 1:
                    cajas = new Pila<>();
                    cajas.apilar(new Caja("C1", 2));
                    cajas.apilar(new Caja("C2", 4));
                    cajas.apilar(new Caja("C3", 6));
                    cajas.apilar(new Caja("C4", 8));
                    cajas.apilar(new Caja("C5", 3));
                    cajas.apilar(new Caja("C6", 9));
                    cajas.apilar(new Caja("C7", 5));
                    System.out.println("Se cargaron las 7 cajas.");
                    break;
                case 2:
                    String codigo = leerTexto("Codigo de la caja: ");
                    int valor = leerEntero("Valor de la caja: ");
                    cajas.apilar(new Caja(codigo, valor));
                    System.out.println("Caja agregada en la cima.");
                    break;
                case 3:
                    String buscar = leerTexto("Codigo de la caja a retirar: ");
                    boolean resultado = Ejercicio1.retirarCaja(cajas, buscar);
                    System.out.println("Retorna: " + resultado);
                    System.out.println("Pila despues: " + cajas);
                    break;
                case 4:
                    cajas = new Pila<>();
                    System.out.println("La pila quedo vacia.");
                    break;
                case 5:
                    Ejercicio1.mostrarPrueba();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Esa opcion no existe.");
            }
        }
    }

    // ------------------------------------------------------------------
    // EJERCICIO 2: segmento del historial
    // ------------------------------------------------------------------

    private static void menuEjercicio2() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("--- Ejercicio 2: segmento del historial ---");
            System.out.println("Historial actual (del tope al fondo): " + historial);
            System.out.println("1. Cargar el ejemplo del enunciado (A1 a A6)");
            System.out.println("2. Agregar una accion");
            System.out.println("3. Obtener un segmento");
            System.out.println("4. Vaciar el historial");
            System.out.println("5. Correr la prueba del enunciado (inicio 2, fin 4)");
            System.out.println("0. Volver");
            opcion = leerEntero("Escoja una opcion: ");

            switch (opcion) {
                case 1:
                    historial = new Pila<>();
                    historial.apilar("A1");
                    historial.apilar("A2");
                    historial.apilar("A3");
                    historial.apilar("A4");
                    historial.apilar("A5");
                    historial.apilar("A6");
                    System.out.println("Se cargaron las 6 acciones.");
                    break;
                case 2:
                    String accion = leerTexto("Nombre de la accion: ");
                    historial.apilar(accion);
                    System.out.println("Accion agregada en la cima.");
                    break;
                case 3:
                    int inicio = leerEntero("Posicion de inicio: ");
                    int fin = leerEntero("Posicion de fin: ");
                    Pila<String> segmento = Ejercicio2.obtenerSegmento(historial, inicio, fin);
                    System.out.println("Segmento: " + segmento);
                    System.out.println("Historial despues (debe quedar igual): " + historial);
                    break;
                case 4:
                    historial = new Pila<>();
                    System.out.println("El historial quedo vacio.");
                    break;
                case 5:
                    Ejercicio2.mostrarPrueba();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Esa opcion no existe.");
            }
        }
    }

    // ------------------------------------------------------------------
    // EJERCICIO 3: seleccion de paquetes
    // ------------------------------------------------------------------

    private static void menuEjercicio3() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("--- Ejercicio 3: seleccion de paquetes ---");
            System.out.println("Pendientes (del frente al final): " + paquetes);
            System.out.println("1. Cargar los paquetes del enunciado (P1 a P7)");
            System.out.println("2. Agregar un paquete");
            System.out.println("3. Seleccionar paquetes con una capacidad");
            System.out.println("4. Vaciar la cola");
            System.out.println("5. Correr la prueba del enunciado (capacidad 14)");
            System.out.println("0. Volver");
            opcion = leerEntero("Escoja una opcion: ");

            switch (opcion) {
                case 1:
                    paquetes = new Cola<>();
                    paquetes.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
                    paquetes.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
                    paquetes.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
                    paquetes.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
                    paquetes.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
                    paquetes.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
                    paquetes.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));
                    System.out.println("Se cargaron los 7 paquetes.");
                    break;
                case 2:
                    String codigo = leerTexto("Codigo del paquete: ");
                    int tipo = leerEntero("Tipo (1 = URGENTE, 2 = NORMAL): ");
                    int peso = leerEntero("Peso en kg (mayor que 0): ");
                    if (peso <= 0) {
                        System.out.println("El peso debe ser mayor que 0.");
                    } else if (tipo == 1) {
                        paquetes.encolar(new Paquete(codigo, TipoEntrega.URGENTE, peso));
                        System.out.println("Paquete agregado al final.");
                    } else {
                        paquetes.encolar(new Paquete(codigo, TipoEntrega.NORMAL, peso));
                        System.out.println("Paquete agregado al final.");
                    }
                    break;
                case 3:
                    int capacidad = leerEntero("Capacidad del vehiculo en kg: ");
                    Cola<Paquete> seleccionados = Ejercicio3.seleccionarPaquetes(paquetes, capacidad);
                    System.out.println("Seleccionados: " + seleccionados);
                    System.out.println("Pendientes: " + paquetes);
                    break;
                case 4:
                    paquetes = new Cola<>();
                    System.out.println("La cola quedo vacia.");
                    break;
                case 5:
                    Ejercicio3.mostrarPrueba();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Esa opcion no existe.");
            }
        }
    }

    // ------------------------------------------------------------------
    // EJERCICIO 4: equipos por rango
    // ------------------------------------------------------------------

    private static void menuEjercicio4() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("--- Ejercicio 4: equipos por rango ---");
            System.out.println("Inorden del arbol: " + arbol.inorden());
            System.out.println("1. Cargar los equipos del 4b (41, 20, 52, ...)");
            System.out.println("2. Agregar un equipo");
            System.out.println("3. Ver los cuatro recorridos");
            System.out.println("4. Contar equipos en un rango");
            System.out.println("5. Vaciar el arbol");
            System.out.println("6. Correr la prueba del enunciado");
            System.out.println("0. Volver");
            opcion = leerEntero("Escoja una opcion: ");

            switch (opcion) {
                case 1:
                    arbol = new ArbolBinarioBusqueda();
                    int[] codigos = {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 60, 43, 44};
                    for (int c : codigos) {
                        if (!arbol.insertar(c)) {
                            System.out.println("El " + c + " ya existe, no se inserta.");
                        }
                    }
                    System.out.println("Se cargaron los equipos.");
                    break;
                case 2:
                    int codigo = leerEntero("Codigo del equipo: ");
                    if (arbol.insertar(codigo)) {
                        System.out.println("Equipo agregado.");
                    } else {
                        System.out.println("Ese codigo ya existe, no se inserta.");
                    }
                    break;
                case 3:
                    System.out.println("Inorden:   " + arbol.inorden());
                    System.out.println("Preorden:  " + arbol.preorden());
                    System.out.println("Postorden: " + arbol.postorden());
                    System.out.println("Amplitud:  " + arbol.amplitud());
                    break;
                case 4:
                    int inferior = leerEntero("Limite inferior: ");
                    int superior = leerEntero("Limite superior: ");
                    int cantidad = Ejercicio4.contarEquiposEnRango(arbol.getRaiz(), inferior, superior);
                    System.out.println("Equipos en el rango: " + cantidad);
                    break;
                case 5:
                    arbol = new ArbolBinarioBusqueda();
                    System.out.println("El arbol quedo vacio.");
                    break;
                case 6:
                    Ejercicio4.mostrarPrueba();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Esa opcion no existe.");
            }
        }
    }

    // ------------------------------------------------------------------
    // LEER DATOS DEL TECLADO
    // ------------------------------------------------------------------

    /** Pide un numero entero. Si escriben algo que no es numero, lo vuelve a pedir. */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = teclado.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Debe escribir un numero entero.");
            }
        }
    }

    /** Pide un texto que no este vacio. */
    private static String leerTexto(String mensaje) {
        String linea = "";
        while (linea.isEmpty()) {
            System.out.print(mensaje);
            linea = teclado.nextLine().trim();
            if (linea.isEmpty()) {
                System.out.println("No puede quedar vacio.");
            }
        }
        return linea;
    }
}