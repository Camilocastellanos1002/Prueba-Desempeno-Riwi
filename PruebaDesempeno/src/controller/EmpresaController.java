package controller;

import entity.Empresa;
import model.EmpresaModel;

import javax.swing.*;
import java.util.List;

public class EmpresaController {

    //metodo para instanciar el modelo de empresa
    public static EmpresaModel instanciaModeloEmpresa(){
        return new EmpresaModel();
    }
    //motodo para crear lista de empresas
    public static String getAll(List<Object> list ){
        String listEmpresa = "Lista de citas: \n";
        for (Object i: list){
            Empresa objEmpresa = (Empresa) i;
            listEmpresa+=objEmpresa.toString()+"\n";
        }
        return listEmpresa;
    }
    //metodo para imprimir lista
    public static void getAll(){

        //llamar metodo getAll para obtener lista, instanciando el modelo de empresa con el metodo findEmpresa
        String listEmpresa = getAll(instanciaModeloEmpresa().findEmpresa());
        JOptionPane.showMessageDialog(null,listEmpresa);
    }

}
