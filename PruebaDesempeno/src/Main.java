import controller.CoderController;
import controller.ContratacionController;
import controller.VacanteController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option ="";
        do {
            option= JOptionPane.showInputDialog("""
                    Menu Principal:
                    1.Menu Coders
                    2.Menu Vacantes
                    3.Menu Contrataciones
                    4.Salir
                    Seleccione una opcion:
                    """);
            switch (option){
                case "1":
                    String option1 = "";
                    do {
                        option1= JOptionPane.showInputDialog("""
                            Menu: \n
                            1.Mostrar Coders
                            2.Ingresar nuevo Coder
                            3.Actualizar informacion Coder
                            4.Eliminar Coder
                            5.Salir
                            Seleccione una opcion: 
                            """);
                        switch (option1){
                            case "1":
                                String option4 ="";
                                do {
                                    option4= JOptionPane.showInputDialog("""
                                    Menu: \n
                                    1.Mostrar todos los Coders
                                    2.Mostrar Coder por clan
                                    3.Mostrar Coder por tecnologia
                                    4.Salir
                                    Seleccione una opcion: 
                                    """);
                                    switch (option4){
                                        case "1":
                                            CoderController.getAll();
                                        break;
                                        case "2":
                                            CoderController.getByClan();
                                        break;
                                        case "3":
                                            CoderController.getByTecno();
                                        break;
                                    }
                                }while (!option4.equals("4"));
                            break;
                            case "2":
                                CoderController.create();
                            break;
                            case "3":
                                CoderController.update();
                            break;
                            case "4":
                                CoderController.delete();
                            break;
                        }
                    }while (!option1.equals("5"));
                break;
                case "2":
                    String option2 = "";
                    do {
                        option2= JOptionPane.showInputDialog("""
                            Menu: \n
                            1.Mostrar Vacantes
                            2.Ingresar nueva Vacante
                            3.Actualizar informacion de una Vacante
                            4.Eliminar Vacante
                            5.Salir
                            Seleccione una opcion: 
                            """);
                        switch (option2){
                            case "1":
                                String option1_2="";
                                do {
                                    option1_2= JOptionPane.showInputDialog("""
                                    Menu: \n
                                    1.Mostrar todas las vacantes
                                    2.Mostrar por titulo
                                    3.Mostrar por tecnologia
                                    4.Salir
                                    Seleccione una opcion: 
                                    """);
                                    switch (option1_2){
                                        case "1":
                                            VacanteController.getAll();
                                        break;
                                        case "2":
                                            VacanteController.getByTitle();
                                        break;
                                        case "3":
                                            VacanteController.getByTecnology();
                                        break;
                                    }
                                }while (!option1_2.equals("4"));
                            break;
                            case "2":
                                VacanteController.create();
                            break;
                            case "3":
                                VacanteController.update();
                            break;
                            case "4":
                                VacanteController.delete();
                            break;
                        }
                    }while (!option2.equals("5"));
                break;
                case "3":
                    String option3 = "";
                    do {
                        option1= JOptionPane.showInputDialog("""
                            Menu: \n
                            1.Mostrar Contrataciones
                            2.Ingresar nueva Contratacion
                            3.Actualizar Contratacion
                            4.Eliminar Contratacion
                            5.Salir
                            Seleccione una opcion: 
                            """);
                        switch (option1){
                            case "1":
                                ContratacionController.getAll();
                            break;
                            case "2":
                                ContratacionController.create();
                            break;
                            case "3":
                                ContratacionController.update();
                            break;
                            case "4":
                                ContratacionController.delete();
                            break;
                            case "5":

                                break;
                        }
                    }while (!option1.equals("5"));
                break;
            }
        }while (!option.equals("4"));
    }
}