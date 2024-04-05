package model;

import database.CRUD;
import database.ConfigDb;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Cita objCita = (Cita) obj;

        try{
            String sql = "INSERT INTO cita (fecha_cita,hora_cita,motivo,id_paciente,id_medico) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1, Date.valueOf(objCita.getFehcaCita()));
            objPrepare.setTime(2, Time.valueOf(objCita.getHoraCita()));
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getFk_id_paciente());
            objPrepare.setInt(5,objCita.getFk_id_medico());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objCita.setId(objResult.getInt(1));

            }
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");


        }catch(SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDb.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listaCitas = new ArrayList<>();

        try{
            String sql = "SELECT * FROM cita\n" +
                    "INNER JOIN paciente ON paciente.id_paciente = cita.id_paciente\n" +
                    "INNER JOIN medico ON medico.id_medico = cita.id_medico;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Cita objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setId(objResult.getInt("cita.id_cita"));
                objCita.setFechaCita(objResult.getString("cita.fecha_cita"));
                objCita.setHoraCita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setFk_id_paciente(objResult.getInt("cita.id_paciente"));
                objCita.setFk_id_medico(objResult.getInt("cita.id_medico"));

                objMedico.setNombre(objResult.getString("medico.nombre"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objCita.setObjmedico(objMedico);
                objCita.setObjpaciente(objPaciente);

                listaCitas.add(objCita);


            }
        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDb.closeConnection();
        return listaCitas;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Cita objCita = (Cita) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE cita SET fecha_cita = ?,hora_cita = ?,motivo =?, id_paciente = ?, id_medico = ? WHERE id_cita = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDate(1, Date.valueOf(objCita.getFehcaCita()));
            objPrepare.setTime(2,Time.valueOf(objCita.getHoraCita()));
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getFk_id_paciente());
            objPrepare.setInt(5,objCita.getFk_id_medico());
            objPrepare.setInt(6,objCita.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro Actulizado correctamente");
            }


        }catch (SQLException e){
            System.out.println("ERROR " + e.getMessage());
        }

        ConfigDb.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Cita objCita = (Cita) obj;
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM cita WHERE id_cita = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCita.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }

        ConfigDb.closeConnection();
        return isDeleted;
    }
}
