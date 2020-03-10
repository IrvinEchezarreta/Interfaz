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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelo.conexion;
import org.apache.poi.ss.formula.functions.Match;

/**
 *
 * @author molef
 */


public class InterfazFunciones extends conexion{
    
    public static String Nombre;
    public static String Segundo_Nombre;
    public static String Apellido_Paterno;
    public static String Apellido_Materno;
    public static String Semestre;
    public static String Carrera;
    public static String Tipo;
    public static String Areas;
       
    
    public String hora (){
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    long millis=System.currentTimeMillis();  
    String date = simpleDateFormat.format(new Date());
    java.sql.Time time=new java.sql.Time(millis);  
    String Fecha=(""+date+"  "+time);
    System.out.println("fecha:"+Fecha);
    return Fecha;
    }
    
    public static boolean validacion(String Matricula){
    
        boolean respuesta;
    
    
        //Expresion regular que representa la matricula 
        // 1 Letra + 8 Numeros 
        String regex = "^[a-zA-Z]+[0-9]{8}";

        //Requisitos del pattern/matcher para hacer funcionar una expresion regular

        //Patron a compilar
        Pattern pattern = Pattern.compile(regex);
        //Contra que se va a comparar el patron   
        Matcher matcher = pattern.matcher(Matricula);

        respuesta = matcher.matches();
        
         if (respuesta==false){
             //MENSAJE DE MATRICULA INVALIDA 
           // JOptionPane.showMessageDialog(null, "Por favor revisa que la matricula que introdujiste sea validad", "Matricula Invalida", 0);
         }else {
             //MATRICULA VALIDA
             System.out.println("Validacion Correcta, Matricula aceptada");
         }
        
        // TEST EN CONSOLA
        // System.out.println(Matricula +" : "+ respuesta);    
        return respuesta;
    }
    
    public void registro_SQL (String datos[],String Matricula,String lugares[]){
        
        //String Consulta = ("SELECT FROM alumnos WHERE MATRICULA ="+Matricula+"");
        //La matricula es valida ???
         boolean valido = validacion(Matricula);
      
          

        //si no es valido, se envia mnsaje de matricula incorrecta
        if (valido==false){
            
             System.out.println("Orz");;

        //si es valida, continuamos  
        }else{
            System.out.println("Matricula valida");
            //JOptionPane.showConfirmDialog(null, "Matricula valida");
            
        try{    
            String pattern = "d/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            long millis=System.currentTimeMillis();  
            String date = simpleDateFormat.format(new Date());
            java.sql.Time time=new java.sql.Time(millis);   

             //  System.out.println("AQUI ESTA EL ERROR : SQL");

              /*  variables[0]=Nombre;
                  variables[1]=Segundo_Nombre;
                  variables[2]=Apellido_Paterno;
                  variables[3]=Apellido_Materno;
                  variables[4]=Carrera;
                  variables[5]=Semestre;*/
              
               Matricula=Matricula;
                System.out.println("Matricula REGISTRO"+Matricula);
               Nombre=datos[0];
               if (Nombre==null){
                                   JOptionPane.showMessageDialog(null, "Por favor valida antes de registrar :) ", "Matricula Invalida", 0);
               }
               System.out.println("Nombre REGISTRO"+Nombre);
               Segundo_Nombre=datos[1];
                 System.out.println("2do Nombre REGISTRO"+Segundo_Nombre);
               Apellido_Paterno=datos[2];
                System.out.println("ApPaterno REGISTRO"+Apellido_Paterno);
               Apellido_Materno=datos[3];
                System.out.println("ApMaterno REGISTRO"+Apellido_Materno);
               Carrera=datos[4];
               System.out.println("Carrrera REGISTRO"+Carrera);
               Semestre=datos[5];
               System.out.println("Semestre REGISTRO"+Semestre);
               if (Carrera.contains("INGENIERÍA")){
                   Tipo="Licenciatura";
               }else if (Carrera.contains("LICENCIATURA")){
                   Tipo="Licenciatura";
               }else if (Carrera.contains("EDUCACIÓN")){
                   Tipo="Licenciatura";
               }
               else if (Carrera.contains("EGEL")){
                   Tipo="Licenciatura";
               }
               else if (Carrera.contains("DOC")){
                   Tipo="Doctorado";
               }
               System.out.println("Tipo REGISTRO"+Tipo);
           /*    
           Areas[0] = interfaz.cbAdministracion.isSelected();
           Areas[1] = interfaz.cbBiblioteca.isSelected();
           Areas[2] = interfaz.cbCubiculos.isSelected();
           Areas[3] = interfaz.cbLectura.isSelected();
           Areas[4] = interfaz.cbLectura.isSelected();
           Areas[5] = interfaz.cbServicios.isSelected();
           Areas[6] = interfaz.cbZonaOtros1.isSelected();*/
           //int contadorAreas = 0;
           Areas = ""; 
           ArrayList<String> Lugares = new ArrayList<String>();
           for (int i = 0; i < lugares.length ; i++) {
                if (lugares[i]==null||lugares[i]==""){
                    
                     
                }else{
                 //    Areas  = Areas + lugares[i] + ",";  
                    Lugares.add(lugares[i]);
                     //contadorAreas = contadorAreas+1;
                }
                
            }
          
 
               
               
              //Matricula/Nombre/Segundo_Nombre/Apellido_Paterno/Apellido_Materno/Nombre_Carrera/Semestre/Tipo/AreasVisitadas/Fecha/Hora
              //La cadena SQL que sera introducida
             
              for (String Lugar : Lugares) {
                   String query = " insert into registros (Matricula,Nombre,Segundo_Nombre,Apellido_Paterno,Apellido_Materno,Nombre_Carrera,Semestre,Tipo,AreasVisitadas,Fecha,Hora)"
                + " values (?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,?)";

                
            
                    PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, Matricula);
              preparedStmt.setString (2, Nombre);
              preparedStmt.setString (3, Segundo_Nombre);
              preparedStmt.setString (4, Apellido_Paterno);
              preparedStmt.setString (5, Apellido_Materno);
              preparedStmt.setString (6, Carrera);
              preparedStmt.setString (7, Semestre);
              preparedStmt.setString (8, Tipo);
              preparedStmt.setString (9, Lugar);
              preparedStmt.setString (10, date);
              preparedStmt.setTime(11, time);
                 System.out.println("FUNCIONO!!");
              // Se ejecuta la orden preparada
                preparedStmt.execute();
              
              
              }
              
              
              // create the mysql insert preparedstatement
            

                  con.close();
            }
                catch (Exception e)
            {
              System.err.println("Got an exception!");
              System.err.println(e.getMessage());
            }



                }








            }
    // se agrego el String [] , sin el frunciona
    public String [] LLENAR (String matricula){
        
    boolean valido=validacion(matricula);
    Connection con = getConexion();
    String [] variables = new String[6];
    
     if (valido==false){
         
     }else {
         
     try{   
      long millis=System.currentTimeMillis();  
      java.sql.Date date=new java.sql.Date(millis);  
      java.sql.Time time=new java.sql.Time(millis);  
      String Matricula = matricula;
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT NOMBRE,SEGUNDO_NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,NOMBRE_CARRERA,SEMESTRES FROM alumnos WHERE MATRICULA = '"+Matricula+"'");
      
    //    System.out.println("AQUI ESTA EL ERROR : LLENAR");
      
    
    
    
    while (rs.next()) {
    Nombre = rs.getString("NOMBRE");
    Segundo_Nombre = rs.getString("SEGUNDO_NOMBRE");
    Apellido_Paterno = rs.getString("APELLIDO_PATERNO");
    Apellido_Materno = rs.getString("APELLIDO_MATERNO");
    Carrera = rs.getString("NOMBRE_CARRERA");
    Semestre = rs.getString("SEMESTRES");
    
    
    
    variables[0]=Nombre;
    variables[1]=Segundo_Nombre;
    variables[2]=Apellido_Paterno;
    variables[3]=Apellido_Materno;
    variables[4]=Carrera;
    variables[5]=Semestre;
    
    
         System.out.print("Nombre: " + Nombre);
         System.out.println(", Apellido: " + Apellido_Paterno);
         System.out.print("Carrera: " + Carrera);
         System.out.print(", Ingreso: " + Semestre +"\n");
    
           
    }
      
      
        }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }      
     
     
  
  }
     
     return variables;
    
    }
    
    
    public void reinicar(){
        
       
        
    }
}
        

    
    
    
       
    
    
    

    
    
 
     
     

