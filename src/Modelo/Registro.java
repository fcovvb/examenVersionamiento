package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Registro {
    int codigo;
    int rut;
    String nombre;
    String apellido;
    int celular;
    String correo;
    int sueldobruto;
    String est_civil;
    String nom_depto;
    
    
    public Registro(int codigo, int rut, String nombre, String apellido,  int celular, String correo, int sueldobruto, String est_civil, String nom_depto) {
        this.codigo = codigo;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.sueldobruto = sueldobruto;
        this.est_civil = est_civil;
        this.nom_depto = nom_depto;
    }

    public Registro(){ }
    
    Sql conectara = new Sql();
    //
    public boolean agregar(int codigo, String rut, String nombre, String apellido,  int celular, String correo, int sueldobruto, String est_civil, String nom_depto){
        if( valida_datos(codigo, rut, nombre, apellido, celular, sueldobruto) ) {
            //Se arma la consulta
            String q=" INSERT INTO examenversionamiento.empleados(codigo,rut,nombre,apellido,celular,email, sueldo_bruto, est_civil, nom_depto) "
                    + "VALUES ( '" + codigo + "','" + rut + "','"+ nombre +"','" + apellido +"','" + celular +"','" + correo +"','" + sueldobruto +"','" + est_civil +"','" + nom_depto +"');";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = conectara.conectar().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
        else
         return false;
    }
    
    public boolean eliminar(int codigo){
        boolean res=false;
        String q = " DELETE FROM examenversionamiento.empleados WHERE codigo=" + codigo + "; " ;
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            if (pstm.executeUpdate() == 1){
                res=true;
            }
            pstm.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    
    public boolean modificar(int codigo, String rut, String nombre, String apellido,  int celular, String correo, int sueldobruto, String est_civil, String nom_depto){
        String q= "UPDATE examenversionamiento.empleados SET nombre='"+nombre+"', rut='"+rut+"', apellido='"+apellido+"', celular ='"+celular+"', email ='"+correo+"', sueldo_bruto ='"+sueldobruto+"', nom_depto ='"+nom_depto+"'WHERE codigo='"+codigo+"';";
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return false;
    }
    /*
    public DefaultTableModel buscar(int codigo){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Código","Nombre","Categoría","Precio","Calidad 4k"};
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM taller3.pelicula");
         ResultSet res = pstm.executeQuery();
         
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      
      Object[][] data = new String[registros][9];
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM taller3.pelicula WHERE codigo ="+codigo+";");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = nombre_categoria (Integer.parseInt(res.getString( "id_categoria" )));
                data[i][3] = res.getString( "precio" );
                data[i][4] = res.getString( "formato4k" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }*/
    
    public DefaultTableModel mostrar(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Código","RUT","Nombre","Apellido","Celular","Correo","Sueldo Bruto", "Est Civil","Depto"};
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM examenversionamiento.empleados");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][9];//***************************************************
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM examenversionamiento.empleados");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){ 
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "rut" );
                data[i][2] = res.getString("nombre");
                data[i][3] = res.getString( "apellido" );
                data[i][4] = res.getString( "celular" );
                data[i][5] = res.getString( "email" );
                data[i][6] = res.getString( "sueldo_bruto" );
                data[i][7] = res.getString( "est_civil" );
                data[i][8] = res.getString( "nom_depto" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    //Metodo para validar datos *********************************************************************
    private boolean valida_datos(int codigo, String rut, String nombre, String apellido, int celular, int sueldobruto){ //no valida depto o estado civil, ya que la vista no permite otros valores.
        
        if(codigo<=0 || codigo >100 || rut.length()<1 || nombre.length() < 1 || apellido.length()< 1 || celular < 99999999 || celular >= 1000000000 || sueldobruto < 120000 ){
            return false;
        }else {
        return false;}
    }
    
     public DefaultTableModel Consulta2(){
     DefaultTableModel tablemodel = new DefaultTableModel();
     int registros = 0;
     String[] columNames = {"Código","RUT","Nombre","Apellido","Celular","Correo","Sueldo Bruto", "Est Civil","Depto"};
     try{
        PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM examenversionamiento.empleados WHERE nom_depto = 'Redes';"); //7 = romance
        ResultSet res = pstm.executeQuery();
        res.next();
        registros = res.getInt("total");
        res.close();
     }catch(SQLException e){
        System.err.println( e.getMessage() );
     }
     Object[][] data = new String[registros][9];
     try{
        PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM examenversionamiento.empleados WHERE nom_depto = 'Redes';");
        ResultSet res = pstm.executeQuery();
        int i=0;
        while(res.next()){
               data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "rut" );
                data[i][2] = res.getString("nombre");
                data[i][3] = res.getString( "apellido" );
                data[i][4] = res.getString( "celular" );
                data[i][5] = res.getString( "email" );
                data[i][6] = res.getString( "sueldo_bruto" );
                data[i][7] = res.getString( "est_civil" );
                data[i][8] = res.getString( "nom_depto" );
           i++;
        }
        res.close();
        tablemodel.setDataVector(data, columNames );
        }catch(SQLException e){
           System.err.println( e.getMessage() );
       }
       return tablemodel;
   }
    public boolean Consulta3(){
        boolean res=false;
        String q = " DELETE FROM examenversionamiento.empleados WHERE sueldo_bruto = 120000; " ;
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            if (pstm.executeUpdate() == 1){
                res=true;
            }
            pstm.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
}
