/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCP;
import controlador.controlador;
import java.sql.SQLException;
import modelo.Usuarios;
/**
 *
 * @author HP
 */
import modelo.UsuariosFunciones;
import vista.PanelAdmin;
public class Main 
{
    public static void main(String[] args) throws SQLException {
        
        Usuarios mod=new Usuarios();
        UsuariosFunciones mod2=new UsuariosFunciones();
        PanelAdmin FrameAdmi=new PanelAdmin();
                
        System.out.println("MVCP.Main.main()");
        controlador ctrl=new controlador(mod, mod2, FrameAdmi);
        FrameAdmi.setVisible(true);
        
    }
    
}
