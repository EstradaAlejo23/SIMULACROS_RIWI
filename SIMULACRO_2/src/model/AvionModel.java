package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;

        try{
            String sql = "INSERT INTO avion (modelo,capacidad) VALUE (?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//devuelve la id que es autoincrementable

            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAvion.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Avion ingresado Correctamente");


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return objAvion;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listaAviones= new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM avion;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//executeQuery devuelve todos los registros de la base de datos

            while (objResult.next()){
                Avion objAvion = new Avion();

                objAvion.setId(objResult.getInt("id"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));

                listaAviones.add(objAvion);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaAviones;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE avion SET modelo = ?, capacidad = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());
            objPrepare.setInt(3,objAvion.getId());

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

        Avion objAvion = (Avion) obj;

        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM avion WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objAvion.getId());
            int totalAffectedRow = objPrepare.executeUpdate();// executeUpdate devuelve un numero de filas afectadas

            if (totalAffectedRow>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente "+objAvion.toString());
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isDeleted;
    }


}
