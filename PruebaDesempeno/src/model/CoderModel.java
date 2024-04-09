package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object create(Object obj) {
        //crear conexion
        Connection objConnection = ConfigDB.openConnection();
        //casting del objeto
        Coder objCoder = (Coder) obj;
        //try
        try {
            //sentencia sql
            String sql = "INSERT INTO coder (nombre, apellidos, documento, cohorte, cv, clan) VALUES (?,?,?,?,?,?);";
            //preparar statement y devolucion de llaves
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //dar valores a  ?

            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellidos());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());
            //ejecutar query
            objPrepare.execute();
            //obtener llaves generadas
            ResultSet objResult = objPrepare.getGeneratedKeys();
            //mientras haya un registro
            while (objResult.next()){
                //agregar id para cada registro
                objCoder.setId(objResult.getInt(1)); //primera columna siempre es el id
            }
            JOptionPane.showMessageDialog(null,"coder cread@ exitosamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error creando coder: "+e.getMessage());
        }
        //cerrar conexion
        ConfigDB.closeConnection();
        //retornar objeto cita
        return objCoder;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaCoders = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM coder;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Coder objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listaCoders.add(objCoder);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al listar coders: "+e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaCoders;
    }
    @Override
    public boolean update(Object obj) {
        //1.abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. crear y castear el objeto que llego como parametro de tipo coder
        Coder objCoder = (Coder) obj;
        //3. bandera de estado
        boolean isUpdate = false;
        //4.ejecuciones con posibles errores
        try {
            //5. sentencia sql
            String sql = "UPDATE coder SET nombre = ?,apellidos= ?, documento=?, clan=?,cohorte=?,cv=? WHERE id= ?;";
            //6. crear statemente para la sentencia sql
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //7. asignar valores a los  ? en el statement
            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellidos());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setString(4,objCoder.getClan());
            objPrepare.setInt(5,objCoder.getCohorte());
            objPrepare.setString(6,objCoder.getCv());
            objPrepare.setInt(7,objCoder.getId());

            //8. ejecutar el query que devuelve las filas afectadas
            int totalAffectedRow = objPrepare.executeUpdate();
            //9. validar si hubo cambios
            if (totalAffectedRow>0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"Actualizacion generada correctamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al actualizar coder: "+e.getMessage());
        }
        //10. cerramos la conexion
        ConfigDB.closeConnection();
        //11. devolver estado de la bandera si se elimino o no el registro
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {

        //1.abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //2.castear que llega como parametro
        Coder objCoder = (Coder) obj;
        //3. bandera de estado si se elimino o no
        boolean isDelete = false;
        //4. Tod o puede fallar entonces un trycatch
        try {
            //5. sentencia sql
            String sql = "DELETE FROM coder WHERE id = ?;";
            //6. ejecutar sentencia sql preparando el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //7. asignarle valor a los signos de interrogacion
            objPrepare.setInt(1,objCoder.getId());
            //8. ejecutar el query con el executeUpdate que devuelve un entero de cuantas filas afectadas
            int totalAffectedRows = objPrepare.executeUpdate();
            //9. verificar si se hizo alguna modificacion
            if (totalAffectedRows>0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Eliminacion generada correctamente");
            }
            //crear excepciones
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al eliminar coder: "+e.getMessage());
        }
        //10. cerrar conexion
        ConfigDB.closeConnection();
        //11. devolver estado de bandera
        return isDelete;
    }
    public List<Coder> findByClan(String var){
        List<Coder> listCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM coder WHERE clan like ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+var+"%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Coder objCoder =new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));

                listCoder.add(objCoder);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al encontrar coder por clan: "+e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }


    public List<Coder> findByTecno(String var){
        List<Coder> listCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM coder WHERE tecnologia like ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+var+"%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Coder objCoder =new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));

                listCoder.add(objCoder);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al encontrar coder por tecnologia: "+e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }
}
