/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

/////Estos imports son para las funciones propias de paquetes incluidos en java/////
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

////////IMPORTANTE LA IMPORTACIONES DE LOS PAQUETES QUE SE ENCUENTRAN DEBAJO///////////////////////////////
import modelo.Usuarios;//SE IMPORTA DEL PAQUETE MODELO LA CLASE USUARIOS
import modelo.UsuariosFunciones;//SE IMPORTA DEL PAQUETE MODELO LA CLASE USUARIOFUNCIONES
import modelo.ExcelFunciones;//SE IMPORTA DEL PQUETE MODELO LA CLASE EXCELUNCIONES
import vista.PanelAdmin;//SE IMPORTA DEL PAQUETE VISTA LA CLASE PANELADMIN QUE ES LA INTERFAZ DEL ADMINISTRADOR
import vista.PanelPrincipal;//SE IMPORTA DEL PAQUETE LA CLASE PANELPRINCIPAL QUE EL MENU DE TRES OPCIONES
import vista.Login;//SE IMPORTA DEL PAQUTE VISTA EL LOGIN PARA VALIDAR USUARIO Y CONTRASEÑA
import vista.Interfaz2;//Se importa la vista de la interfaz de registro de visitas para alumnos
import modelo.modeloInterfaz;//se importa el modelo de interfaz de registro de visitas para alumnos
import modelo.InterfazFunciones;//se importan las funciones que ejecuta la interfaz de registro para alumnos
import modelo.conexion;
import vista.visitante;
import modelo.VisitantesFunciones;
/**
 *SOLO ACCION LISTENER DE LOS BOTONES O DE CUALQUIE OTRA COSA QUE INDIQUE LA EJECUCION DE +
 * UNA ACCION EN LAS INTERFACES CONTENIDAS EN EL PAQUETE VISTAS
 * 
 * 
 * AQUI SE PONE LOS ACCION LISENER CORRECPONDIENTE A CADA COMPONENTE
 * SE PUEDE TOMAR TODO LOS COMPONENETES PUBLICOS DESDE TXTFIELD Y ETC
 * 
 * 
 * 
 * TODOS LOS COMPONENTES DE LA INTERFACES DEBEN ESTA COMO PUBLICOS  NO PRIVADOS 
 * 
 * 
 * 
 * @author HP
 */

public class controlador implements ActionListener
{
    /*
    NOTA:
        cuando se crean mas interfaces o clases en diferentes paquetes, si al principio 
        no se hace la import a esta clase controlador jamas se va hace ninguna de la funciones como validar el usuario
        de la interfaz del login por ejemplo.
    
        SI SE REALIZO LA IMPORTACION EJEPLO: import vista.login
        1- se crea una variable privada Login haciendo referencia a la interfaz
        2- se crea una varible para esa clase EJEMPLO:private login lg
    NOTA:
        1- lg sirve para llamar o hacer referencia a cualquier cosa contenida dentro de la clase login
           del paquete vista SOLO A LO QUE ESTA EN LA clase LOGIN
        2- este procesos se hace para hacer llamados a metodos, funciones o componestes dentro las clases de
           diferentes paquetes
    */
    private Usuarios mod;//se crea una variable mod para la clase usuarios
    private UsuariosFunciones mod2;//se crea una variable mod2 para la clase usuarioFunciomes
    private ExcelFunciones modExcel;//se crea una variable modExcel para la clase Excelfunciones
    private PanelAdmin FrameAdmi;//se le asigna un varible identificadora para el contructor
    private PanelPrincipal FramePrincipal;//se crea una variable FremaePrincipal para la clase panelPrincipal
    private Login lg;//se crea una variable lg para la clase Login
    private modeloInterfaz modInt;
    private Interfaz2 interfaz;
    private InterfazFunciones funcs;
    private conexion con;
    private visitante vis;
    private VisitantesFunciones visf;

    ////////////////////////////objetos utilies para exportar e importar///////////////////////////////
    JFileChooser SelectArchivo=new JFileChooser();
    File archivo;
    int contador=0;
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    //el contructor debe recibir de la clase Main los objetos que se quieran utilizar
    public controlador(Usuarios mod, UsuariosFunciones mod2,ExcelFunciones modExcel, PanelAdmin FrameAdmi, PanelPrincipal FramePrincipal, Login lg,Interfaz2 interfaz,InterfazFunciones funcs,modeloInterfaz modInter, visitante vis, VisitantesFunciones visf)
    {
        System.out.println("entro al contructor");
        this.mod=mod;//sirve para hacer llamadas a los metodos contenidos
        this.mod2=mod2;//sirve para hacer llamadas a los metodos contenidos
        this.modExcel=modExcel;//sirve para hacer llamadas a los metodos contenidos
        this.FrameAdmi=FrameAdmi;//sirve para hacer llamadas a los metodos contenidos
        this.FramePrincipal=FramePrincipal;//sirve para hacer llamadas a los metodos contenidos
        this.lg=lg;//sirve para hacer llamadas a los metodos contenidos
        this.modInt=modInter;
        this.funcs=funcs;
        this.interfaz=interfaz;
        this.vis = vis;
        this.visf = visf;
        //BOTONES DE LA INTERFAZ LOGIN
        this.lg.btnIniciarSecion.addActionListener(this);//lg hace un llamdo al componente boton de nombre btnIniciarSecion
        this.lg.btnCerraVentanaLogin.addActionListener(this);//lg hace un llamdo al componente boton de nombre btnCerraVentanaLogin
        
        //SON LOS TRES BOTONES QUE HACEN DE MENU EN LA PRIMERA INTERFAZ
        this.FramePrincipal.btnAdministradorOpcion.addActionListener(this);//FramePrincipal hace un llamdo al componente boton de nombre btnAdministradorOpcion
        this.FramePrincipal.btnEstudianteOpcion.addActionListener(this);//FramePrincipal hace un llamdo al componente boton de nombre btnEstudianteOpcion
        this.FramePrincipal.btnVisitanteOpcion.addActionListener(this);//FramePrincipal un llamdo al componente boton de nombre btnVisitanteOpcion
        
        //BOTONES Y FUNCIONES DE LA INTERFAZ DEL ADMINISTRADOR
        this.FrameAdmi.btnSalirAdmi.addActionListener(this);
        this.FrameAdmi.btnRegistrar.addActionListener(this);//opcion para registrar
        this.FrameAdmi.item1.addActionListener(this);//opcion elminar datos de popMenu
        this.FrameAdmi.item2.addActionListener(this);//opcion modificar datos de popMenu 
        
        this.FrameAdmi.btnRegistrarArea.addActionListener(this);
        this.FrameAdmi.item1A.addActionListener(this);
        this.FrameAdmi.item2A.addActionListener(this);
        
        this.FrameAdmi.btnFiltrarRegistros.addActionListener(this);
        this.FrameAdmi.btnExportarExcel.addActionListener(this);//ExportarExcel;
        
        //BOTON DE LA INTERFAZ DE REGIsTRO DE usuarios
        this.interfaz.btnConfirmar.addActionListener(this);
        this.interfaz.btnRegistrar.addActionListener(this);
        this.interfaz.btnSalirRegistro.addActionListener(this);
         this.interfaz.cbZonaOtros1.addActionListener(this);
        
        this.vis.botonRegistrarVIS.addActionListener(this);
        this.vis.btnRegresarVisitante.addActionListener(this);
        this.vis.cbOtros.addActionListener(this);
    
    }
    //ACONTINUACION SE CREAN METODOS PARA INICIALIZAR INICALIZAN LA INTERFACES 
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
    
     public void iniciarInterfaz()
    {
       interfaz.setTitle("Registro de alumnos");
       interfaz.setLocationRelativeTo(null);
       interfaz.setVisible(true);
         interfaz.tfOtros.setEnabled(false);
    }
      public void iniciarVistitante()
    {
       vis.setTitle("Registro de visitantes");
       vis.setLocationRelativeTo(null);
       vis.setVisible(true);
       vis.tfOtros.setEnabled(false);
     
       
    }
    
    public void AgregarFiltro(){
        SelectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)","xls"));
        SelectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)","xlsx"));
    }
    
  
    
    //AQUI SE RELIZAN LA FUNCIONES O ACCIONES DE CADA BOTON Y ETC, AL MISMO TIEMPO SE ENVIAN O RECIVEN
    //ESOS DATOS DE LA CLASE USUARIOFUNCIONES DEL PAQUETE MODELO ASI COMO DE EXCELFUNCIONES Y ETC.
    //Override
    InterfazFunciones obj = new InterfazFunciones();
    VisitantesFunciones visfun = new VisitantesFunciones();
    
    public void actionPerformed(ActionEvent e)
    {
        /*
        NOTA:
            cada que se presiona in boton se detona una accion, se valida cual de 
            todos los botones o componetes creados dentro de las clases detono la accion
            y se valida en los if
        */
        if (e.getSource()==FramePrincipal.btnAdministradorOpcion) 
        {
            iniciarLogin();// se abre la ventana del login
        }
        else if(e.getSource()==FramePrincipal.btnEstudianteOpcion){
            iniciarInterfaz();// se abre la ventana de la interfaz de registro ?
        }
        
        else if(e.getSource()==FramePrincipal.btnVisitanteOpcion){
            iniciarVistitante();// se abre la ventana de la interfaz de registro ?
        }
        
        else if(e.getSource()==lg.btnIniciarSecion)
        {
            boolean fv = false;
            /*
            NOTA:
                SI SE QUIERE TOMAR ALGO CONTENIDO EN UN TEXTFIEL
                1-Se hace un llamado a ese componente por medio de lg
            EJEMPLO: lg.NombreDelTextFiel.getText()
                2-Una vez que se toma el contenido se manta a los setters de la clase usuarios
                para no perder el valor obtenido
                3-se hace un llamado a mod2 para el metodo consultar y se debe enviar
                a ese metodo el mod con las varibles que se enciaron en los set
            */
            mod.setContraseña(lg.txtContraseñaAutentificacio.getText());
            mod.setUsuarioU(lg.txtUsuarioAutentificaion.getText());
            fv =mod2.consultar(mod, fv);
            if(fv == true)
            {
                iniciarAdmi();//se inicia la ventana del administrador
                lg.dispose();//se cierra el login
            }
        }
        
       
        
        else if (e.getSource()==interfaz.btnConfirmar){
           
          //consultar  
           String matricula = interfaz.tfMatricula.getText();
         
           
           
           
           String variables[]=obj.LLENAR(matricula);
            String fecha = funcs.hora();
              
           if(variables[1]==null){
        
        
                variables[0]="";
                variables[1]="";
                variables[2]="";
                variables[3]="";
                interfaz.lbFechaHora2.setText("");
                JOptionPane.showMessageDialog(null, "Por favor revisa que la matricula que introdujiste sea validad", "Matricula Invalida", 0);
            String NC = (variables[0]+" "+variables[1]+" "+variables[2]+" "+variables[3]);
            interfaz.lbNombre2.setText(NC);
            interfaz.lbCarrera2.setText("");
            interfaz.lbIngreso2.setText("");
          

            }else {
           
           
           for (String variable : variables) {
           
                System.out.println("esto "+ variable);
            }
            String NC = (variables[0]+" "+variables[1]+" "+variables[2]+" "+variables[3]);
            interfaz.lbNombre2.setText(NC);
            interfaz.lbCarrera2.setText(variables[4]);
            interfaz.lbIngreso2.setText(variables[5]);
          
            
            
            interfaz.lbFechaHora2.setText(fecha);
           
          
            }

            //InterfazFunciones.validacion(matricula);
           
           
          
        }
         else if (e.getSource()==interfaz.btnRegistrar){
           
          //consultar  
           String VAreas[] = new String[8];
           VAreas[0]="";
           
           if (interfaz.cbAdministracion.isSelected()==true){
               VAreas[0]=interfaz.cbAdministracion.getText();
           }else {
               //NADA
           }
          
           if (interfaz.cbBiblioteca.isSelected()==true){
               VAreas[1]=interfaz.cbBiblioteca.getText();
           }else {
               //NADA
           }
  
           if (interfaz.cbCubiculos.isSelected()==true){
               VAreas[2]=interfaz.cbCubiculos.getText();
           }else {
               //NADA
           }
          
           if (interfaz.cbLectura.isSelected()==true){
               VAreas[3]=interfaz.cbLectura.getText();
           }else {
               //NADA
           }
        
           if (interfaz.cbPrestamo.isSelected()==true){
               VAreas[4]=interfaz.cbPrestamo.getText();
           }else {
               //NADA
           }
           if ( interfaz.cbServicios.isSelected()==true){
               VAreas[5]=interfaz.cbServicios.getText();
           }else {
               //NADA
           }
     
 

          if (interfaz.cbZonaOtros1.isSelected()==true){
               VAreas[6]=interfaz.tfOtros.getText();
           }else {
               //NADA
           }
                        
           if (interfaz.cbSala.isSelected()==true){
               VAreas[7]=interfaz.cbSala.getText();
           }
               else{
               //NADA
           }
           
         
           
       
           
           
            
            String matricula = interfaz.tfMatricula.getText();
            
            obj.registro_SQL(obj.LLENAR(matricula), matricula, VAreas);
          
            
          
            JOptionPane.showMessageDialog(FrameAdmi, "Registro Completado");
            
            interfaz.lbNombre2.setText("");
            interfaz.lbCarrera2.setText("");
            interfaz.lbFechaHora2.setText("");
            interfaz.lbIngreso2.setText("");
            
            interfaz.cbAdministracion.setSelected(false);
            interfaz.cbBiblioteca.setSelected(false);
            interfaz.cbCubiculos.setSelected(false);
            interfaz.cbLectura.setSelected(false);
            interfaz.cbPrestamo.setSelected(false);
            interfaz.cbSala.setSelected(false);
            interfaz.cbServicios.setSelected(false);
            interfaz.cbZonaOtros1.setSelected(false);
            interfaz.tfOtros.setText("");
            
          // hola.LLENAR(matricula);
          
         // hola.registro_SQL(matricula);
           
            
        }
         else if (e.getSource()==vis.cbOtros){
             
             if(vis.cbOtros.isSelected()!=true){
                  vis.tfOtros.setEnabled(false);
             }else{
                   vis.tfOtros.setEnabled(true);
                 
             }
             
         }
         
          else if (e.getSource()==interfaz.cbZonaOtros1){
             
             if(interfaz.cbZonaOtros1.isSelected()==true){
                  interfaz.tfOtros.setEnabled(true);
             }else{
                 interfaz.tfOtros.setEnabled(false);
             }
             
         }
         
         else if (e.getSource()==vis.btnRegresarVisitante) {
             vis.dispose();
         }
         
       
             else if (e.getSource()==vis.botonRegistrarVIS){
           
          //consultar  
           String VA[] = new String[8];
           VA[0]="";
           
           if (vis.cbAdministracion.isSelected()==true){
               VA[0]=vis.cbAdministracion.getText();
           }else {
               //NADA
           }
          
           if (vis.cbBiblioteca.isSelected()==true){
               VA[1]=vis.cbBiblioteca.getText();
           }else {
               //NADA
           }
  
           if (vis.cbCubiculos.isSelected()==true){
               VA[2]=vis.cbCubiculos.getText();
           }else {
               //NADA
           }
          
           if (vis.cbLectura.isSelected()==true){
               VA[3]=vis.cbLectura.getText();
           }else {
               //NADA
           }
        
           if (vis.cbPrestamo.isSelected()==true){
               VA[4]=vis.cbPrestamo.getText();
           }else {
               //NADA
           }
           if ( vis.cbServicios.isSelected()==true){
               VA[5]=vis.cbServicios.getText();
           }else {
               //NADA
           }
     
           if (vis.cbOtros.isSelected()==true){
               VA[6]=vis.tfOtros.getText();
           }else {
               //NADA
           }
           if (vis.cbSala.isSelected()==true){
               VA[7]=vis.cbSala.getText();
           }
               else{
               //NADA
           }
           
         
           
       
           
           
            visfun.registro_SQL_VIS(VA);
          
            
          
            JOptionPane.showMessageDialog(FrameAdmi, "Registro Completado");
       
            
            vis.cbAdministracion.setSelected(false);
            vis.cbBiblioteca.setSelected(false);
            vis.cbCubiculos.setSelected(false);
            vis.cbLectura.setSelected(false);
            vis.cbPrestamo.setSelected(false);
            vis.cbSala.setSelected(false);
            vis.cbServicios.setSelected(false);
            vis.cbOtros.setSelected(false);
            vis.tfOtros.setText("");
         
         }
         
         
         else if (e.getSource()==interfaz.btnSalirRegistro){
             interfaz.dispose();
             
         }
        
      
        else if (e.getSource()== lg.btnCerraVentanaLogin)
        {
            lg.dispose();//cierra ventana login
        }
       
        
        
        
        
        //se compara de donde vino la accion en este caso si vino del btnRegistar del FRAME DEL ADMINISTRADOR 
        //entra en la condicion
        /*
        NOTA: APARTIR DE AQUI CADA QUE OBTIENE ALGO DE UN TXTFIELD
        SE LLAMA A mod2 QUE HACE REFENCIA PARRA PODER LLAMAR A CUALQUIER METODO DENTRO DE LAS CLASES
        USUARIO FUNCIONES Y SENENVIA UN NUMERO 
        
        EN mod SOLO SE GUARDAN VALORES en la clase USUARIOS
        */
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
            FrameAdmi.cargarUsuarios();//se actualiza la tabla llamando al metodo cargar usuarios 
            
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
            String fecha_inicio="";
            String fecha_Final="";
            try{
            String formato = FrameAdmi.dateChoserInicio.getDateFormatString();
            java.util.Date date = FrameAdmi.dateChoserInicio.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            fecha_inicio = String.valueOf(sdf.format(date));
            System.out.println(fecha_inicio+"dato tomado bien");
            }catch(Exception r)
            {
                System.out.println(fecha_inicio+"dato tomado bien");
            }
            
            try{
            String formato2 = FrameAdmi.dateChoserFinal.getDateFormatString();
            java.util.Date date2 = FrameAdmi.dateChoserFinal.getDate();
            SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
            fecha_Final = String.valueOf(sdf2.format(date2));
            }catch(Exception h)
            {
                System.out.println(fecha_Final+"dato tomado bien");
            }
            
            mod.setFechaInicio(fecha_inicio);
            mod.setFechaFinal(fecha_Final);

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
