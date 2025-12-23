import controlador.Controlador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Controlador controlador = new Controlador();

        while (true) {
            System.out.println("\n=== CONVERSOR DE MONEDAS ===");
            System.out.println("1) Convertir moneda");
            System.out.println("2) Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = sc.nextLine();

            switch (opcion) {

                case "1":
                    try {
                        System.out.print("Ingrese el monto: ");
                        double monto = Double.parseDouble(sc.nextLine());

                        System.out.print("Moneda origen (USD, EUR, COP): ");
                        String origen = sc.nextLine().toUpperCase();

                        System.out.print("Moneda destino (USD, EUR, COP): ");
                        String destino = sc.nextLine().toUpperCase();

                        double resultado =
                                controlador.convertir(monto, origen, destino);

                        System.out.println(
                                "Resultado: " + resultado + " " + destino
                        );

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("Saliendo del sistema...");
                    return;

                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}
