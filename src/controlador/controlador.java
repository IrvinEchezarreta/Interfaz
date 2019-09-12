/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuarios;
import modelo.UsuariosFunciones;
import vista.PanelAdmin;
/**
 *
 * @author HP
 */
public class controlador implements ActionListener
{
    private Usuarios mod;
    private UsuariosFunciones mod2;
    private PanelAdmin FrameAdmi;
    
    public controlador(Usuarios mod, UsuariosFunciones mod2, PanelAdmin FrameAdmi)
    {
        System.out.println("entro al contructor");
        this.mod=mod;
        this.mod2=mod2;
        this.FrameAdmi=FrameAdmi;
        this.FrameAdmi.btnRegistrar.addActionListener(this);
                
        
    }
    
    public void iniciar()
    {
        FrameAdmi.setTitle("Administrador");
        FrameAdmi.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== FrameAdmi.btnRegistrar)
        {
            System.out.println("entro a la accion");
            mod.setNombreUsuario(FrameAdmi.txtNombresU.getText());
            mod.setApellidoPaternoU(FrameAdmi.txtApellidoPaternoU.getText());
            mod.setApellidoMaternoU(FrameAdmi.txtApellidoMaternoU.getText());
            mod.setUsuarioU(FrameAdmi.txtUsuarioU.getText());
            mod.setContraseña(FrameAdmi.txtUsuarioU.getText());
            mod2.registrar(mod);
            
        }
        
        
    }
    
    
}
