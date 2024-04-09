package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Vacante;
import model.ContratacionModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ContratacionController {

    public static ContratacionModel instanciaModeloContratacion(){
        return new ContratacionModel();
    }
    public static String getAll(List<Object> list ){
        String listContrataciones = "Lista de contrataciones: \n";
        for (Object i: list){
            Contratacion objContratacion = (Contratacion) i;
            listContrataciones+=objContratacion.toString()+"\n";
        }
        return listContrataciones;
    }

    public static void getAll(){

        //llamar metodo getAll para obtener lista, instanciando el modelo de cita con el metodo find all
        String listContrataciones = getAll(instanciaModeloContratacion().findAll());
        JOptionPane.showMessageDialog(null,listContrataciones);
    }

    public static void create(){

        Float salario = Float.parseFloat(JOptionPane.showInputDialog(null,"Ingrese el salario del contrato: "));
        //llamar instancia de la listar
        Object[] optionVacantes = Utils.listToArray(VacanteController.instanciarModeloVacante().findByActiveStatus());
        Object[] optionCoder = Utils.listToArray(CoderController.instanciarModeloCoder().findAll());

        //selector de pacientes
        Vacante vacanteSeleccionado = (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione la vacante: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionVacantes,
                optionVacantes[0]
        );
        //if (vacanteSeleccionado.getEstado() == "ACTIVO"){
            //selector de coder
            Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el coder para la contratacion: ",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionCoder,
                    optionCoder[0]
            );
            //creando la instancia de la cita
            instanciaModeloContratacion().create(new Contratacion(vacanteSeleccionado.getEstado(),salario,coderSeleccionado.getId(),coderSeleccionado,vacanteSeleccionado.getId(),vacanteSeleccionado));
            vacanteSeleccionado.setEstado("INACTIVO");
        //}

    }


    public static void delete(){

        Object [] optionsContratos = Utils.listToArray(instanciaModeloContratacion().findAll());
        Contratacion contratoSeleccionado = (Contratacion) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico asignado a la cita: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsContratos,
                optionsContratos[0]
        );
        instanciaModeloContratacion().delete(contratoSeleccionado);
    }

    public static void update(){

    }
}
