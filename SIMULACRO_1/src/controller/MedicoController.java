package controller;

import entity.Especialidad;
import entity.Medico;
import model.MedicoModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class MedicoController {

    public static void getAll(){
        String list = getAll(instanciarModel().findAll());

        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros \n";

        for(Object temp : list){
            Medico objMedico = (Medico) temp;
            listaString += objMedico.toString() + "\n";
        }

        return listaString;
    }

    public static void deleted(){
        Object[] opciones = Utils.listaAarray(instanciarModel().findAll());
        Medico objMedico = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico a eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        instanciarModel().delete(objMedico);
    }

    public static void updated(){
        Object[] opciones = Utils.listaAarray(instanciarModel().findAll());
        Medico objMedico = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico a actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        objMedico.setNombre(JOptionPane.showInputDialog(null,"Ingresa el nombre dle medico: ",objMedico.getNombre()));
        objMedico.setApellido(JOptionPane.showInputDialog(null,"Ingresa el apellido del medico",objMedico.getApellido()));

        Object[] opcionesEspecialidades = Utils.listaAarray(EspecialidadController.instanciarModel().findAll());

        objMedico.setObjEspecialidad((Especialidad) JOptionPane.showInputDialog(
                null,
                "Seleccione una especialidad: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEspecialidades,
                opcionesEspecialidades[0]
        ));

        instanciarModel().update(objMedico);
    }

    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del medico: ");
        String apellido = JOptionPane.showInputDialog(("Ingresa el apellido del medico"));

        Object[] opcionesEspecialidades = Utils.listaAarray(EspecialidadController.instanciarModel().findAll());

        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Seleccione una especialidad: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEspecialidades,
                opcionesEspecialidades[0]
        );

        instanciarModel().insert(new Medico(nombre,apellido,objEspecialidad.getId(),objEspecialidad));

    }

    public static MedicoModel instanciarModel(){
        return new MedicoModel();
    }
}
