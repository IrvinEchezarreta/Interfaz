/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasRAFA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import modelo.modeloInterfaz;

/**
 *
 * @author molef
 */
public class PruebasSQL {
    
    public static String Nombre;
    public static String Segundo_Nombre;
    public static String Apellido_Paterno;
    public static String Apellido_Materno;
    public static String Semestre;
    public static String Carrera;
    public static String Tipo;
    public static String Areas;
    
    private final String usuario="root";
    private final String contraseña="";
    private final String base="testbiblio";
    private final String url = "jdbc:mysql://localhost/testbiblio"+base;
    static Connection con=null;
    static String controlador= "com.mysql.jdbc.Driver";
    
    
    public static void main (String [] Args)
    

    {
   
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    long millis=System.currentTimeMillis();  
    String date = simpleDateFormat.format(new Date());
    java.sql.Time time=new java.sql.Time(millis);  

    
    try
        {
     
      // Crear conexion a base de datos SQL
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost/testbiblio";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
    
     
      // se crea un objeto SQL que nos permita insertar fecha y hora en la base de datos
   
    
      
      //java.sql.Date date=new java.sql.Date(millis);  
     
      String Matricula = "E16080560";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT NOMBRE,SEGUNDO_NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,NOMBRE_CARRERA,SEMESTRES FROM alumnos WHERE MATRICULA = '"+Matricula+"'");
      
    while (rs.next()) {
    Nombre = rs.getString("NOMBRE");
    Segundo_Nombre = rs.getString("SEGUNDO_NOMBRE");
    Apellido_Paterno = rs.getString("APELLIDO_PATERNO");
    Apellido_Materno = rs.getString("APELLIDO_MATERNO");
    Carrera = rs.getString("NOMBRE_CARRERA");
    Semestre = rs.getString("SEMESTRES");
    
    if (Carrera.contains("LICENCIATURA"))
    {
        Tipo = "Licenciatura";
    }else if (Carrera.contains("INGENIERÍA")){
        Tipo = "Licenciatura";
    }else {
        Tipo = "Postgrado";
    }
    
    
         System.out.print("Nombre: " + Nombre);
         System.out.println(", Apellido: " + Apellido_Paterno);
         System.out.print("Carrera: " + Carrera);
         System.out.print(", Ingreso: " + Semestre +"\n");
         System.out.println("nivel :"+Tipo);
         System.out.println("fecha:"+date);
         
         
         
         
    }
    
         
        try{    
             
            
             

              //Matricula/Nombre/Segundo_Nombre/Apellido_Paterno/Apellido_Materno/Nombre_Carrera/Semestre/Tipo/AreasVisitadas/Fecha/Hora
              //La cadena SQL que sera introducida
              String query = " insert into registros (Matricula,Nombre,Segundo_Nombre,Apellido_Paterno,Apellido_Materno,Nombre_Carrera,Semestre,Tipo,AreasVisitadas,Fecha,Hora)"
                + " values (?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,?)";

              // create the mysql insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, Matricula);
            

              preparedStmt.setString (2, Nombre);
                System.out.println("AQUI ESTA EL ERROR :"+Nombre);
              preparedStmt.setString (3, Segundo_Nombre);
                System.out.println("AQUI ESTA EL ERROR :"+Segundo_Nombre);
              preparedStmt.setString (4, Apellido_Paterno);
                System.out.println("AQUI ESTA EL ERROR :"+Apellido_Paterno);
              preparedStmt.setString (5, Apellido_Materno);
                System.out.println("AQUI ESTA EL ERROR :"+Apellido_Materno);
              preparedStmt.setString (6, Carrera);
                System.out.println("AQUI ESTA EL ERROR :"+Carrera);
              preparedStmt.setString (7, Semestre);
                System.out.println("AQUI ESTA EL ERROR :"+Semestre);
              preparedStmt.setString (8, "LICENCIATURA");
              preparedStmt.setString (9, "ALGO");
              preparedStmt.setString (10, date);
              preparedStmt.setTime(11, time);

              // Se ejecuta la orden preparada
                preparedStmt.execute();

      
      conn.close();
        }
        
        
        
        
        
        
        
         catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
        System.out.println("AQUI ESTA EL ERROR : LLENAR");
    }
        
        
        
        
        
   
  }
         catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
        System.out.println("AQUI ESTA EL ERROR : SQL");
    }

    }
}

    
    

