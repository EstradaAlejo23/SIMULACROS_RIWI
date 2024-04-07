package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;

        try{
            String sql = "INSERT INTO pasajero (nombre,apellido,documento_identidad) VALUE (?,?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//devuelve la id que es autoincrementable

            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellidos());
            objPrepare.setString(3,objPasajero.getDocumentoIdentidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPasajero.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Pasajero ingresada Correctamente");


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaPasajeros = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM pasajero;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//executeQuery devuelve todos los registros de la base de datos

            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId(objResult.getInt("id"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellido"));
                objPasajero.setDocumentoIdentidad((objResult.getString("documento_identidad")));

                listaPasajeros.add(objPasajero);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaPasajeros;

    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellidos());
            objPrepare.setString(3,objPasajero.getDocumentoIdentidad());
            objPrepare.setInt(4,objPasajero.getId());

            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El registro fue actualizado correctamente");

            }

        }catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = (Pasajero) obj;

        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM pasajero WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objPasajero.getId());

            int totalAffectedRow = objPrepare.executeUpdate();// executeUpdate devuelve un numero de filas afectadas

            if (totalAffectedRow>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente "+objPasajero.toString());
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isDeleted;
    }
}
