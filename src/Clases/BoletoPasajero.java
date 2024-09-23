
package Clases;


public class BoletoPasajero extends Comprobante {
    private String nombrePasajero;
    private String fechaViaje;
    private String numeroAsiento;
//Default
    public BoletoPasajero() {
    }
//Constructor
    public BoletoPasajero(String nombrePasajero, String fechaViaje, String numeroAsiento, int id_comprobante, int idRuta, int idBus, String fechaEmision, double precio, String agencia) {
        super(id_comprobante, idRuta, idBus, fechaEmision, precio, agencia);
        this.nombrePasajero = nombrePasajero;
        this.fechaViaje = fechaViaje;
        this.numeroAsiento = numeroAsiento;
    }

    //Getters and Setters

    
    
    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }
    
    
}
