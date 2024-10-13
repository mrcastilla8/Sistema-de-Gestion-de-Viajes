package Clases;

import java.io.Serializable;

public class Comprobante implements Serializable {
    private int id_comprobante;
    private int idRuta;
    private int idBus;
    private String fechaEmision;
    private double precio;
    private String agencia;

    // Constructor
    public Comprobante(int id_comprobante, int idRuta, int idBus, String fechaEmision, double precio, String agencia) {
        this.id_comprobante = id_comprobante;
        this.idRuta = idRuta;
        this.idBus = idBus;
        this.fechaEmision = fechaEmision;
        this.precio = precio;
        this.agencia = agencia;
    }

    // Getters and Setters
    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
}
