package banco.modelo;

import banco.excepciones.OperacionInvalidaExcepcion;
import banco.excepciones.SaldoInsuficienteExcepcion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cuenta {
    private final String numero;
    private final String titular;
    private double saldo;                         // sin setter: solo cambia por operaciones
    private final List<Operacion> historial = new ArrayList<>();

    public Cuenta(String numero, String titular) throws OperacionInvalidaExcepcion {
        if (numero == null || numero.isBlank())
            throw new OperacionInvalidaExcepcion("El número de cuenta es obligatorio");
        if (titular == null || titular.isBlank())
            throw new OperacionInvalidaExcepcion("El titular es obligatorio");
        this.numero = numero;
        this.titular = titular;
    }

    // ---- Operaciones públicas (validadas) ----

    public void depositar(double monto) throws OperacionInvalidaExcepcion {
        validarMonto(monto);
        saldo += monto;
        registrar(TipoOperacion.DEPOSITO, monto, "Depósito en efectivo");
    }

    public void retirar(double monto) throws OperacionInvalidaExcepcion {
        validarMonto(monto);
        validarFondos(monto);
        saldo -= monto;
        registrar(TipoOperacion.RETIRO, monto, "Retiro en efectivo");
    }

    public void transferir(Cuenta destino, double monto) throws OperacionInvalidaExcepcion {
        if (destino == null)
            throw new OperacionInvalidaExcepcion("La cuenta destino no puede ser nula");
        if (destino == this)
            throw new OperacionInvalidaExcepcion("No se puede transferir a la misma cuenta");
        validarMonto(monto);
        validarFondos(monto);

        // Todas las validaciones pasaron: ambos movimientos se aplican juntos
        this.saldo -= monto;
        this.registrar(TipoOperacion.TRANSFERENCIA_ENVIADA, monto, "A cuenta " + destino.numero);
        destino.saldo += monto;
        destino.registrar(TipoOperacion.TRANSFERENCIA_RECIBIDA, monto, "De cuenta " + this.numero);
    }

    // ---- Consultas (solo lectura) ----

    public double getSaldo() { return saldo; }
    public String getNumero() { return numero; }
    public String getTitular() { return titular; }

    /** Vista de solo lectura: el exterior no puede agregar ni borrar operaciones. */
    public List<Operacion> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    // ---- Auxiliares privados ----

    private void validarMonto(double monto) throws OperacionInvalidaExcepcion {
        if (Double.isNaN(monto) || Double.isInfinite(monto) || monto <= 0)
            throw new OperacionInvalidaExcepcion("El monto debe ser mayor que cero");
    }

    private void validarFondos(double monto) throws SaldoInsuficienteExcepcion {
        if (monto > saldo) throw new SaldoInsuficienteExcepcion(saldo, monto);
    }

    private void registrar(TipoOperacion tipo, double monto, String detalle) {
        historial.add(new Operacion(tipo, monto, saldo, detalle));
    }
}
