/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.modeloInterfaz;
import modelo.InterfazFunciones;
import vista.Interfaz2;


/**
 *ESTA CLASE SIRVE LA ESTABLECER UNA LINEA DE COMUNICACION CON LAS CONSULTAS
 * @author HP
 * 
 * 
 * 
 */




public class pruebasql extends conexion 
{
    
    public boolean consultar(modeloInterfaz use, boolean fv)//metodo para validar los datos de la matricula
    {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = getConexion();
            
            String sql = "SELECT * FROM alumnos WHERE matricula ='"+(use.getTfMatricula()+"");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
        while(rs.next())
        {
            fv = true;
            System.out.println("conexion correcta, matricula ="+(use.getTfMatricula())+"");
            return fv;
            
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosFunciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        }
        
        
    
     public static void main (String [] args){
  
            boolean fv = false;
            pruebasql A = new pruebasql();

            
         
     }
    
}
   
    
