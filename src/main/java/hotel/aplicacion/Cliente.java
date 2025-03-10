/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import hotel.aplicacion.Utilidades;

/**
 *
 * @author Luismi
 */


/**
 * Clase que representa un cliente del hotel.
 * Contiene información básica como su código, nombre, DNI y teléfono.
 */
public class Cliente {
    private static int contadorClientes = 0; // Contador estático para asignar códigos únicos a los clientes.
    public int codigo; // Código del cliente
    public String nombre; // Nombre del cliente
    public String dni; // DNI del cliente
    public String telefono; // Teléfono del cliente

    
     /**
     * Constructor de la clase Cliente. Valida el DNI antes de crear el objeto.
     * @param nombre Nombre del cliente.
     * @param dni DNI del cliente (debe ser válido).
     * @param telefono Teléfono del cliente.
     * @throws Exception Si el DNI no es válido.
     */
    
    public Cliente(String nombre, String dni, String telefono) throws Exception {
        // Validación del DNI, si no es correcto no creará el objeto
        Utilidades.validarDNI(dni); // Llama al método para la validación del DNI
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.codigo = obtenerNumeroCliente();
    }

    
    /**
     * Método estático que genera un número único para cada cliente.
     *
     * @return Número de cliente generado.
     */
        private static int obtenerNumeroCliente() {
        contadorClientes++;
        return contadorClientes;
    }

    /**
     * Método que devuelve la información del cliente en formato de cadena.
     *
     * @return Cadena con los datos del cliente.
     */
    public String mostrarInformacion() {
        return "Código: " + getCodigo() + ", Nombre: " + getNombre() + ", DNI: " + getDni() + ", Teléfono: " + getTelefono();
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
