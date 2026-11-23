package banco.excepciones;

public class SaldoInsuficienteExcepcion extends OperacionInvalidaExcepcion {
    public SaldoInsuficienteExcepcion(double saldo, double monto) {
        super("Saldo insuficiente: saldo $" + saldo + ", monto solicitado $" + monto);
    }
}
