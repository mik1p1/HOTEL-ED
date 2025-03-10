/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class Hotel {
    private static Scanner sc = new Scanner(System.in);
    private static Cliente cliente1, cliente2, cliente3;
    private static Reserva reserva1, reserva2, reserva3;

    public static void main(String[] args) throws Exception, Exception {
        int opcion = 0;
        do {
            System.out.println("\n--- Gestión de Reservas en un Hotel ---");
            System.out.println("1. Crear clientes");
            System.out.println("2. Crear reserva");
            System.out.println("3. Mostrar datos de reservas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    altaClientes();
                    break;
                case 2:
                {
                    try {
                        crearReserva();
                    } catch (Exception ex) {
                        Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case 3:
                    mostrarReservas();
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void altaClientes() {
        try {
            System.out.println("\nIntroduzca los datos del cliente:");
            String nombre;
            do {
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
            } while (nombre.isEmpty());

            String dni = null;
            boolean dniValido;
            do {
                try {
                    System.out.print("Introduzca DNI: ");
                    dni = sc.nextLine();
                    Utilidades.validarDNI(dni);
                    dniValido = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    dniValido = false;
                }
            } while (!dniValido);

            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();

            cliente1 = new Cliente(nombre, dni, telefono);
            System.out.println("Cliente creado correctamente:\n" + cliente1.mostrarInformacion());

            cliente2 = new Cliente("Ana García", "12345678Z", "600111222");
            cliente3 = new Cliente("Luis Martínez", "87654321X", "600333444");
            System.out.println("Cliente creado correctamente:\n" + cliente2.mostrarInformacion());
            System.out.println("Cliente creado correctamente:\n" + cliente3.mostrarInformacion());
        } catch (Exception e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
        }
    }

    private static void crearReserva() throws Exception {
        if (cliente1 == null || cliente2 == null || cliente3 == null) {
            System.out.println("Primero debe crear los clientes (opción 1).");
            return;
        }

        System.out.println("\nIntroduzca los datos para la reserva:");
        System.out.print("Número de cliente (1, 2 o 3): ");
        int numCliente = Integer.parseInt(sc.nextLine());
        Cliente clienteSeleccionado = switch (numCliente) {
            case 1 -> cliente1;
            case 2 -> cliente2;
            case 3 -> cliente3;
            default -> {
                System.out.println("Número de cliente no válido.");
                yield null;
            }
        };
        if (clienteSeleccionado == null) return;

        LocalDate fechaEntrada;
        do {
            fechaEntrada = Utilidades.leerFecha("Introduzca la fecha de entrada");
        } while (fechaEntrada.isBefore(LocalDate.now()));

        LocalDate fechaSalida;
        do {
            fechaSalida = Utilidades.leerFecha("Introduzca la fecha de salida");
            try {
                Utilidades.validarFechas(fechaEntrada, fechaSalida);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Por favor, intente de nuevo.");
            }
        } while (true);

        TipoHabitacion tipoHabitacion;
        while (true) {
            System.out.print("Tipo de habitación (DOBLE/SUITE): ");
            String tipoHabCadena = sc.nextLine().toUpperCase();
            if (tipoHabCadena.equals("DOBLE")) {
                tipoHabitacion = TipoHabitacion.DOBLE;
                break;
            } else if (tipoHabCadena.equals("SUITE")) {
                tipoHabitacion = TipoHabitacion.SUITE;
                break;
            } else {
                System.out.println("Tipo de habitación no válido.");
            }
        }

        System.out.print("¿Se solicita cama supletoria? (S/N): ");
        boolean camaSupletoria = sc.nextLine().trim().equalsIgnoreCase("S");

        Reserva nuevaReserva = new Reserva(clienteSeleccionado, fechaEntrada, fechaSalida, tipoHabitacion, camaSupletoria);
        if (reserva1 == null) {
            reserva1 = nuevaReserva;
        } else if (reserva2 == null) {
            reserva2 = nuevaReserva;
        } else if (reserva3 == null) {
            reserva3 = nuevaReserva;
        } else {
            System.out.println("Se ha alcanzado el máximo de reservas permitidas.");
            return;
        }
        System.out.println("Reserva creada correctamente:\n" + nuevaReserva.mostrarDetalles());
    }

    private static void mostrarReservas() {
        System.out.println("\n=== Reservas creadas ===");
        if (reserva1 != null) System.out.println(reserva1.mostrarDetalles() + "\n---------------------------");
        if (reserva2 != null) System.out.println(reserva2.mostrarDetalles() + "\n---------------------------");
        if (reserva3 != null) System.out.println(reserva3.mostrarDetalles() + "\n---------------------------");
        if (reserva1 == null && reserva2 == null && reserva3 == null) {
            System.out.println("No hay reservas creadas.");
        }
    }
}
