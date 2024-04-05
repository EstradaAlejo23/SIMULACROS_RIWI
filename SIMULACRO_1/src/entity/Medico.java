package entity;

public class Medico {

    private int id;
    private String nombre;
    private String apellido;

    private int fk_id_especialidad;

    private Especialidad objEspecialidad;//inyeccion de dependencia, inyectar un objeto dentro de otra clase
    // para tener los datos de medico y de especialidad.

    public Medico() {
    }


    public Medico(String nombre, String apellido, int fk_id_especialidad, Especialidad ojbEspecialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fk_id_especialidad = fk_id_especialidad;
        this.objEspecialidad = objEspecialidad;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getFk_id_especialidad() {
        return fk_id_especialidad;
    }

    public void setFk_id_especialidad(int fk_id_especialidad) {
        this.fk_id_especialidad = fk_id_especialidad;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                "Especialidad: " + this.objEspecialidad.getNombre();
    }

    public String mostrar() {
        return "Medico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fk_id_especialidad=" + fk_id_especialidad +
                ", objEspecialidad=" + objEspecialidad +
                '}';
    }
}
