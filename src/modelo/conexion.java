/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *ESTA CLASE SIRVE LA ESTABLECER UNA LINEA DE COMUNICACION CON LAS CONSULTAS
 * @author HP
 */
public class conexion 
{
    private final String usuario="root";
    private final String contraseña="";
    private final String base="testbiblio";
    private final String url = "jdbc:mysql://localhost/"+base;
    Connection con=null;
    static String controlador= "com.mysql.jdbc.Driver";
    
  public Connection getConexion()
  {
        try
        {
            Class.forName(controlador);//carga el driver nativo 
            con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/testbiblio", "root", "");
            //System.out.println("se establecio conexion");
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
        }
        return con;
  }
}
