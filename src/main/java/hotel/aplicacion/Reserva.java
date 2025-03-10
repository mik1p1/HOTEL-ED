/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Luismi
 */


/**
 * Clase que representa una reserva en el hotel. Contiene información del cliente, fechas de entrada y salida, tipo de habitación,
 * si se solicita cama supletoria y el coste total de la estancia.
 */
public class Reserva {
    private static int contadorReservas = 0; // Contador para asignar numeros únicos a las reservas
    private int codigoReserva; // Código único de la reserva.
    private Cliente cliente; // Cliente que realiza la resevraa
    private LocalDate fechaEntrada; // Fecha de entrada al hotel.
    private LocalDate fechaSalida; // Fecha de salida del hotel.
    private TipoHabitacion tipoHabitacion; // Tipo de habitación reservada.
    private boolean camaSupletoria;// Indica si se solicita cama supletoria.
    private double costeTotal; // Coste total de la reserva.
    
    // Constantes fijas para el precio
    private static final double precioDoble = 50.0;
    private static final double precioSuite = 100.0;
    private static final double recargoCamaSupletoria = 20.0;

    
     /**
     * Constructor de la clase Reserva.
     * Valida que la fecha de salida sea posterior a la de entrada y calcula el coste total.
     *
     * @param cliente        Cliente que realiza la reserva.
     * @param fechaEntrada   Fecha de entrada al hotel.
     * @param fechaSalida    Fecha de salida del hotel.
     * @param tipoHabitacion Tipo de habitación reservada (DOBLE o SUITE).
     * @param camaSupletoria Indica si se solicita cama supletoria.
     * @throws Exception Si la fecha de salida no es posterior a la de entrada.
     */
       public Reserva(Cliente cliente, LocalDate fechaEntrada, LocalDate fechaSalida,
                   TipoHabitacion tipoHabitacion, boolean camaSupletoria) throws Exception {
        if (!fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.codigoReserva = obtenerCodigoReserva();
        this.cliente = cliente;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tipoHabitacion = tipoHabitacion;
        this.camaSupletoria = camaSupletoria;
        this.costeTotal = calcularCosteTotal();
    }

    /**
     * Método estático que genera un código único para cada reserva.
     *
     * @return Código de la reserva generado.
     */
    private static int obtenerCodigoReserva() {
        contadorReservas++;
        return contadorReservas;
    }
 /**
     * Calcula el coste total de la reserva en función del número de noches,
     * el tipo de habitación y si se solicita cama supletoria.
     * Aplica un 10% de descuento si la estancia es superior a 7 noches.
     *
     * @return Coste total de la reserva.
     */
    
    public double calcularCosteTotal() {
        long noches = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        double precioNoche = (tipoHabitacion == TipoHabitacion.DOBLE) ? precioDoble : precioSuite;
        
        if (camaSupletoria) {
            precioNoche += recargoCamaSupletoria;
        }
        double total = noches * precioNoche;
        if (noches > 7) {
            total *= 0.9; // Aplica un descuento del 10%
        }
        return total;
    }

     /**
     * Muestra los detalles de la reserva en un formato legible.
     *
     * @return Cadena con los detalles de la reserva.
     */
    public String mostrarDetalles() {
        String detalles = "Código Reserva: " + codigoReserva + "\n" +
                          "Cliente: " + cliente.mostrarInformacion() + "\n" +
                          "Fecha de Entrada: " + fechaEntrada + "\n" +
                          "Fecha de Salida: " + fechaSalida + "\n" +
                          "Tipo de Habitación: " + tipoHabitacion + "\n" +
                          "Cama Supletoria: " + (camaSupletoria ? "Sí" : "No") + "\n" +
                          "Coste Total: " + costeTotal + "Euros";
        return detalles;
    }

    public int getCodigoReserva() {
        return codigoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public boolean iscamaSupletoria() {
        return camaSupletoria;
    }

    public double getCosteTotal() {
        return costeTotal;
    }
    
    public void setFechaEntrada(LocalDate fechaEntrada) throws Exception {
        if (fechaSalida != null && !fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.fechaEntrada = fechaEntrada;
        this.costeTotal = calcularCosteTotal();
    }

   
    public void setFechaSalida(LocalDate fechaSalida) throws Exception {
        if (fechaEntrada != null && !fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.fechaSalida = fechaSalida;
        this.costeTotal = calcularCosteTotal();
    }
}