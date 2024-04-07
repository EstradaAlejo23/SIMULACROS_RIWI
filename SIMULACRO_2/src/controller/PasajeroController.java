package controller;

import entity.Pasajero;
import model.PasajeroModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PasajeroController {

    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingrese el documento de identidad");


        instanciarModel().insert(new Pasajero(nombre,apellidos,documentoIdentidad));
    }

    public static void getAll(){
        String list = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);

    }

    public static String getAll(List<Object> list){

        String listaString = "LISTA DE REGISTROS: \n";

        for(Object temp : list){
            Pasajero objPasajero = (Pasajero) temp;
            listaString += objPasajero.toString() + "\n";
        }

        return listaString;
    }
    public static PasajeroModel instanciarModel(){
        return new PasajeroModel();
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());

        Pasajero objSeleccionar = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Selecciona una Pasajero",
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
        Pasajero objSeleccionar = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Selecciona un Pasajero para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSeleccionar.setNombre(JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre",objSeleccionar.getNombre()));
        objSeleccionar.setApellidos(JOptionPane.showInputDialog(null,"Ingresa los nuevos apellidos",objSeleccionar.getApellidos()));
        objSeleccionar.setDocumentoIdentidad(JOptionPane.showInputDialog(null,"Ingresa El nuevo Documento de Identidad",objSeleccionar.getDocumentoIdentidad()));


        instanciarModel().update(objSeleccionar);

    }
}
