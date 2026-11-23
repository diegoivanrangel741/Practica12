package banco.app;

import banco.excepciones.OperacionInvalidaExcepcion;
import banco.modelo.Cuenta;
import banco.modelo.Operacion;

public class Main {
    public static void main(String[] args) {
        try {
            Cuenta ana = new Cuenta("001", "Ana");
            Cuenta luis = new Cuenta("002", "Luis");

            ana.depositar(1000);
            ana.retirar(200);
            ana.transferir(luis, 300);

            // Pruebas de validación
            intentar("Retiro mayor al saldo", () -> ana.retirar(5000));
            intentar("Depósito negativo", () -> ana.depositar(-50));
            intentar("Transferencia a sí misma", () -> ana.transferir(ana, 10));

            // Acceso directo al saldo (no compila):
            // ana.saldo = 1000000;            // error: saldo has private access in Cuenta
            // ana.getHistorial().clear();     // UnsupportedOperationException

            mostrar(ana);
            mostrar(luis);
        } catch (OperacionInvalidaExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    interface Accion { void ejecutar() throws OperacionInvalidaExcepcion; }

    static void intentar(String descripcion, Accion a) {
        try {
            a.ejecutar();
        } catch (OperacionInvalidaExcepcion e) {
            System.out.println("[Rechazada] " + descripcion + " -> " + e.getMessage());
        }
    }

    static void mostrar(Cuenta c) {
        System.out.println("\nCuenta " + c.getNumero() + " (" + c.getTitular()
                + ") - Saldo: $" + c.getSaldo());
        for (Operacion o : c.getHistorial()) System.out.println("  " + o);
    }
}
