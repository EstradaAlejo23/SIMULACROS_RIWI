package controller;

import entity.Especialidad;
import model.EspecialidadModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class EspecialidadController {

    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la Especialidad");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la Especialidad");

        instanciarModel().insert(new Especialidad(nombre,descripcion));

    }

    public static void getAll(){
        String list = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);

    }

    public static String getAll(List<Object> list){

        String listaString = "LISTA DE REGISTROS: \n";

        for(Object temp : list){
            Especialidad objEspecialidad = (Especialidad) temp;
            listaString += objEspecialidad.toString() + "\n";
        }

        return listaString;
    }

    public static EspecialidadModel instanciarModel(){
        return new EspecialidadModel();
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());

        Especialidad objSeleccionar = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanciarModel().delete(objSeleccionar);
    }

    public static void updated(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Especialidad objSeleccionar = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSeleccionar.setNombre(JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre",objSeleccionar.getNombre()));
        objSeleccionar.setDescripcion(JOptionPane.showInputDialog(null,"Ingresa la nueva descripcion",objSeleccionar.getDescripcion()));

        instanciarModel().update(objSeleccionar);

    }
}
