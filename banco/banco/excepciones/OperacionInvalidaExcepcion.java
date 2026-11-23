package banco.excepciones;

public class OperacionInvalidaExcepcion extends Exception {
    public OperacionInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
