package entity;

public class Vuelo {
    private int id;
    private String destino;
    private String horaSalida;
    private String fechaSalida;
    private int id_avion;

    private Avion objAvion;

    public Vuelo() {
    }

    public Vuelo(String destino, String horaSalida, String fechaSalida, int id_avion, Avion objAvion) {
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.fechaSalida = fechaSalida;
        this.id_avion = id_avion;
        this.objAvion = objAvion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public Avion getObjAvion() {
        return objAvion;
    }

    public void setObjAvion(Avion objAvion) {
        this.objAvion = objAvion;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "destino='" + destino + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                "   " + objAvion +
                '}';
    }
}
