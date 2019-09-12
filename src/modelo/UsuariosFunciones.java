/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author HP
 */
public class UsuariosFunciones extends conexion
{
    public void registrar(Usuarios use)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql= "INSERT INTO usuarios(nombre, apellidoPaterno, apellidoMaterno, usuario, contraseña) VALUES(?,?,?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, use.getNombreUsuario());
            ps.setString(1, use.getApellidoPaternoU());
            ps.setString(1, use.getApellidoMaternoU());
            ps.setString(1, use.getUsuarioU());
            ps.setString(1, use.getContraseña());
            
        } catch (Exception e) 
        {
            
        }
    }
    
}
