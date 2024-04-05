package model;

import database.CRUD;
import database.ConfigDb;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD{
    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDb.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;

        try{
            String sql = "INSERT INTO especialidad (nombre,descripcion) VALUE (?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//devuelve la id que es autoincrementable

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objEspecialidad.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Especialidad ingresada Correctamente");


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDb.closeConnection();
        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listaEspecialidad = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try{
            String sql = "SELECT * FROM especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//executeQuery devuelve todos los registros de la base de datos

            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));

                listaEspecialidad.add(objEspecialidad);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDb.closeConnection();
        return listaEspecialidad;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El registro fue actualizado correctamente");

            }

        }catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        ConfigDb.closeConnection();
        return isUpdated;
    }


    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();

        Especialidad objEspecialidad = (Especialidad) obj;

        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objEspecialidad.getId());
            int totalAffectedRow = objPrepare.executeUpdate();// executeUpdate devuelve un numero de filas afectadas

            if (totalAffectedRow>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente "+objEspecialidad.toString());
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return isDeleted;
    }

}
