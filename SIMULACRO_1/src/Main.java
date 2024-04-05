import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import database.ConfigDb;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int option =0, option2 = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Especialidades
                    2.Administrar Medicos
                    3. Administrar Pacientes
                    4. Administrar Citas
                    5.Salir
                    
                    Ingrese una opcion:
                    """));

            switch (option){
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Especialidad
                                2. Crear Especialidad
                                3. Eliminar Especilidad
                                4. Actualizar Especialidad
                                5.Salir
                    
                                Ingrese una opcion:
                                """));

                        switch (option2){
                            case 1:
                                EspecialidadController.getAll();
                                break;
                            case 2:
                                EspecialidadController.insert();
                                break;
                            case 3:
                                EspecialidadController.delete();
                                break;
                            case 4:
                                EspecialidadController.updated();
                                break;
                        }
                    }while (option2 != 5);

                    break;

                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Medicos
                                2. Crear Medico
                                3. Eliminar Medico
                                4. Actualizar Medico
                                5.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                MedicoController.getAll();
                                break;
                            case 2:
                                MedicoController.insert();
                                break;
                            case 3:
                                MedicoController.deleted();
                                break;
                            case 4:
                                MedicoController.updated();

                                break;
                        }

                    }while(option2 !=5);
                    break;

                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Pacientes
                                2. Crear Paciente
                                3. Eliminar Paciente
                                4. Actualizar Paciente
                                5.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                PacienteController.getAll();
                                break;
                            case 2:
                                PacienteController.insert();
                                break;
                            case 3:
                                PacienteController.deleted();
                                break;
                            case 4:
                                PacienteController.update();
                                break;
                        }
                    }while(option2 != 5);
                    break;

                case 4:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Cita
                                2. Crear Cita
                                3. Eliminar Cita
                                4. Actualizar Cita
                                5.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                CitaController.getAll();
                                break;
                            case 2:
                                CitaController.insert();
                                break;
                            case 3:
                                CitaController.delete();
                                break;
                            case 4:
                                CitaController.update();
                                break;
                        }
                    }while(option2 != 5);
                    break;
            }
        }while (option != 5);
    }
}