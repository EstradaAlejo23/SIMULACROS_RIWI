package controller;

import entity.Paciente;
import model.PacientesModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PacienteController {

    public static void update(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Elige el paciente a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        pacienteSeleccionado.setNombre(JOptionPane.showInputDialog(null,"Ingrese un nuevo nombre",pacienteSeleccionado.getNombre()));
        pacienteSeleccionado.setApellido(JOptionPane.showInputDialog(null,"Ingrese un nuevo Apellido",pacienteSeleccionado.getApellido()));
        pacienteSeleccionado.setFechaNacimiento(JOptionPane.showInputDialog(null,"Ingrese un nueva fecha de nacimiento",pacienteSeleccionado.getFechaNacimiento()));
        pacienteSeleccionado.setDocumentoIdentidad(JOptionPane.showInputDialog(null,"Ingrese un nuevo documento de identidad",pacienteSeleccionado.getDocumentoIdentidad()));

        instanciarModel().update(pacienteSeleccionado);
    }

    public static void deleted(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Elige el paciente a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanciarModel().delete(pacienteSeleccionado);
    }
    public static void getAll(){
        String list =  getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros \n";

        for(Object temp: list){
            Paciente objPaciente = (Paciente) temp;
            listaString += objPaciente.toString() + "\n";
        }
        return listaString;
    }

    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingresa los apellidos del paciente");
        String fechaNacimiento = JOptionPane.showInputDialog("Ingresa la fecha del paciente YY-MM-DD");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingresa el documento del paciente");

        instanciarModel().insert(new Paciente(nombre,apellidos,fechaNacimiento,documentoIdentidad));
    }

    public static PacientesModel instanciarModel(){
        return new PacientesModel();
    }
}
