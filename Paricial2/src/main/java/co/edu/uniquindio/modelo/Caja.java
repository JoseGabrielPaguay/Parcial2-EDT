package co.edu.uniquindio.modelo;

/** Caja del deposito: tiene un codigo unico y un valor entero. */
public class Caja {

    private final String codigo;
    private final int valor;

    public Caja(String codigo, int valor) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        this.codigo = codigo;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getValor() {
        return valor;
    }

    /** Ejemplo: "C3:6". */
    @Override
    public String toString() {
        return codigo + ":" + valor;
    }
}
