package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {

    public Object create(Object obj) {
        //1. abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto que viene como parametro
        Vacante objVacante = (Vacante) obj;
        //3. tod o puede fallar
        try {
            //4. sentencia sql
            String sql = "INSERT INTO vacante (titulo,descripcion,duracion,estado,empresa_id,tecnologia) VALUES (?,?,?,?,?,?);";
            //5.preparar el statement y que genere llaves
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6. dar valores al statemente
            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2,objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,objVacante.getEstado());
            objPrepare.setInt(5,objVacante.getEmpresa_id());
            objPrepare.setString(6,objVacante.getTecnologia());


            //7. ejecutar el query
            objPrepare.execute();
            //8. obtener los dalores de las llaves
            ResultSet objResult = objPrepare.getGeneratedKeys();
            //9. por registro
            while (objResult.next()){
                objVacante.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Vacante creada");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error creando vacante: "+e.getMessage());
        }
        //10. cerrar conexion
        ConfigDB.closeConnection();
        //11. devolver objeto
        return objVacante;
    }

    @Override
    public List<Object> findAll() {
        //1. lista de objetos
        List<Object>  listVacantes = new ArrayList<>();
        //2. abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //3. tod o puede fallar
        try {
            //4. sentencia sql
            //seleccionar la tabla vacante
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.empresa_id;"; //ingresa datos de la tabla empresa a la tabla vacante
            //5. crear preparestatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. obtener resultados y ejecutar query para devolver un objeto de la lista de los registros de la DB
            ResultSet objResult = objPrepare.executeQuery();
            //7. por cada registro
            while (objResult.next()){
                //crear objeto Vacante
                Vacante objVacante = new Vacante();
                //crear objeto Empresa
                Empresa objEmpresa = new Empresa();
                //llenar datos del objeto vacante
                objVacante.setId(objResult.getInt("vacante.id")); //al hacer un inner join especificar que tabla
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                //llenar datos del objeto Empresa
                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                //guardar los datos de empresa en vacante
                objVacante.setObjEmpresa(objEmpresa);
                //adicionar vacante a la lista
                listVacantes.add(objVacante);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al listar vacantes: "+e.getMessage());
        }
        //8. cerrar conexion
        ConfigDB.closeConnection();
        //9 devolver lista
        return listVacantes;
    }

    public List<Vacante> findByTitle(String title){
        List<Vacante> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa  ON empresa.id = vacante.empresa_id WHERE titulo like ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+title+"%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Vacante objVacante =new Vacante();
                //crear objeto Empresa
                Empresa objEmpresa = new Empresa();
                //llenar datos del objeto vacante
                objVacante.setId(objResult.getInt("vacante.id")); //al hacer un inner join especificar que tabla
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));

                //llenar datos del objeto Empresa
                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                //guardar los datos de empresa en vacante
                objVacante.setObjEmpresa(objEmpresa);
                //adicionar vacante a la lista
                listVacantes.add(objVacante);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al encontrar vacante por titulo: "+e.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }
    public List<Vacante> findByTecnology(String title){
        List<Vacante> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa  ON empresa.id = vacante.empresa_id WHERE tecnologia like ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+title+"%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Vacante objVacante =new Vacante();
                //crear objeto Empresa
                Empresa objEmpresa = new Empresa();
                //llenar datos del objeto vacante
                objVacante.setId(objResult.getInt("vacante.id")); //al hacer un inner join especificar que tabla
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));

                //llenar datos del objeto Empresa
                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                //guardar los datos de empresa en vacante
                objVacante.setObjEmpresa(objEmpresa);
                //adicionar vacante a la lista
                listVacantes.add(objVacante);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al encontrar vacante por tecnologia: "+e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacantes;
    }
    public List<Vacante> findByActiveStatus(){
        List<Vacante> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa  ON empresa.id = vacante.empresa_id WHERE estado= 'ACTIVO';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Vacante objVacante =new Vacante();
                //crear objeto Empresa
                Empresa objEmpresa = new Empresa();
                //llenar datos del objeto medico
                objVacante.setId(objResult.getInt("vacante.id")); //al hacer un inner join especificar que tabla
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));

                //llenar datos del objeto Empresa
                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                //guardar los datos de empresa en vacante
                objVacante.setObjEmpresa(objEmpresa);
                //adicionar vacante a la lista
                listVacantes.add(objVacante);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al encontrar vacante por estado: "+e.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }
    public List<Vacante> findByInActiveStatus(){
        List<Vacante> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa  ON empresa.id = vacante.empresa_id WHERE estado= 'INACTIVO';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Vacante objVacante =new Vacante();
                //crear objeto Empresa
                Empresa objEmpresa = new Empresa();
                //llenar datos del objeto medico
                objVacante.setId(objResult.getInt("vacante.id")); //al hacer un inner join especificar que tabla
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                //llenar datos del objeto Empresa
                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                //guardar los datos de empresa en vacante
                objVacante.setObjEmpresa(objEmpresa);
                //adicionar vacante a la lista
                listVacantes.add(objVacante);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al encontrar vacante por estado: "+e.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }

    @Override
    public boolean update(Object obj) {
        //abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //castear
        Vacante objVacante = (Vacante) obj;
        //bandera de estado
        boolean isUpdate = false;
        //try
        try {
            //sentencia sql
            String sql = "UPDATE vacante SET titulo=?, descripcion=?,duracion=?,estado=?,empresa_id=?,tecnologia=? WHERE id=?;";
            //preparamos statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //dar valores a los ?
            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2,objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,objVacante.getEstado());
            objPrepare.setInt(5,objVacante.getEmpresa_id());
            objPrepare.setString(6,objVacante.getTecnologia());
            objPrepare.setInt(7,objVacante.getId());

            //ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected>0){
                isUpdate= true;
                JOptionPane.showMessageDialog(null,"Registro actualizado correctamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al actualizar vacante: "+e.getMessage());
        }
        //cerrar conexion
        ConfigDB.closeConnection();
        //retornar
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        //1. abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. casting
        Vacante objVacante = (Vacante) obj;
        //3. bandera
        boolean isDeleted = false;
        //4.try
        try {
            //5.sentencia sql
            String sql = "DELETE FROM vacante WHERE vacante.id=? ;";
            //6. cracion del prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //7.darle valores al ?
            objPrepare.setInt(1,objVacante.getId());
            //8. ejecutar query con la devolucion de la cantidad de columnas afectadas
            int totalRowAffected = objPrepare.executeUpdate();
            //9. verificar
            if (totalRowAffected>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"vacante eliminado exitosamente");
            }
        }catch (Exception e){
            JOptionPane.showInputDialog(null,"Error al eliminar vacante: "+e.getMessage());
        }
        //10. cerrar conexion
        ConfigDB.closeConnection();
        //11 retornar bandera
        return isDeleted;
    }
    public boolean deleteInactiveStatus(Object obj) {
        //1. abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. casting
        Vacante objVacante = (Vacante) obj;
        //3. bandera
        boolean isDeleted = false;
        //4.try
        try {
            //5.sentencia sql
            String sql = "DELETE FROM vacante WHERE vacante.estado='INACTIVO' ;";
            //6. cracion del prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //8. ejecutar query con la devolucion de la cantidad de columnas afectadas
            int totalRowAffected = objPrepare.executeUpdate();
            //9. verificar
            if (totalRowAffected>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"vacante eliminado exitosamente");
            }
        }catch (Exception e){
            JOptionPane.showInputDialog(null,"Error al eliminar vacante: "+e.getMessage());
        }
        //10. cerrar conexion
        ConfigDB.closeConnection();
        //11 retornar bandera
        return isDeleted;
    }

}
