/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuarios;//se importa del paquete modelo la clase usuarios
import modelo.UsuariosFunciones;//se importa del paquete modelo la clase usuariosFunciones
import vista.PanelAdmin;//se importa del paquete vistas el Frame PanelAdmi
/**
 *
 * AQUI SE PONE LOS ACCION LISENER CORRECPONDIENTE A CADA COMPONENTE
 * SE PUEDE TOMAR TODO LOS COMPONENETES PUBLICOS DESDE TXTFIELD Y ETC
 * @author HP
 */

public class controlador implements ActionListener
{
    private Usuarios mod;//se le asigna un varible identificadora para el contructor 
    private UsuariosFunciones mod2;//se le asigna un varible identificadora para el contructor 
    private PanelAdmin FrameAdmi;//se le asigna un varible identificadora para el contructor 
    
    public controlador(Usuarios mod, UsuariosFunciones mod2, PanelAdmin FrameAdmi)
    {
        System.out.println("entro al contructor");
        this.mod=mod;//sirve para hacer llamadas a los metodos contenidos
        this.mod2=mod2;//sirve para hacer llamadas a los metodos contenidos
        this.FrameAdmi=FrameAdmi;//sirve para hacer llamadas a los metodos contenidos
        this.FrameAdmi.btnRegistrar.addActionListener(this);//opcion para registrar
        this.FrameAdmi.item1.addActionListener(this);//opcion elminar datos de popMenu
        this.FrameAdmi.item2.addActionListener(this);//opcion modificar datos de popMenu 
    }
    
    public void iniciar()
    {
        FrameAdmi.setTitle("Administrador");
        FrameAdmi.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //getSource obtiniene algunas de las acciones 
        //se compara de donde vino la accion en este caso si vino del btnRegistar del FRAME DEL ADMINISTRADOR entra en la condicion
        if(e.getSource()== FrameAdmi.btnRegistrar)
        {
            System.out.println("entro a la accion");
            //se invoca al objeto mod que contiene los getters y setters
            //se obtiene del frame por medio del txtField y se envia al correcpondiente setters
            mod.setNombreUsuario(FrameAdmi.txtNombresU.getText());
            mod.setApellidoPaternoU(FrameAdmi.txtApellidoPaternoU.getText());
            mod.setApellidoMaternoU(FrameAdmi.txtApellidoMaternoU.getText());
            mod.setUsuarioU(FrameAdmi.txtUsuarioU.getText());
            mod.setContraseña(FrameAdmi.txtContraseñaU.getText());
            mod2.registrar(mod);//se llama al metodo registrar de la clase UsuarioFunciones con el contenido en mod
            FrameAdmi.cargarUsuarios();//se actualiza la tabla
            
        }
        else if(e.getSource()==FrameAdmi.item1)//opcion para eliminar
        {
            //se toma de la tabla usuarios los valores en la fila leccionada de la columna 3
            mod.setUsuarioU(FrameAdmi.tablaUsuarios.getValueAt(FrameAdmi.tablaUsuarios.getSelectedRow(), 3).toString());
            mod2.eliminar(mod);
            FrameAdmi.cargarUsuarios();
        }
        else if(e.getSource()==FrameAdmi.item2)//opcion para modificar
        {
            //se la tabla usuarios se obtinene el contenido de la fila por cada columna y se manda
            //a los getter y setters para poder usar mas adelante en modificar
            mod.setNombreUsuario(FrameAdmi.tablaUsuarios.getValueAt(0, 0).toString());
            mod.setApellidoPaternoU(FrameAdmi.tablaUsuarios.getValueAt(0, 1).toString());
            mod.setApellidoMaternoU(FrameAdmi.tablaUsuarios.getValueAt(0, 2).toString());
            mod.setUsuarioU(FrameAdmi.tablaUsuarios.getValueAt(0, 3).toString());
            mod.setContraseña(FrameAdmi.tablaUsuarios.getValueAt(0, 4).toString());
            mod2.modificar(mod);
            FrameAdmi.cargarUsuarios();
            
        }
        
        
    }
    
    
}
