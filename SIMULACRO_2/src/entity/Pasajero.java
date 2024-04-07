package entity;

public class Pasajero {
    private int id;
    private String nombre;
    private String apellidos;
    private String documentoIdentidad;

    public Pasajero() {
    }

    public Pasajero(String nombre, String apellidos, String documentoIdentidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documentoIdentidad = documentoIdentidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                '}';
    }
}
