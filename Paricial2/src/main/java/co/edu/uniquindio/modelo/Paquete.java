package co.edu.uniquindio.modelo;

/** Paquete pendiente de entrega: codigo, tipo de entrega y peso en kilogramos. */
public class Paquete {

    private final String codigo;
    private final TipoEntrega tipo;
    private final int peso;

    public Paquete(String codigo, TipoEntrega tipo, int peso) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de entrega no puede ser null");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que 0");
        }
        this.codigo = codigo;
        this.tipo = tipo;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoEntrega getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

    /** Ejemplo: "P2 (URGENTE, 7 kg)". */
    @Override
    public String toString() {
        return codigo + " (" + tipo + ", " + peso + " kg)";
    }
}