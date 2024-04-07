package controller;

import entity.Avion;
import model.AvionModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AvionController {
    public static void insert() {
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del avion");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del avion"));

        instanciarModel().insert(new Avion(modelo,capacidad));

    }

    public static void getAll(){
        String list = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);

    }

    public static String getAll(List<Object> list){

        String listaString = "LISTA DE REGISTROS: \n";

        for(Object temp : list){
            Avion objAvion = (Avion) temp;
            listaString += objAvion.toString() + "\n";
        }

        return listaString;
    }

    public static AvionModel instanciarModel(){
        return new AvionModel();
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());

        Avion objSeleccionar = (Avion) JOptionPane.showInputDialog(
                null,
                "Selecciona una Avion",
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
        Avion objSeleccionar = (Avion) JOptionPane.showInputDialog(
                null,
                "Selecciona un avion a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSeleccionar.setModelo(JOptionPane.showInputDialog(null,"Ingresa el nuevo modelo",objSeleccionar.getModelo()));
        objSeleccionar.setCapacidad(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la nueva capacidad",objSeleccionar.getCapacidad())));

        instanciarModel().update(objSeleccionar);

    }
}
