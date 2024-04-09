package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object create(Object obj) {
        //crear conexion
        Connection objConnection = ConfigDB.openConnection();
        //casting del objeto
        Contratacion objContratacion = (Contratacion) obj;
        //try
        try {
            //sentencia sql
            String sql = "INSERT INTO contratacion (estado,salario,vacante_id,coder_id) VALUES (?,?,?,?,?);";
            //preparar statement y devolucion de llaves
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //dar valores a  ?
            objPrepare.setString(1,objContratacion.getEstado());
            objPrepare.setFloat(2,objContratacion.getSalario());
            objPrepare.setInt(3,objContratacion.getVacante_id());
            objPrepare.setInt(4,objContratacion.getCoder_id());
            //ejecutar query
            objPrepare.execute();
            //obtener llaves generadas
            ResultSet objResult = objPrepare.getGeneratedKeys();
            //mientras haya un registro
            while (objResult.next()){
                //agregar id
                objContratacion.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"contratacion creada");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error creando contratacion: "+e.getMessage());
        }
        //cerrar conexion
        ConfigDB.closeConnection();
        //retornar objeto cita
        return objContratacion;
    }

    @Override
    public boolean update(Object obj) {
        //conexion
        Connection objconnection = ConfigDB.openConnection();
        //casting del objeto recibido
        Contratacion objContratacion = (Contratacion) obj;
        //bandera de estado
        boolean isUpdate = false;
        //try
        try {
            //sentencia sql
            String sql = "UPDATE contratacion SET estado =?, salario=?, vacante_id=?,coder_id= ? WHERE id=?;";
            //preparamos el statement
            PreparedStatement objPrepare = objconnection.prepareStatement(sql);
            //asignamos valores a ?
            objPrepare.setString(1,objContratacion.getEstado());
            objPrepare.setFloat(2,objContratacion.getSalario());
            objPrepare.setInt(3,objContratacion.getVacante_id());
            objPrepare.setInt(4,objContratacion.getCoder_id());
            objPrepare.setInt(5,objContratacion.getId());

            //ejcutamos query o obtenemos cantidad de filas afectadas
            int totalRowAffected = objPrepare.executeUpdate();
            //verificamos si hubo cambios
            if (totalRowAffected>0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"Actualizacion de contratacion generada exitosamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al actualizar contratacion: "+e.getMessage());
        }
        //cerramos conexion
        ConfigDB.closeConnection();
        //retornamos valores
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        //1.abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. castear y obtener objeto
        Contratacion objContratacion = (Contratacion) obj;
        //3.crear bandera de estado
        boolean isDeleted = false;
        //4. try
        try {
            String sql = "DELETE FROM contratacion WHERE contratacion.id = ?;";
            //preparar statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //dar valores a ?
            objPrepare.setInt(1,objContratacion.getId());
            //cantidad de columnas afectadas
            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"La contratacion se eliminado correctamente");
            }
        }catch (Exception e){
            JOptionPane.showInputDialog(null,"Error al eliminar contratacion: "+e.getMessage());
        }
        //cerrar conexion
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {

        //1. abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. crear lista de contrataciones
        List<Object> listaContrataciones = new ArrayList<>();
        //3. try
        try {
            //4. sentencia sql
            // linea de sql que hace la consulta y une dos tablas
            String sql = "SELECT * FROM contratacion INNER JOIN coder ON coder.id = contratacion.coder_id  INNER JOIN vacante ON vacante.id = contratacion.vacante_id INNER JOIN empresa;";
            //5. crear statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. se ejecuta el query
            ResultSet objResult = objPrepare.executeQuery();
            //7. para cada registro de la tabla
            while (objResult.next()){
                //crear objeto que va a guardar la info
                Contratacion objContratacion = new Contratacion();
                Coder objCoder = new Coder();
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                //asignar valores
                //al tener varias tablas ó hacer INNER JOIN, especificar id de que tarjeta
                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setFecha_aplicacion(objResult.getString("contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getFloat("contratacion.salario"));

                objCoder.setId(objResult.getInt("coder.id"));
                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setClan(objResult.getString("coder.clan"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));



                //objetos tipo coder y vacante
                objVacante.setObjEmpresa(objEmpresa);
                objContratacion.setObjCoder(objCoder);
                objContratacion.setObjVacante(objVacante);

                //guardar objeto a la lista
                listaContrataciones.add(objContratacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        //cerrar conexion
        ConfigDB.closeConnection();
        //devolver lista
        return listaContrataciones;
    }
}
