package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    static Connection objConnection = null;
    public static Connection openConnection(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            //datos para la conexion
            String url = "jdbc:mysql://b6gt2zhmacm8y3pphdoi-mysql.services.clever-cloud.com:3306/b6gt2zhmacm8y3pphdoi";
            String user ="uqvomtuwoj4ru122";
            String password= "zTIdlgNniNAsMXJSRP99";

            //establecer conexion con los datos suministrados
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error al utilizar el driver: "+e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se puedo establecer conexion con la base de datos : "+e.getMessage());
        }
        return objConnection;
    }
    public static void closeConnection(){
        try {
            if (objConnection!=null){
                objConnection.close();
                objConnection=null;
                System.out.println("Conexion finalizada");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al finalizar la conexion: "+e.getMessage());
        }
    }
}
