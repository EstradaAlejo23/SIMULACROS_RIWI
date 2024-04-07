package entity;

public class Reservacion {
    private int id;
    private String fechaReservacion;
    private String asiento;
    private int id_Pasajero;
    private Pasajero objPasajero;
    private int id_vuelo;
    private Vuelo objVuelo;

    public Reservacion() {
    }

    public Reservacion(String fechaReservacion, String asiento, int id_Pasajero, Pasajero objPasajero, int id_vuelo, Vuelo objVuelo) {
        this.fechaReservacion = fechaReservacion;
        this.asiento = asiento;
        this.id_Pasajero = id_Pasajero;
        this.objPasajero = objPasajero;
        this.id_vuelo = id_vuelo;
        this.objVuelo = objVuelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public int getId_Pasajero() {
        return id_Pasajero;
    }

    public void setId_Pasajero(int id_Pasajero) {
        this.id_Pasajero = id_Pasajero;
    }

    public Pasajero getObjPasajero() {
        return objPasajero;
    }

    public void setObjPasajero(Pasajero objPasajero) {
        this.objPasajero = objPasajero;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public Vuelo getObjVuelo() {
        return objVuelo;
    }

    public void setObjVuelo(Vuelo objVuelo) {
        this.objVuelo = objVuelo;
    }

    @Override
    public String toString() {
        return "Reservacion{" +
                "fechaReservacion=" + fechaReservacion +
                ", asiento=" + asiento +
                ", Nombre Pasajero=" + objPasajero.getNombre() +
                ", Destino Vuelo=" + objVuelo.getDestino() +
                '}';
    }
}
