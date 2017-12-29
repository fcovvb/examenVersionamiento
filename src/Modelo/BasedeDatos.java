package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class BasedeDatos {
    Connection conectar=null;
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection(""
                    + "jdbc:mysql://superbetmatch.mysql.database.azure.com","fcovvb@superbetmatch","Tabla22#");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conectar;
    }
}