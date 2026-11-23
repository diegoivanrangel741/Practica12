import java.util.ArrayList;

/** Administra la plantilla de empleados y la nomina. */
public class SistemaEmpleados {

    private ArrayList<Empleado> empleados = new ArrayList<>();

    public boolean registrar(Empleado empleado) {
        if (empleado == null || !empleado.isValido()) {
            System.out.println("Registro rechazado: el empleado no tiene numero o nombre validos");
            return false;
        }
        if (buscar(empleado.getNumero()) != null) {
            System.out.println("Registro rechazado: ya existe el empleado " + empleado.getNumero());
            return false;
        }
        empleados.add(empleado);
        return true;
    }

    public Empleado buscar(int numero) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNumero() == numero) {
                return empleados.get(i);
            }
        }
        return null;
    }

    public boolean incrementarSueldo(int numero, double porcentaje, String motivo) {
        Empleado empleado = buscar(numero);
        if (empleado == null) {
            System.out.println("  Rechazado: no existe el empleado " + numero);
            return false;
        }
        return empleado.aplicarIncremento(porcentaje, motivo);
    }

    public void mostrarNomina() {
        System.out.println("No.  Nombre               Antig.  Incr.  Sueldo");
        for (Empleado e : empleados) {
            System.out.printf("%-4d %-20s %-7d %-6d $%.2f%n",
                    e.getNumero(), e.getNombre(), e.getAntiguedad(),
                    e.getIncrementosAnio(), e.getSueldoActual());
        }
    }
}
