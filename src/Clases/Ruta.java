
package Clases;

public class Ruta {
    private int idRuta;
    private String lugarInicio;
    private String lugarDestino;
    private int duracionEstimada;

    // Constructor
    public Ruta(int idRuta, String lugarInicio, String lugarDestino, int duracionEstimada) {
        this.idRuta = idRuta;
        this.lugarInicio = lugarInicio;
        this.lugarDestino = lugarDestino;
        this.duracionEstimada = duracionEstimada;
    }

    // Métodos
    public void addRuta() {
        // Implementación
    }

    public void verRuta() {
        // Implementación
    }

    public void borrarRuta() {
        // Implementación
    }

    public void modificarRuta() {
        // Implementación
    }

    // Getters y Setters
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(String lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }
}
