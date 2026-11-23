/**
 * Representa el sueldo de una persona.
 * El monto es privado y nunca puede quedar negativo.
 */
public class Sueldo {

    private double monto;

    /** El constructor reutiliza el setter para no repetir la validación. */
    public Sueldo(double monto) {
        setMonto(monto);
    }

    public double getMonto() {
        return monto;
    }

    /** Solo acepta montos finitos y mayores o iguales a cero. */
    public void setMonto(double monto) {
        if (Double.isFinite(monto) && monto >= 0) {
            this.monto = monto;
        } else {
            System.out.println("Monto invalido (" + monto + "): el sueldo no puede ser negativo. "
                    + "Se conserva " + this);
        }
    }

    @Override
    public String toString() {
        return String.format("$%.2f", monto);
    }
}
