package controller;

import entity.Avion;
import entity.Vuelo;
import model.VueloModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class VueloController {
    public static void getAll(){
        String list = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros \n";

        for(Object temp : list){
            Vuelo objVuelo = (Vuelo) temp;
            listaString += objVuelo.toString() + "\n";
        }

        return listaString;
    }

    public static void deleted(){
        Object[] opciones = Utils.listaAarray(instanciarModel().findAll());
        Vuelo objVuelo = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el vuelo a eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        instanciarModel().delete(objVuelo);
    }

    public static void updated(){
        Object[] opciones = Utils.listaAarray(instanciarModel().findAll());
        Vuelo objVuelo = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el Vuelo a actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        objVuelo.setDestino(JOptionPane.showInputDialog(null,"Ingresa el Destino del vuelo: ",objVuelo.getDestino()));
        objVuelo.setHoraSalida(JOptionPane.showInputDialog(null,"Ingresa la Hora de salida HH:mm:ss: ",objVuelo.getHoraSalida()));
        objVuelo.setFechaSalida(JOptionPane.showInputDialog(null,"Ingresa la Fecha de salida YY:MM:DD: ",objVuelo.getFechaSalida()));

        Object[] opcionesAvion = Utils.listaAarray(AvionController.instanciarModel().findAll());

        objVuelo.setObjAvion((Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione un Avion: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesAvion,
                opcionesAvion[0]
        ));

        instanciarModel().update(objVuelo);
    }

    public static void insert(){
        String destino = JOptionPane.showInputDialog("Ingresa el destino del vuelo: ");
        String fecha = JOptionPane.showInputDialog("Ingresa la fecha del Vuelo: YY-MM-DD");
        String hora = JOptionPane.showInputDialog("Ingresa la hora del Vuelo: HH:mm:ss");

        Object[] opcionesAvion = Utils.listaAarray(AvionController.instanciarModel().findAll());

        Avion objAvion = (Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione un Avion: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesAvion,
                opcionesAvion[0]
        );

        instanciarModel().insert(new Vuelo(destino,hora,fecha,objAvion.getId(),objAvion));

    }

    public static VueloModel instanciarModel(){
        return new VueloModel();
    }


}
