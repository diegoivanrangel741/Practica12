public class PruebaSueldo {
    public static void main(String[] args) {
        System.out.println("--- Caso 1: valores validos ---");
        Sueldo sueldo = new Sueldo(8500);
        System.out.println("Sueldo inicial: " + sueldo);
        sueldo.setMonto(12000);
        System.out.println("Sueldo nuevo:   " + sueldo);

        System.out.println();

        System.out.println("--- Caso 2: valor negativo en el setter ---");
        sueldo.setMonto(-500);
        System.out.println("Sueldo actual:  " + sueldo);

        System.out.println();

        System.out.println("--- Caso 3: constructor con valor negativo ---");
        Sueldo otro = new Sueldo(-100);
        System.out.println("Sueldo actual:  " + otro);

        System.out.println();

        System.out.println("--- Caso 4: limite (cero) ---");
        sueldo.setMonto(0);
        System.out.println("Sueldo actual:  " + sueldo);

        System.out.println();

        System.out.println("--- Caso 5: valores no numericos ---");
        sueldo.setMonto(Double.NaN);
        sueldo.setMonto(Double.POSITIVE_INFINITY);
        System.out.println("Sueldo actual:  " + sueldo);
    }
}
