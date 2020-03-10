/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author HP
 */
public class Usuarios 
{
    private String nombreUsuario;
    private String apellidoPaternoU;
    private String apellidoMaternoU;
    private String usuarioU;
    private String contraseña;
    
    private String nombreArea;
    private String nombreTipo;
    private String Semestre;
    private String Carrera;
    private String FechaInicio;
    private String FechaFinal;

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(String FechaFinal) {
        this.FechaFinal = FechaFinal;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String Semestre) {
        this.Semestre = Semestre;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }         

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaternoU() {
        return apellidoPaternoU;
    }

    public void setApellidoPaternoU(String apellidoPaternoU) {
        this.apellidoPaternoU = apellidoPaternoU;
    }

    public String getApellidoMaternoU() {
        return apellidoMaternoU;
    }

    public void setApellidoMaternoU(String apellidoMaternoU) {
        this.apellidoMaternoU = apellidoMaternoU;
    }

    public String getUsuarioU() {
        return usuarioU;
    }

    public void setUsuarioU(String usuarioU) {
        this.usuarioU = usuarioU;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
       
}
