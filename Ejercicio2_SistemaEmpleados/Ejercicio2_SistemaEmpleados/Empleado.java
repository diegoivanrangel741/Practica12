/**
 * Empleado de la empresa.
 * El sueldo (composicion "tiene-un") es privado y NO tiene setter:
 * solo cambia con aplicarIncremento(), que valida las reglas autorizadas.
 */
public class Empleado {

    // Reglas de negocio (constantes privadas)
    private static final double TOPE_SUELDO = 100000.0;
    private static final int MAX_INCREMENTOS_ANIO = 2;
    private static final int ANTIGUEDAD_MINIMA = 1;
    private static final String NOMBRE_POR_DEFECTO = "SIN NOMBRE";

    private int numero;
    private String nombre = NOMBRE_POR_DEFECTO;
    private int antiguedad;
    private Sueldo sueldo;
    private int incrementosAnio;

    public Empleado(int numero, String nombre, int antiguedad, double sueldoInicial) {
        setNumero(numero);
        setNombre(nombre);
        setAntiguedad(antiguedad);
        this.sueldo = new Sueldo(sueldoInicial);
    }

    // ---------- Consultores ----------
    /** Un empleado es valido si tiene numero y nombre correctos. */
    public boolean isValido() { return numero > 0 && !nombre.equals(NOMBRE_POR_DEFECTO); }
    public int getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public int getAntiguedad() { return antiguedad; }
    public int getIncrementosAnio() { return incrementosAnio; }

    /** Devuelve un double, NO el objeto Sueldo (evita que otra clase lo modifique). */
    public double getSueldoActual() { return sueldo.getMonto(); }

    // ---------- Modificadores ----------
    public void setNombre(String nombre) {
        if (nombreValido(nombre)) {
            this.nombre = nombre.trim();
        } else {
            System.out.println("Nombre invalido ('" + nombre + "'). Se conserva '" + this.nombre + "'");
        }
    }

    private void setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
        } else {
            System.out.println("Numero de empleado invalido (" + numero + "): debe ser mayor a 0");
        }
    }

    private void setAntiguedad(int antiguedad) {
        if (antiguedad >= 0) {
            this.antiguedad = antiguedad;
        } else {
            System.out.println("Antiguedad invalida (" + antiguedad + "): no puede ser negativa");
        }
    }

    // ---------- Unica via para cambiar el sueldo ----------
    /**
     * Aplica un incremento porcentual solo si cumple TODAS las reglas autorizadas.
     * @return true si el incremento se aplico, false si fue rechazado
     */
    public boolean aplicarIncremento(double porcentaje, String motivo) {
        double maximo = porcentajeMaximo(motivo);
        if (maximo < 0) {
            System.out.println("  Rechazado: motivo '" + motivo + "' no autorizado");
            return false;
        }
        if (!(porcentaje > 0) || porcentaje > maximo) {
            System.out.println("  Rechazado: el porcentaje " + porcentaje + "% debe estar entre 0 y "
                    + maximo + "% para " + motivo);
            return false;
        }
        if (antiguedad < ANTIGUEDAD_MINIMA) {
            System.out.println("  Rechazado: requiere al menos " + ANTIGUEDAD_MINIMA + " anio de antiguedad");
            return false;
        }
        if (incrementosAnio >= MAX_INCREMENTOS_ANIO) {
            System.out.println("  Rechazado: ya se aplicaron " + MAX_INCREMENTOS_ANIO + " incrementos este anio");
            return false;
        }
        double nuevo = Math.round(sueldo.getMonto() * (1 + porcentaje / 100) * 100) / 100.0;
        if (nuevo > TOPE_SUELDO) {
            System.out.println("  Rechazado: el nuevo sueldo $" + String.format("%.2f", nuevo)
                    + " rebasa el tope de $" + String.format("%.2f", TOPE_SUELDO));
            return false;
        }
        sueldo.setMonto(nuevo);
        incrementosAnio++;
        System.out.println("  Aprobado: +" + porcentaje + "% por " + motivo + " -> " + sueldo);
        return true;
    }

    /** Cierra el anio: sube la antiguedad y reinicia el contador de incrementos. */
    public void cerrarAnio() {
        antiguedad++;
        incrementosAnio = 0;
    }

    // ---------- Metodos auxiliares internos (private) ----------
    private double porcentajeMaximo(String motivo) {
        if (motivo == null) {
            return -1;
        }
        switch (motivo.trim().toUpperCase()) {
            case "ANTIGUEDAD": return 5;
            case "DESEMPENO":  return 10;
            case "PROMOCION":  return 20;
            default:           return -1;
        }
    }

    private boolean nombreValido(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return false;
        }
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
}
