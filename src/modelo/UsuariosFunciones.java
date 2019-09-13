/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HP
 */
public class UsuariosFunciones extends conexion
{
    //esta clase herada la clase conexion para poder hacer las consultas sql
    //aqui va en su mayoria consultas mas sin embargo se puede poner otras cosas dependiendo de la situacion
    public DefaultTableModel TablaUsuarios() throws SQLException
    {
        System.out.println("modelo.UsuariosFunciones.TablaUsuarios()");
        DefaultTableModel modelo = new DefaultTableModel();
        //pa.tablaUsuarios.setModel(modelo);
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM usuarios";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        ResultSetMetaData rsMD = rs.getMetaData();
        int columnas = rsMD.getColumnCount();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");
       
        while(rs.next())
        {
            Object[]  filas = new Object[columnas];
            
            for(int i= 0; i < columnas; i++)
            {
                filas[i]= rs.getObject(i+1);
                //System.out.println(filas[i]);
            }
            modelo.addRow(filas);
        }
        //System.out.println(modelo);
        return modelo;
    }
    
    public DefaultTableModel TablaAreas() throws SQLException
    {
        System.out.println("modelo.UsuariosFunciones.TablaAreas()");
        DefaultTableModel modelo2 = new DefaultTableModel();
        //pa.tablaUsuarios.setModel(modelo);
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM areas";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        ResultSetMetaData rsMD = rs.getMetaData();
        int columnas = rsMD.getColumnCount();
        
        modelo2.addColumn("Nombre del Area");
       
        while(rs.next())
        {
            Object[]  filas = new Object[columnas];
            
            for(int i= 0; i < columnas; i++)
            {
                filas[i]= rs.getObject(i+1);
                System.out.println(filas[i]);
            }
            modelo2.addRow(filas);
        }
        //System.out.println(modelo);
        return modelo2;
        
    }
    
    public void registrar(Usuarios use, int num)
    {
        System.out.println("entro al metodo registrar");
        PreparedStatement ps = null;
        Connection con = getConexion();
        String usuarios= "INSERT INTO usuarios(nombre, apellidoPaterno, apellidoMaterno, usuario, contraseña) VALUES(?,?,?,?,?)";
        String areas= "INSERT INTO areas(Area) VALUES(?)";
        
        try 
        {
            //use es un objeto creado para llamar metodos de la clase Usuarios
            //en este caso para obtner lo guardado en los setters y hacer la insercion
            if(num == 1)
            {
                ps = con.prepareStatement(usuarios);
                ps.setString(1, use.getNombreUsuario());
                ps.setString(2, use.getApellidoPaternoU());
                ps.setString(3, use.getApellidoMaternoU());
                ps.setString(4, use.getUsuarioU());
                ps.setString(5, use.getContraseña());
                System.out.println("entro a la clasula sql");
                ps.execute();
            }
            else if (num == 2) 
            {
                ps = con.prepareStatement(areas);
                ps.setString(1, use.getNombreArea());
                System.out.println("entro a la clasula sql");
                ps.execute();
            }
            
        } catch (Exception e) 
        {
            System.out.println("Error");
        }
    }
    
    public void modificar(Usuarios use)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql= "UPDATE usuarios SET nombre='";
        try 
        {
            ps = con.prepareStatement(sql+use.getNombreUsuario()+"',apellidoPaterno='"+use.getApellidoPaternoU()+"',apellidoMaterno='"+use.getApellidoMaternoU()+"',contraseña='"+use.getContraseña()+"' WHERE usuario = '"+use.getUsuarioU()+"'");
            ps.execute();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void buscar(Usuarios use)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
    }
    
    public void eliminar(Usuarios use)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql= "DELETE FROM usuarios WHERE usuario='";
        try 
        {  
            ps = con.prepareStatement(sql+use.getUsuarioU()+"'");
            ps.execute();
        } 
        catch (Exception e) 
        {
        }
    }
}
