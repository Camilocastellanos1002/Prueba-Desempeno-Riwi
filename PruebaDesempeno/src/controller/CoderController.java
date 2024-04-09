package controller;

import entity.Coder;
import model.CoderModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CoderController {

    //este metodo permite instanciar el modelo
    public  static CoderModel instanciarModeloCoder(){
        return new CoderModel();
    }
    public static void create(){
        //1. asignar valores por el usuario
        String nombre =JOptionPane.showInputDialog(null,"Ingrese nombre del coder: \n");
        String apellidos = JOptionPane.showInputDialog(null,"Ingrese apellidos del coder: \n");
        String documento =JOptionPane.showInputDialog(null,"Ingrese documento del coder: \n");
        String clan =JOptionPane.showInputDialog(null,"Ingrese clan del coder: \n");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese cohorte del coder: \n"));
        String cv =JOptionPane.showInputDialog(null,"Ingrese cv del coder: \n");

        //metodo que crea el objeto coder con los valores
        instanciarModeloCoder().create(new Coder(nombre,apellidos,documento,cohorte,cv,clan));
    }

    public static void getAll(){
        String listCoder = getAllString(instanciarModeloCoder().findAll());
        JOptionPane.showMessageDialog(null,listCoder);
    }

    public static String getAllString(List<Object> list){
        String listCoder = "Lista de coders: \n";
        for (Object i: list){
            Coder objEspecialidad = (Coder) i;
            listCoder+=objEspecialidad.toString()+"\n";
        }
        return listCoder;
    }

    public static void update(){
        //1. crear aaraylist de objetos para crear el select
        Object[] options = Utils.listToArray(instanciarModeloCoder().findAll());//entregando la creacion de la instancia del modelo
        //2. select
        Coder objSelected = (Coder) JOptionPane.showInputDialog(null,
                "Selecione el coder para actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        //3.pedir al usuario el ingreso de datos con los valores por defecto en caso de no ingresar nada
        objSelected.setNombre(JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre del coder: ", objSelected.getNombre()));
        objSelected.setApellidos(JOptionPane.showInputDialog(null,"Ingresa los nuevos apellidos del coder: ", objSelected.getApellidos()));
        objSelected.setDocumento(JOptionPane.showInputDialog(null,"Ingresa el nuevo documento del coder: ", objSelected.getDocumento()));
        objSelected.setClan(JOptionPane.showInputDialog(null,"Ingresa el nuevo clan del coder: ", objSelected.getClan()));
        objSelected.setCohorte(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la nueva corte del coder: ", objSelected.getCohorte())));
        objSelected.setCv(JOptionPane.showInputDialog(null,"Ingresa el nuevo archivo cv del coder: ", objSelected.getCv()));

        instanciarModeloCoder().update(objSelected);
    }
    public static void delete(){
        //crear aaraylist de objetos

        Object[] options = Utils.listToArray(instanciarModeloCoder().findAll());//entregando la creacion de la instancia del modelo

        Coder objSelected = (Coder) JOptionPane.showInputDialog(null,
                "Selecione el coder para eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instanciarModeloCoder().delete(objSelected);
    }
}
