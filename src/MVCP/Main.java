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
import controlador.controlador;//SE IMPORTA DEL PAQUETE CONTROLADOR EL ARCHIVO JAVA CONTTROLADOR 
/////////////////////////////////SE IMPORTAND DEL PAQUETE MODELO//////////////////////////////////
import modelo.Usuarios;//SE IMPORTA DEL PAQUETE EL ARCHIVO JAVA USUARIOS
import modelo.UsuariosFunciones;//SE IMPORTA DEL PAQUETE EL ARCHIVO JAVA USUARIOFUNCIONES
import modelo.ExcelFunciones;//SE IMPORTA DEL PAQUETE EL ARCHIVO JAVA EXCELFUNCIONES
/////////////////////////////////INTERFACES DEL PAQUETE VISTA/////////////////////////////////
import vista.PanelAdmin;//SE IMPORTA DEL PAQUETE EL ARCHIVO JAVA PANELADMIN ES LA INTERFAZ
import vista.PanelPrincipal;//SE IMPORTA DEL PAQUETE EL ARCHIVO JAVA PANELPRINCIPAL INTERFAZ
import vista.Login;//SE IMPORTA DEL PAQUETE EL ARCHIVO LOGIN INTERFAZ
public class Main 
{
    public Main()
    {
        
    }
    public static void main(String[] args) throws SQLException {
                 
        System.out.println("MVCP.Main.main()");
        
        PanelPrincipal FramePrincipal = new PanelPrincipal();//SE CREA EL OBJETO PARA LLAMAR A LA INTERFAZ
        Login lg = new Login();//SE CREA EL OBJETO PARA LLAMAR A LA INTERFAZ LOGIN
        PanelAdmin FrameAdmi=new PanelAdmin();//SE CREA EL OBJETO PARA LLAMAR A LA INTERFAZ DEL ADMINISTRADOR
        Usuarios mod=new Usuarios();//SE CREA EL OBJETO PARA LLMAR A LA CLASE USUARIOS DEL PAQUETE  MODELO
        UsuariosFunciones mod2=new UsuariosFunciones();//SE CREA EL OBJETO PARA LLAMAR A LA CLASE USUARIOFUNCIONES DEL PAQUETE MODELO
        ExcelFunciones modExcel = new ExcelFunciones();
        
        /*
        NOTA: 
        CADA QUE SE CREA UNA NUEVA CLASE DENTRO DE UN PAQUETE SE TIENE CREAR UNA IMPORTACION, LUEGO CREAR SU OBJETO Y 
        DE IGUAL MANERA AGREGARLO AL OBJETO CREADO PARA EL CONTROLADOR EN ESTE CADO "crtl2"
        */
        controlador ctrl2=new controlador(mod, mod2, modExcel, FrameAdmi,FramePrincipal, lg);//SE INICIALIZA EL OBJETO CONTROLADOR JUNTO CON LOS OBJETOS ANTERIORMENTE MENCIONADOS
        ctrl2.iniciar();
    }
    
}
