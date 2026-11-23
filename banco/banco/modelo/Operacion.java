package banco.modelo;

import java.time.LocalDateTime;

/**
 * Registro inmutable. Su constructor es de paquete: solo Cuenta
 * (mismo paquete) puede crear operaciones; desde otros paquetes solo se leen.
 */
public final class Operacion {
    private final TipoOperacion tipo;
    private final double monto;
    private final double saldoResultante;
    private final String detalle;
    private final LocalDateTime fecha;

    Operacion(TipoOperacion tipo, double monto, double saldoResultante, String detalle) {
        this.tipo = tipo;
        this.monto = monto;
        this.saldoResultante = saldoResultante;
        this.detalle = detalle;
        this.fecha = LocalDateTime.now();
    }

    public TipoOperacion getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public double getSaldoResultante() { return saldoResultante; }
    public String getDetalle() { return detalle; }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public String toString() {
        return String.format("%-22s $%8.2f | saldo: $%8.2f | %s",
                tipo, monto, saldoResultante, detalle);
    }
}
