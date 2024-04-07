package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = (Vuelo) obj;

        try{
            String sql = "INSERT INTO vuelo (destino,fecha_salida,hora_salida,id_avion) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setString(2,objVuelo.getFechaSalida());
            objPrepare.setString(3,objVuelo.getHoraSalida());
            objPrepare.setInt(4,objVuelo.getId_avion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVuelo.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");

        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaVuelo = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM vuelo\n" +
                    "INNER JOIN avion ON avion.id = vuelo.id_avion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//devuelve todos los registro de la base de datos

            while(objResult.next()){
                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();

                objVuelo.setId(objResult.getInt("vuelo.id"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFechaSalida(objResult.getString("vuelo.fecha_salida"));
                objVuelo.setHoraSalida(objResult.getString("vuelo.hora_salida"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));

                objAvion.setId(objResult.getInt("avion.id"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));


                objVuelo.setObjAvion(objAvion);

                listaVuelo.add(objVuelo);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

        ConfigDB.closeConnection();
        return listaVuelo;
    }


    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE vuelo SET destino=?, fecha_salida =?, hora_salida =?, id_avion = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setString(2,objVuelo.getFechaSalida());
            objPrepare.setString(3,objVuelo.getHoraSalida());
            objPrepare.setInt(4,objVuelo.getObjAvion().getId());
            objPrepare.setInt(5,objVuelo.getId());

            int totalRowAfected = objPrepare.executeUpdate();

            if(totalRowAfected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Registro actualizado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM vuelo WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVuelo.getId());

            int totalRowAfected = objPrepare.executeUpdate();

            if(totalRowAfected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

}
