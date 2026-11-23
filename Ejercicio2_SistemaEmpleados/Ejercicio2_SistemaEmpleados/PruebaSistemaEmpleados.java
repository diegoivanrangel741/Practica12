public class PruebaSistemaEmpleados {
    public static void main(String[] args) {
        SistemaEmpleados sistema = new SistemaEmpleados();

        System.out.println("=== Registro de empleados ===");
        sistema.registrar(new Empleado(1, "Ana Torres", 3, 15000));
        sistema.registrar(new Empleado(2, "Luis Perez", 0, 9000));
        sistema.registrar(new Empleado(3, "Marta Lopez", 5, 98000));
        sistema.registrar(new Empleado(0, "Carlos Ruiz", 2, 12000));   // numero invalido
        sistema.registrar(new Empleado(4, "Carlos123", 2, 12000));     // nombre invalido
        sistema.registrar(new Empleado(1, "Repetido", 1, 8000));       // numero duplicado
        sistema.registrar(new Empleado(5, "Sofia Diaz", 2, -3000));    // sueldo negativo
        System.out.println();
        sistema.mostrarNomina();

        System.out.println();

        System.out.println("=== Incrementos autorizados ===");
        System.out.println("Ana: 5% por ANTIGUEDAD");
        sistema.incrementarSueldo(1, 5, "ANTIGUEDAD");
        System.out.println("Ana: 10% por DESEMPENO");
        sistema.incrementarSueldo(1, 10, "DESEMPENO");

        System.out.println();

        System.out.println("=== Incrementos rechazados ===");
        System.out.println("Ana: tercer incremento del anio");
        sistema.incrementarSueldo(1, 3, "ANTIGUEDAD");
        System.out.println("Ana: motivo inventado");
        sistema.incrementarSueldo(1, 3, "CAPRICHO");
        System.out.println("Luis: sin antiguedad suficiente");
        sistema.incrementarSueldo(2, 5, "ANTIGUEDAD");
        System.out.println("Marta: 25% por PROMOCION (excede el 20%)");
        sistema.incrementarSueldo(3, 25, "PROMOCION");
        System.out.println("Marta: 10% por DESEMPENO (rebasa el tope)");
        sistema.incrementarSueldo(3, 10, "DESEMPENO");
        System.out.println("Marta: porcentaje negativo");
        sistema.incrementarSueldo(3, -5, "DESEMPENO");
        System.out.println("Empleado inexistente");
        sistema.incrementarSueldo(99, 5, "ANTIGUEDAD");

        System.out.println();

        System.out.println("=== Cierre de anio y nuevo incremento ===");
        sistema.buscar(1).cerrarAnio();
        System.out.println("Ana: 2% por ANTIGUEDAD");
        sistema.incrementarSueldo(1, 2, "ANTIGUEDAD");

        System.out.println();

        System.out.println("=== Cambio de nombre ===");
        sistema.buscar(2).setNombre("   ");
        sistema.buscar(2).setNombre("Luis Alberto Perez");

        System.out.println();

        System.out.println("=== Nomina final ===");
        sistema.mostrarNomina();
    }
}
