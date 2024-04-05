package entity;

public class Cita {
    private int id;

    private String fechaCita;
    private String horaCita;
    private String motivo;

    private int fk_id_paciente;
    private Paciente objpaciente;

    private int fk_id_medico;
    private Medico objmedico;

    public Cita() {
    }

    public Cita( String fehcaCita, String horaCita, String motivo, int fk_id_paciente, Paciente paciente, int fk_id_medico, Medico medico) {
        this.fechaCita = fehcaCita;
        this.horaCita = horaCita;
        this.motivo = motivo;
        this.fk_id_paciente = fk_id_paciente;
        this.objpaciente = paciente;
        this.fk_id_medico = fk_id_medico;
        this.objmedico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFehcaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fehcaCita) {
        this.fechaCita = fehcaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getFk_id_paciente() {
        return fk_id_paciente;
    }

    public void setFk_id_paciente(int fk_id_paciente) {
        this.fk_id_paciente = fk_id_paciente;
    }

    public Paciente getObjpaciente() {
        return objpaciente;
    }

    public void setObjpaciente(Paciente objpaciente) {
        this.objpaciente = objpaciente;
    }

    public int getFk_id_medico() {
        return fk_id_medico;
    }

    public void setFk_id_medico(int fk_id_medico) {
        this.fk_id_medico = fk_id_medico;
    }

    public Medico getobjMedico() {
        return objmedico;
    }

    public void setObjmedico(Medico objmedico) {
        this.objmedico = objmedico;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "  fehcaCita='" + fechaCita + '\'' +
                ", horaCita='" + horaCita + '\'' +
                ", motivo='" + motivo + '\'' +
                ", paciente=" + objpaciente.getNombre() +
                ", medico=" + objmedico.getNombre() +
                '}';
    }
}
