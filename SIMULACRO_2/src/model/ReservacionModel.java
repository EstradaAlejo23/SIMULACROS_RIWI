package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;
        int contadorReservas = 0;
        int contadorCapacidad = objReservacion.getObjVuelo().getObjAvion().getCapacidad();
        String contadorAsiento = objReservacion.getAsiento();

        try{
            String sql = "SELECT * FROM reservacion INNER JOIN pasajero ON pasajero.id = reservacion.id_pasajero INNER JOIN vuelo ON vuelo.id = reservacion.id_vuelo;";
            PreparedStatement objPrepare1 = objConnection.prepareStatement(sql);
            ResultSet objResult1 = objPrepare1.executeQuery();

            while(objResult1.next()){
                contadorReservas++;
                System.out.println(contadorReservas);
            }
        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        if( contadorReservas < contadorCapacidad) {
            try {
                String sql1 = "INSERT INTO reservacion (fecha_reservacion,asiento,id_pasajero,id_vuelo) VALUES (?,?,?,?);";
                PreparedStatement objPrepare = objConnection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);

                objPrepare.setDate(1, Date.valueOf(objReservacion.getFechaReservacion()));
                objPrepare.setString(2, objReservacion.getAsiento());
                objPrepare.setInt(3, objReservacion.getId_Pasajero());
                objPrepare.setInt(4, objReservacion.getId_vuelo());

                objPrepare.execute();


                ResultSet objResult = objPrepare.getGeneratedKeys();

                while (objResult.next()) {
                    objReservacion.setId(objResult.getInt(1));

                }
                JOptionPane.showMessageDialog(null, "Registro insertado correctamente");


            } catch (SQLException e) {
                System.out.println("ERROR: " + e);
            }
            ConfigDB.closeConnection();
            return objReservacion;
        }else{
            JOptionPane.showMessageDialog(null,"Las reservas superan la capacidad del avion");
        }
        return null;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaReservas = new ArrayList<>();

        try{
            String sql = "SELECT * FROM reservacion INNER JOIN pasajero ON pasajero.id = reservacion.id_pasajero INNER JOIN vuelo ON vuelo.id = reservacion.id_vuelo;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Reservacion objReservacion = new Reservacion();
                Pasajero objPasajero = new Pasajero();
                Vuelo objVuelo = new Vuelo();

                objReservacion.setId(objResult.getInt("reservacion.id"));
                objReservacion.setFechaReservacion(objResult.getString("reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("reservacion.asiento"));
                objReservacion.setId_Pasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));

                objPasajero.setNombre(objResult.getString("pasajero.nombre"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objReservacion.setObjPasajero(objPasajero);
                objReservacion.setObjVuelo(objVuelo);

                listaReservas.add(objReservacion);


            }
        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDB.closeConnection();
        return listaReservas;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE reservacion SET fecha_reservacion = ?,asiento = ?, id_pasajero = ?, id_vuelo = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDate(1, Date.valueOf(objReservacion.getFechaReservacion()));
            objPrepare.setString(2,objReservacion.getAsiento());
            objPrepare.setInt(3,objReservacion.getId_Pasajero());
            objPrepare.setInt(4,objReservacion.getId_vuelo());
            objPrepare.setInt(5,objReservacion.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro Actulizado correctamente");
            }


        }catch (SQLException e){
            System.out.println("ERROR " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM reservacion WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objReservacion.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }
}
