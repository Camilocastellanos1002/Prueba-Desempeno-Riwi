package model;

import database.ConfigDB;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel {
    public List<Object> findEmpresa() {
        //1. abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. crear lista de citas
        List<Object> listaEmpresas = new ArrayList<>();
        //3. try
        try {
            //4. sentencia sql
            String sql = "SELECT * FROM empresa";
            //5. crear statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. se ejecuta el query
            ResultSet objResult = objPrepare.executeQuery();
            //7. para cada registro de la tabla
            while (objResult.next()){
                //crear objeto que va a guardar la info
                Empresa objEmpresa = new Empresa();
                //asignar valores
                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                //guardar objeto a la lista
                listaEmpresas.add(objEmpresa);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al listar empresas: "+e.getMessage());
        }
        //cerrar conexion
        ConfigDB.closeConnection();
        //devolver lista
        return listaEmpresas;
    }

}
