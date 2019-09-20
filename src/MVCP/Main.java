/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCP;
import java.sql.SQLException;
/**
 *
 * @author HP
 */
import controlador.controlador;
import modelo.ExcelFunciones;
import modelo.Usuarios;
import modelo.UsuariosFunciones;
import modelo.ExcelFunciones;
import vista.PanelAdmin;
import vista.PanelPrincipal;
import vista.Login;
public class Main 
{
    public Main()
    {
        
    }
    public static void main(String[] args) throws SQLException {
                 
        System.out.println("MVCP.Main.main()");
        
        PanelPrincipal FramePrincipal = new PanelPrincipal();
        Login lg = new Login();
        PanelAdmin FrameAdmi=new PanelAdmin();
        Usuarios mod=new Usuarios();
        UsuariosFunciones mod2=new UsuariosFunciones();
        ExcelFunciones modExcel = new ExcelFunciones();
        
        
        controlador ctrl2=new controlador(mod, mod2, modExcel, FrameAdmi,FramePrincipal, lg);
        ctrl2.iniciar();
    }
    
}
