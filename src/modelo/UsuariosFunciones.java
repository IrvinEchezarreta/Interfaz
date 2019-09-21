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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListModel;
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
                //System.out.println(filas[i]);
            }
            modelo2.addRow(filas);
        }
        //System.out.println(modelo);
        return modelo2;
        
    }
    
    public DefaultTableModel TablaRegistros(Usuarios use) throws SQLException
    {
        System.out.println("modelo.UsuariosFunciones.TablaRegistros()");
        DefaultTableModel modelo3 = new DefaultTableModel();
        //Usuarios use = new Usuarios();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "";
        System.out.println(use.getNombreTipo());
        System.out.println(use.getSemestre());
        System.out.println(use.getSemestre());
        System.out.println(use.getNombreArea());
        System.out.println(use.getFechaInicio());
        System.out.println(use.getFechaFinal());
        
        if(use.getNombreTipo()== null && use.getNombreArea()==null && use.getSemestre()==null && use.getCarrera()== null && use.getFechaInicio()==null && use.getFechaFinal()==null)
        {
            sql = "SELECT * FROM registros";
        }
        else if( !use.getNombreTipo().equals("") && use.getNombreArea().equals("") && use.getSemestre().equals("") && use.getCarrera().equals("") && use.getFechaInicio().equals("d/MM/yyyy") && use.getFechaFinal().equals("d/MM/yyyy"))
        {
            sql = "SELECT * FROM registros WHERE Tipo LIKE '"+use.getNombreTipo()+"'";
        }
        else if( use.getNombreTipo().equals("") && use.getNombreArea().equals("") && !use.getSemestre().equals("") && use.getCarrera().equals("") && use.getFechaInicio().equals("d/MM/yyyy") && use.getFechaFinal().equals("d/MM/yyyy"))
        {
            sql = "SELECT * FROM registros WHERE Semestre LIKE '"+use.getSemestre()+"'";
        }
        else if( use.getNombreTipo().equals("") && !use.getNombreArea().equals("") && use.getSemestre().equals("") && use.getCarrera().equals("") && use.getFechaInicio().equals("d/MM/yyyy") && use.getFechaFinal().equals("d/MM/yyyy"))
        {
            sql = "SELECT * FROM registros WHERE AreasVisitadas LIKE '"+use.getNombreArea()+"'";
        }
        else if( use.getNombreTipo().equals("") && use.getNombreArea().equals("") && use.getSemestre().equals("") && !use.getCarrera().equals("") && use.getFechaInicio().equals("d/MM/yyyy") && use.getFechaFinal().equals("d/MM/yyyy"))
        {
            sql = "SELECT * FROM registros WHERE Nombre_Carrera LIKE '"+use.getCarrera()+"'";
        }
        else if(use.getNombreTipo().equals("") && use.getNombreArea().equals("") && use.getSemestre().equals("") && use.getCarrera().equals("") && !use.getFechaInicio().equals("d/MM/yyyy") && !use.getFechaFinal().equals("d/MM/yyyy"))
        {
            sql = "SELECT * FROM registros WHERE Fecha BETWEEN '"+use.getFechaInicio()+"' AND '"+use.getFechaFinal()+"'";
        }
        
        //System.out.println(sql+"<----------------------------------------------");
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        ResultSetMetaData rsMD = rs.getMetaData();
        int columnas = rsMD.getColumnCount();
        
        modelo3.addColumn("Matricula");
        modelo3.addColumn("Nombre");
        modelo3.addColumn("Segundo Nombre");
        modelo3.addColumn("Apellido Paterno");
        modelo3.addColumn("Apellido Materno");
        modelo3.addColumn("Nombre de la carrera");
        modelo3.addColumn("Tipo");
        modelo3.addColumn("Areas Visitadas");
        modelo3.addColumn("Semestre");
        modelo3.addColumn("Fecha");
        modelo3.addColumn("Hora");
        
        while(rs.next())
        {
            Object[]  filas = new Object[columnas];
            
            for(int i= 0; i < columnas; i++)
            {
                filas[i]= rs.getObject(i+1);
                System.out.println(filas[i]);
            }
            modelo3.addRow(filas);
        }
        
        return modelo3;
        
    }
    public DefaultComboBoxModel getModeloBoxArea() 
    {
        DefaultComboBoxModel listModelArea = new DefaultComboBoxModel();
        listModelArea.addElement("");
        try 
        {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = getConexion();
            
            String sql = "SELECT Area FROM areas ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
		listModelArea.addElement(rs.getString(1));
            }
            
        } catch (Exception e) {
        }
        return listModelArea;
      
    }
    
     public DefaultComboBoxModel getModeloBoxCarrera() 
    {
        DefaultComboBoxModel listModelCarrera = new DefaultComboBoxModel();
        listModelCarrera.addElement("");
	listModelCarrera.addElement("INGENIERÍA ELECTRÓNICA");
        listModelCarrera.addElement("INGENIERÍA EN GESTIÓN EMPRESARIAL");
        listModelCarrera.addElement("INGENIERÍA ELECTRICA");
        listModelCarrera.addElement("LICENCIATURA EN ADMINISTRACIÓN");
        listModelCarrera.addElement("INGENIERÍA AMBIENTAL");
        listModelCarrera.addElement("INGENIERÍA BIOQUÍMICA");
        listModelCarrera.addElement("INGENIERÍA MECÁNICA");
        listModelCarrera.addElement("INGENIERÍA EN SISTEMAS COMPUTACIONALES");
        listModelCarrera.addElement("INGENIERÍA CIVIL");
        listModelCarrera.addElement("INGENIERIA BIOMÉDICA");
        listModelCarrera.addElement("INGENIERÍA INDUSTRIAL");
        listModelCarrera.addElement("INGENIERÍA QUÍMICA");
           
        return listModelCarrera; 
    }
    public DefaultComboBoxModel getModeloBoxTipos() 
    {
        DefaultComboBoxModel listModelTipo = new DefaultComboBoxModel();
        listModelTipo.addElement("");
	listModelTipo.addElement("Licenciatura");
        listModelTipo.addElement("Posgrado");
        listModelTipo.addElement("Docentes");
        listModelTipo.addElement("Administrativos");
           
        return listModelTipo;  
    }
    public boolean consultar(Usuarios use, boolean fv)
    {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = getConexion();
            
            String sql = "SELECT * FROM usuarios WHERE usuario ='"+use.getUsuarioU()+"' AND contraseña= '"+use.getContraseña()+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
        while(rs.next())
        {
            fv = true;
            return fv;
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosFunciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
    
    public void modificar(Usuarios use, int num)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String usuariosSQL= "UPDATE usuarios SET nombre='";
        String areasSQL= "UPDATE areas SET Area='";
        try 
        {   
            if(num == 1)
            {
                ps = con.prepareStatement(usuariosSQL+use.getNombreUsuario()+"',apellidoPaterno='"+use.getApellidoPaternoU()+"',apellidoMaterno='"+use.getApellidoMaternoU()+"',contraseña='"+use.getContraseña()+"' WHERE usuario = '"+use.getUsuarioU()+"'");
                ps.execute();
            }
            else if(num == 2)
            {
                
            }
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
    
    public void eliminar(Usuarios use, int num)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String usuariosSQL= "DELETE FROM usuarios WHERE usuario='";
        String areaSQL= "DELETE FROM areas WHERE Area='";
        try 
        {   
            if(num == 1)
            {
                ps = con.prepareStatement(usuariosSQL+use.getUsuarioU()+"'");
                ps.execute();
            }
            else if(num == 2)
            {
                ps = con.prepareStatement(areaSQL+use.getNombreArea()+"'");
                ps.execute();
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}