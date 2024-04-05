package controller;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CitaController {

    public static void update(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());

        Cita citaSeleccionada = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        citaSeleccionada.setFechaCita(JOptionPane.showInputDialog(null,"Ingresa la fecha de la cita: YY-MM-DD",citaSeleccionada.getFehcaCita()));
        citaSeleccionada.setHoraCita(JOptionPane.showInputDialog(null,"Ingresa la hora de la cita: HH:mm:ss",citaSeleccionada.getHoraCita()));
        citaSeleccionada.setMotivo(JOptionPane.showInputDialog(null,"Ingresa el motivo de la cita",citaSeleccionada.getMotivo()));

        Object[] optionsPacientes = Utils.listaAarray(PacienteController.instanciarModel().findAll());
        Object[] optionsMedicos = Utils.listaAarray(MedicoController.instanciarModel().findAll());

        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Selecciona el paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPacientes,
                optionsPacientes[0]
        );

        citaSeleccionada.setFk_id_paciente(pacienteSeleccionado.getId());
        citaSeleccionada.setObjpaciente(pacienteSeleccionado);

        Medico medicoSeleccionado = (Medico) JOptionPane.showInputDialog(
                null,
                "Selecciona el Medico",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsMedicos,
                optionsMedicos[0]
        );

        citaSeleccionada.setFk_id_medico(medicoSeleccionado.getId());
        citaSeleccionada.setObjmedico(medicoSeleccionado);


        instanciarModel().update(citaSeleccionada);


    }
    public static void insert(){
        String fecha = JOptionPane.showInputDialog("Ingresa la fecha de la cita: YY-MM-DD");
        String hora = JOptionPane.showInputDialog("Ingresa la hora de la cita: HH:mm:ss");
        String motivo = JOptionPane.showInputDialog("Ingresa el motivo de la cita");

        Object[] optionsPacientes = Utils.listaAarray(PacienteController.instanciarModel().findAll());
        Object[] optionsMedicos = Utils.listaAarray(MedicoController.instanciarModel().findAll());

        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Selecciona el paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPacientes,
                optionsPacientes[0]
        );

        Medico medicoSeleccionado = (Medico) JOptionPane.showInputDialog(
                null,
                "Selecciona el Medico",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsMedicos,
                optionsMedicos[0]
        );

        instanciarModel().insert(new Cita(fecha,hora,motivo,pacienteSeleccionado.getId(),pacienteSeleccionado,medicoSeleccionado.getId(),medicoSeleccionado));
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Cita citaSeleccionada = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanciarModel().delete(citaSeleccionada);

    }

    public static void getAll(){
        String listaString = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,listaString);
    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros \n";

        for(Object temp: list ){
            Cita obj = (Cita) temp;
            listaString += obj.toString() + "\n";
        }
        return listaString;
    }

    public static CitaModel instanciarModel(){
        return new CitaModel();
    }
}
