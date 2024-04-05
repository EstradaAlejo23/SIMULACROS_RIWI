package model;

import database.CRUD;
import database.ConfigDb;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacientesModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Paciente objPaciente = (Paciente) obj;

        try {
            String sql = "INSERT INTO paciente (nombre,apellidos,fecha_nacimiento,documento_identidad) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellido());
            objPrepare.setDate(3, Date.valueOf(objPaciente.getFechaNacimiento()));
            objPrepare.setString(4,objPaciente.getDocumentoIdentidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objPaciente.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Registro Exitoso");

        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }
        ConfigDb.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listaPacientes = new ArrayList<>();

        try{
            String sql = "SELECT * FROM paciente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellido(objResult.getString("apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));

                listaPacientes.add(objPaciente);
            }
        }catch (SQLException e){
            System.out.println("Error: "+ e);
        }
        ConfigDb.closeConnection();
        return listaPacientes;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Paciente objPaciente = (Paciente) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE paciente SET nombre =?,apellidos = ?, fecha_nacimiento = ?,documento_identidad =? WHERE id_paciente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellido());
            objPrepare.setDate(3,Date.valueOf(objPaciente.getFechaNacimiento()));
            objPrepare.setString(4,objPaciente.getDocumentoIdentidad());
            objPrepare.setInt(5,objPaciente.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if(totalRowsAffected > 0){
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

        Connection objConneccion = ConfigDb.openConnection();
        Paciente objPaciente = (Paciente) obj;
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPrepare = objConneccion.prepareStatement(sql);

            objPrepare.setInt(1,objPaciente.getId());

            int totalRowAfected = objPrepare.executeUpdate();

            if(totalRowAfected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro Eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("Error: "+e);
        }

        ConfigDb.closeConnection();
        return isDeleted;
    }
}
