/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Usuarios;//se importa del paquete modelo la clase usuarios
import modelo.UsuariosFunciones;//se importa del paquete modelo la clase usuariosFunciones
import modelo.ExcelFunciones;
import vista.PanelAdmin;//se importa del paquete vistas el Frame PanelAdmi
import vista.PanelPrincipal;
import vista.Login;
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
    private ExcelFunciones modExcel;
    private PanelAdmin FrameAdmi;//se le asigna un varible identificadora para el contructor
    private PanelPrincipal FramePrincipal;
    private Login lg;
    ////////////////////////////objetos utilies para exportar e importar///////////////////////////////
    JFileChooser SelectArchivo=new JFileChooser();
    File archivo;
    int contador=0;
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public controlador(Usuarios mod, UsuariosFunciones mod2,ExcelFunciones modExcel, PanelAdmin FrameAdmi, PanelPrincipal FramePrincipal, Login lg)
    {
        System.out.println("entro al contructor");
        this.mod=mod;//sirve para hacer llamadas a los metodos contenidos
        this.mod2=mod2;//sirve para hacer llamadas a los metodos contenidos
        this.modExcel=modExcel;
        this.FrameAdmi=FrameAdmi;//sirve para hacer llamadas a los metodos contenidos
        this.FramePrincipal=FramePrincipal;
        this.lg=lg;
       
        this.lg.btnIniciarSecion.addActionListener(this);
        this.lg.btnCerraVentanaLogin.addActionListener(this);
        
        this.FramePrincipal.btnAdministradorOpcion.addActionListener(this);
        this.FramePrincipal.btnEstudianteOpcion.addActionListener(this);
        this.FramePrincipal.btnVisitanteOpcion.addActionListener(this);
        
        this.FrameAdmi.btnSalirAdmi.addActionListener(this);
        this.FrameAdmi.btnRegistrar.addActionListener(this);//opcion para registrar
        this.FrameAdmi.item1.addActionListener(this);//opcion elminar datos de popMenu
        this.FrameAdmi.item2.addActionListener(this);//opcion modificar datos de popMenu 
        
        this.FrameAdmi.btnRegistrarArea.addActionListener(this);
        this.FrameAdmi.item1A.addActionListener(this);
        this.FrameAdmi.item2A.addActionListener(this);
        
        this.FrameAdmi.btnFiltrarRegistros.addActionListener(this);
        
        this.FrameAdmi.btnExportarExcel.addActionListener(this);//ExportarExcel;
    }
    
    public void iniciar()
    {
        FramePrincipal.setTitle("Menu");
        FramePrincipal.setLocationRelativeTo(null);
        FramePrincipal.setVisible(true);
    }
    
    public void iniciarLogin()
    {
        lg.setTitle("Login");
        lg.setLocationRelativeTo(null);
        lg.setVisible(true);
    }
    
    public void iniciarAdmi()
    {
        FrameAdmi.setTitle("Administrador");
        FrameAdmi.setLocationRelativeTo(null);
        FrameAdmi.setVisible(true);
    }
    
    public void AgregarFiltro(){
        SelectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)","xls"));
        SelectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)","xlsx"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //getSource obtiniene algunas de las acciones 
        if (e.getSource()==FramePrincipal.btnAdministradorOpcion) 
        {
            iniciarLogin();
        }
        else if(e.getSource()==lg.btnIniciarSecion)
        {
            boolean fv = false;
            mod.setContraseña(lg.txtContraseñaAutentificacio.getText());
            mod.setUsuarioU(lg.txtUsuarioAutentificaion.getText());
            fv =mod2.consultar(mod, fv);
            if(fv == true)
            {
                iniciarAdmi();
                lg.dispose();
            }
        }
        else if (e.getSource()== lg.btnCerraVentanaLogin)
        {
            lg.dispose();
        }
        else if(e.getSource()==FrameAdmi.btnSalirAdmi)
        {
            FrameAdmi.dispose();
        }
        //se compara de donde vino la accion en este caso si vino del btnRegistar del FRAME DEL ADMINISTRADOR entra en la condicion
        else if(e.getSource()== FrameAdmi.btnRegistrar)
        {
            System.out.println("entro a la accion");
            //se invoca al objeto mod que contiene los getters y setters
            //se obtiene del frame por medio del txtField y se envia al correcpondiente setters
            mod.setNombreUsuario(FrameAdmi.txtNombresU.getText());
            mod.setApellidoPaternoU(FrameAdmi.txtApellidoPaternoU.getText());
            mod.setApellidoMaternoU(FrameAdmi.txtApellidoMaternoU.getText());
            mod.setUsuarioU(FrameAdmi.txtUsuarioU.getText());
            mod.setContraseña(FrameAdmi.txtContraseñaU.getText());
            mod2.registrar(mod, 1);//se llama al metodo registrar de la clase UsuarioFunciones con el contenido en mod
            FrameAdmi.cargarUsuarios();//se actualiza la tabla
            
        }
        else if(e.getSource()== FrameAdmi.btnRegistrarArea)
        {
            mod.setNombreArea(FrameAdmi.txtArea.getText());
            mod2.registrar(mod, 2);
            FrameAdmi.cargarAreas();
        }
        else if(e.getSource()==FrameAdmi.item1)//opcion para eliminar
        {
            //se toma de la tabla usuarios los valores en la fila leccionada de la columna 3
            mod.setUsuarioU(FrameAdmi.tablaUsuarios.getValueAt(FrameAdmi.tablaUsuarios.getSelectedRow(), 3).toString());
            mod2.eliminar(mod, 1);
            FrameAdmi.cargarUsuarios();
        }
        else if(e.getSource()==FrameAdmi.item2)//opcion para modificar
        {
            //se la tabla usuarios se obtinene el contenido de la fila por cada columna y se manda
            //a los getter y setters para poder usar mas adelante en modificar
            
            mod.setNombreUsuario(FrameAdmi.tablaUsuarios.getValueAt(FrameAdmi.tablaUsuarios.getSelectedRow(), 0).toString());
            mod.setApellidoPaternoU(FrameAdmi.tablaUsuarios.getValueAt(FrameAdmi.tablaUsuarios.getSelectedRow(), 1).toString());
            mod.setApellidoMaternoU(FrameAdmi.tablaUsuarios.getValueAt(FrameAdmi.tablaUsuarios.getSelectedRow(), 2).toString());
            mod.setUsuarioU(FrameAdmi.tablaUsuarios.getValueAt(FrameAdmi.tablaUsuarios.getSelectedRow(), 3).toString());
            mod.setContraseña(FrameAdmi.tablaUsuarios.getValueAt(FrameAdmi.tablaUsuarios.getSelectedRow(), 4).toString());
            mod2.modificar(mod, 1);
            FrameAdmi.cargarUsuarios();
            
        }
        else if(e.getSource()==FrameAdmi.item1A)
        {
            System.out.println("controlador.controlador.actionPerformed()");
            mod.setNombreArea(FrameAdmi.tablaAreas.getValueAt(FrameAdmi.tablaAreas.getSelectedRow(), 0).toString());
            mod2.eliminar(mod, 2);
            FrameAdmi.cargarAreas();
        }
        else if(e.getSource()==FrameAdmi.item2A)
        {
            mod.setNombreArea(FrameAdmi.tablaAreas.getValueAt(FrameAdmi.tablaAreas.getSelectedRow(), 0).toString());
            mod2.modificar(mod, 2);
            FrameAdmi.cargarAreas();
        }
        else if(e.getSource()==FrameAdmi.btnFiltrarRegistros)
        {
            mod.setNombreArea(FrameAdmi.comboBoxArea.getSelectedItem().toString());
            mod.setNombreTipo(FrameAdmi.comboBoxTipo.getSelectedItem().toString());
            mod.setSemestre(FrameAdmi.txtSemestre.getText());
            mod.setCarrera(FrameAdmi.comboBoxCarrera.getSelectedItem().toString());

            FrameAdmi.cargarRegistros(mod);
            
        }
        else if (e.getSource()==FrameAdmi.btnExportarExcel)//RESPUESTA AL BOTON EXPORTAR
        {
            contador++;
            if(contador==1)AgregarFiltro();
            if(SelectArchivo.showDialog(null, "Seleccionar Archivo")==JFileChooser.APPROVE_OPTION){
                archivo=SelectArchivo.getSelectedFile();
                //ALT + 124 ||
                if(archivo.getName().endsWith("xls")||archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, modExcel.Exportar(archivo,FrameAdmi.tablaRegistros));
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccionar formato Valido");
                }
            }
        }
    }
}
