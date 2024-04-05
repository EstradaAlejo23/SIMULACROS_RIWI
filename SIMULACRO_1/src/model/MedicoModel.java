package model;

import database.CRUD;
import database.ConfigDb;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();

        Medico objMedico = (Medico) obj;

        try{
            String sql = "INSERT INTO medico (nombre,apellidos,id_especialidad) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellido());
            objPrepare.setInt(3,objMedico.getFk_id_especialidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objMedico.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");

        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }
        ConfigDb.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaMedicos = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try{
            String sql = "SELECT * FROM medico\n" +
                    "INNER JOIN especialidad ON especialidad.id_especialidad = medico.id_especialidad;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//devuelve todos los registro de la base de datos

            while(objResult.next()){
                Medico objMedico = new Medico();
                Especialidad objEspecialidad = new Especialidad();

                objMedico.setId(objResult.getInt("medico.id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellido(objResult.getString("medico.apellidos"));
                objMedico.setFk_id_especialidad(objResult.getInt("medico.id_especialidad"));

                objEspecialidad.setId(objResult.getInt("especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));


                objMedico.setObjEspecialidad(objEspecialidad);

                listaMedicos.add(objMedico);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

        ConfigDb.closeConnection();
        return listaMedicos;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Medico objMedico = (Medico) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE medico SET nombre =?, apellidos =?, id_especialidad =? WHERE id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellido());
            objPrepare.setInt(3,objMedico.getObjEspecialidad().getId());
            objPrepare.setInt(4,objMedico.getId());


            int totalRowAfected = objPrepare.executeUpdate();

            if(totalRowAfected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Registro actualizado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDb.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Medico objMedico = (Medico) obj;
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM medico WHERE id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objMedico.getId());

            int totalRowAfected = objPrepare.executeUpdate();

            if(totalRowAfected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }

        ConfigDb.closeConnection();
        return isDeleted;
    }
}
