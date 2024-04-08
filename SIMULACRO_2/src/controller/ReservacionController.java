package controller;

import entity.Avion;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.ReservacionModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ReservacionController {

    public static void update(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());

        Reservacion reservaSeleccionada = (Reservacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la Reservacion a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        reservaSeleccionada.setFechaReservacion(JOptionPane.showInputDialog(null,"Ingresa la fecha de la Reservacion: YY-MM-DD",reservaSeleccionada.getFechaReservacion()));
        reservaSeleccionada.setAsiento(JOptionPane.showInputDialog(null,"Ingresa el asiento a Seleccionar",reservaSeleccionada.getAsiento()));

        Object[] optionsPasajeros = Utils.listaAarray(PasajeroController.instanciarModel().findAll());
        Object[] optionsVuelos = Utils.listaAarray(VueloController.instanciarModel().findAll());

        Pasajero pasajeroSeleccionado = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Selecciona el pasajero",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPasajeros,
                optionsPasajeros[0]
        );

        reservaSeleccionada.setId_Pasajero(pasajeroSeleccionado.getId());
        reservaSeleccionada.setObjPasajero(pasajeroSeleccionado);

        Vuelo vueloSeleccionado = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Selecciona el Vuelo",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsVuelos,
                optionsVuelos[0]
        );

        reservaSeleccionada.setId_vuelo(vueloSeleccionado.getId());
        reservaSeleccionada.setObjVuelo(vueloSeleccionado);


        instanciarModel().update(reservaSeleccionada);
    }

    public static void insert(){

            String fecha = JOptionPane.showInputDialog("Ingresa la fecha de la cita: YY-MM-DD");
            String asiento = JOptionPane.showInputDialog("Ingresa el asiento ");

            Object[] optionsPasajeros = Utils.listaAarray(PasajeroController.instanciarModel().findAll());
            Object[] optionsVuelos = Utils.listaAarray(VueloController.instanciarModel().findAll());



            Pasajero pasajeroSeleccionado = (Pasajero) JOptionPane.showInputDialog(
                    null,
                    "Selecciona el Pasajero",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsPasajeros,
                    optionsPasajeros[0]
            );

            Vuelo vueloSeleccionado = (Vuelo) JOptionPane.showInputDialog(
                    null,
                    "Selecciona el vuelo",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsVuelos,
                    optionsVuelos[0]
            );


            instanciarModel().insert(new Reservacion(fecha,asiento,pasajeroSeleccionado.getId(),pasajeroSeleccionado,vueloSeleccionado.getId(),vueloSeleccionado));
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Reservacion reservacionSeleccionada = (Reservacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la Reservacion a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanciarModel().delete(reservacionSeleccionada);

    }

    public static void getAll(){
        String listaString = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,listaString);
    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros \n";

        for(Object temp: list ){
            Reservacion obj = (Reservacion) temp;
            listaString += obj.toString() + "\n";
        }
        return listaString;
    }

    public static ReservacionModel instanciarModel(){
        return new ReservacionModel();
    }
}
