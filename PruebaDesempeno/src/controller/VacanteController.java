package controller;

import entity.Empresa;
import entity.Vacante;
import model.VacanteModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class VacanteController {

    public static VacanteModel instanciarModeloVacante(){
        return new VacanteModel();
    }
    public static void create(){
        //1. perdir valores
        String titulo = JOptionPane.showInputDialog(null,"Ingrese el titulo de la vacante: ");
        String descripcion = JOptionPane.showInputDialog(null,"Ingrese descripcion de la vacante: ");
        String duracion = JOptionPane.showInputDialog(null,"Ingrese la duracion de la vacante: ");
        String tecnologia =JOptionPane.showInputDialog(null,"Ingrese la tecnologia que requiere la vacante: ");

        //2. listar empresas
        Object[] optionEmpresa = Utils.listToArray(EmpresaController.instanciaModeloEmpresa().findEmpresa());

        //3.selector
        Empresa objEmpresa =(Empresa) JOptionPane.showInputDialog(  //la opcion seleccionada es casteada y guardada como objeto
                null,   //no tiene parentcomponent
                "Seleccione una especialidad: ",    //mensaje
                "",                                 //no tiene titulo
                JOptionPane.QUESTION_MESSAGE,       //ventana de pregunto
                null,                               //no tiene ningun icono
                optionEmpresa,                 //opciones
                optionEmpresa[0]               //opcion por defecto
        );

        //3. crear el modelo de medico asignando el objeto medico con los valores asignados y nesesarios en el constructor
        //por defecto al crear una vacante el estado es ACTIVO

        instanciarModeloVacante().create(new Vacante(titulo,descripcion,duracion,"ACTIVO",tecnologia,objEmpresa.getId(),objEmpresa));
    }

    public static void getAll(){
        String listVacantes = getAll(instanciarModeloVacante().findAll());
        JOptionPane.showMessageDialog(null,listVacantes);
    }
    public static String getAll(List<Object> list){

        String listVacantes = "Lista de Vacantes: \n";
        for (Object i: list){
            Vacante objVacante = (Vacante) i;
            listVacantes+=objVacante.toString()+"\n";
        }
        return listVacantes;
    }

    public static void getByTitle(){
        String titulo = JOptionPane.showInputDialog(null,"Ingrese el titulo a buscar: ");
        VacanteModel objVacanteModel = new VacanteModel();

        String listString = "Coincidencias: \n";
        for (Vacante i:objVacanteModel.findByTitle(titulo)){
            listString+=i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static void getByTecnology(){
        String tecno = JOptionPane.showInputDialog(null,"Ingrese la tecnologia a buscar: ");
        VacanteModel objVacanteModel = new VacanteModel();

        String listString = "Coincidencias: \n";
        for (Vacante i:objVacanteModel.findByTecnology(tecno)){
            listString+=i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static void getActiveStatus(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listString = "Coincidencias: \n";
        for (Vacante i:objVacanteModel.findByActiveStatus()){
            listString+=i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static void getInActiveStatus(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listString = "Coincidencias: \n";
        for (Vacante i:objVacanteModel.findByInActiveStatus()){
            listString+=i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }

    public static void delete(){
        Object[] options = Utils.listToArray( instanciarModeloVacante().findAll());
        Vacante objVacante =(Vacante) JOptionPane.showInputDialog(  //la opcion seleccionada es casteada y guardada como objeto
                //especialidad
                null,   //no tiene parentcomponent
                "Seleccione el medico a eliminar: ",    //mensaje
                "",                                 //no tiene titulo
                JOptionPane.QUESTION_MESSAGE,       //ventana de pregunto
                null,                               //no tiene ningun icono
                options,                 //opciones
                options[0]               //opcion por defecto
        );
        instanciarModeloVacante().delete(objVacante);
    }

    public static void update(){
        //lista de vacantes para actualizar
        Object[] options = Utils.listToArray(instanciarModeloVacante().findAll());
        Vacante objVacante =(Vacante) JOptionPane.showInputDialog(  //la opcion seleccionada es casteada y guardada como objeto
                //especialidad
                null,   //no tiene parentcomponent
                "Seleccione la vacante que deseaa actualizar: ",    //mensaje
                "",                                 //no tiene titulo
                JOptionPane.QUESTION_MESSAGE,       //ventana de pregunto
                null,                               //no tiene ningun icono
                options,                 //opciones
                options[0]               //opcion por defecto
        );
        //valores actualizar
        String titulo = JOptionPane.showInputDialog(null,"Ingrese el nuevo titulo de la vacante: ",objVacante.getTitulo());
        String descripcion = JOptionPane.showInputDialog(null,"Ingrese la nueva descripcion de la vacante: ",objVacante.getDescripcion());
        String duracion = JOptionPane.showInputDialog(null,"Ingrese la nueva duracion de la vacante: ",objVacante.getDuracion());
        String estado = JOptionPane.showInputDialog(null,"Ingrese el nuevo estado de la vacante: ",objVacante.getEstado());
        String tecnologia = JOptionPane.showInputDialog(null,"Ingrese de nuevo la tecnologia requerida para la vacante: ",objVacante.getTecnologia());



        Object[] optionEmpresa = Utils.listToArray(EmpresaController.instanciaModeloEmpresa().findEmpresa());

        Empresa objEmpresa =(Empresa) JOptionPane.showInputDialog(  //la opcion seleccionada es casteada y guardada como objeto
                //especialidad
                null,   //no tiene parentcomponent
                "Seleccione de nuevo la empresa solicitante de la vacante: ",    //mensaje
                "",                                 //no tiene titulo
                JOptionPane.QUESTION_MESSAGE,       //ventana de pregunto
                null,                               //no tiene ningun icono
                optionEmpresa,                 //opciones
                optionEmpresa[0]               //opcion por defecto
        );
        instanciarModeloVacante().update(new Vacante(titulo,descripcion,duracion,estado,tecnologia,objEmpresa.getId(),objEmpresa));
    }

}
