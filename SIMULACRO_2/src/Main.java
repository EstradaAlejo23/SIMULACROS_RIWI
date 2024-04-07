import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;
import database.ConfigDB;
import entity.Pasajero;
import entity.Vuelo;

import javax.swing.*;
import java.io.ObjectInputFilter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int option =0, option2 = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Aviones
                    2.Administrar Vuelos
                    3. Administrar Pasajeros
                    4. Administrar Reservaciones
                    5.Salir
                    
                    Ingrese una opcion:
                    """));

            switch (option){
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Aviones
                                2. Crear Avion
                                3. Eliminar Avion
                                4. Actualizar Avion
                                5.Salir
                    
                                Ingrese una opcion:
                                """));

                        switch (option2){
                            case 1:
                                AvionController.getAll();
                                break;
                            case 2:
                                AvionController.insert();
                                break;
                            case 3:
                                AvionController.delete();
                                break;
                            case 4:
                                AvionController.updated();
                                break;
                        }
                    }while (option2 != 5);

                    break;

                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Vuelos
                                2. Crear Vuelo
                                3. Eliminar Vuelo
                                4. Actualizar Vuelo
                                5.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                VueloController.getAll();
                                break;
                            case 2:
                                VueloController.insert();
                                break;
                            case 3:
                                VueloController.deleted();
                                break;
                            case 4:
                                VueloController.updated();
                                break;
                        }

                    }while(option2 !=5);
                    break;

                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Pasajeros
                                2. Crear Pasajero
                                3. Eliminar Pasajero
                                4. Actualizar Pasajero
                                5.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                PasajeroController.getAll();
                                break;
                            case 2:
                                PasajeroController.insert();
                                break;
                            case 3:
                                PasajeroController.delete();
                                break;
                            case 4:
                                PasajeroController.updated();
                                break;
                        }
                    }while(option2 != 5);
                    break;

                case 4:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Reservaciones
                                2. Crear Reservaciones
                                3. Eliminar Reservacion
                                4. Actualizar Reservacion
                                5.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                ReservacionController.getAll();
                                break;
                            case 2:
                                ReservacionController.insert();
                                break;
                            case 3:
                                ReservacionController.delete();
                                break;
                            case 4:
                                ReservacionController.update();
                                break;
                        }
                    }while(option2 != 5);
                    break;
            }
        }while (option != 5);
    }
}