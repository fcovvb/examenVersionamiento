
package Controlador;

import Modelo.Registro;
import Vista.Agregar;
import Vista.Eliminar;
import Vista.Empleados;
import Vista.InterfazNueva;
import Vista.Mostrar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JOptionPane;


public class Controlador implements ActionListener,MouseListener{

    Empleados interfaz = new Empleados();
    public static Agregar interfazagregar = new Agregar();
    public static Mostrar interfazmostrar = new Mostrar();
    public static Eliminar interfazeliminar = new Eliminar();
    public static InterfazNueva interfaznueva = new InterfazNueva();
    
    //modelo
    private Registro modelo = new Registro();
    
    public enum Accion{
        boton_agregar,
        boton_eliminar,
        boton_buscar,
        boton_modificar,
        boton_limpiar1,
        boton_consulta1,
        boton_consulta2,
        boton_consulta3,
        boton_consulta4,
        
    }
    
    public Controlador() {
         try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(interfaz);
            SwingUtilities.updateComponentTreeUI(interfazagregar);
            SwingUtilities.updateComponentTreeUI(interfazmostrar);
            SwingUtilities.updateComponentTreeUI(interfazeliminar);
            SwingUtilities.updateComponentTreeUI(interfaznueva);
            this.interfaz.setTitle("Base Empleados");
            interfazagregar.setTitle("Agregar Empleado");
            interfazmostrar.setTitle("Modificar / Buscar Empleado");
            interfazeliminar.setTitle("Eliminar Empleado");
            interfaznueva.setTitle("Interfaz de Consultas");
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        interfaz.setVisible(true);
    }
    
    public void iniciar(){
        //Escuchamos los botones
        interfazagregar.boton_agregar.setActionCommand( "boton_agregar" );
        interfazagregar.boton_agregar.addActionListener(this);
        interfazeliminar.boton_eliminar.setActionCommand( "boton_eliminar" );
        interfazeliminar.boton_eliminar.addActionListener(this);
        interfazmostrar.boton_buscar.setActionCommand( "boton_buscar" );
        interfazmostrar.boton_buscar.addActionListener(this);
        interfazmostrar.boton_modificar.setActionCommand( "boton_modificar" );
        interfazmostrar.boton_modificar.addActionListener(this);
        //interfaznueva.boton_consulta1.setActionCommand( "boton_consulta1" );
        //interfaznueva.boton_consulta1.addActionListener(this);
        //interfaznueva.boton_consulta2.setActionCommand( "boton_consulta2" );
        //interfaznueva.boton_consulta2.addActionListener(this);
       // interfaznueva.boton_consulta4.setActionCommand( "boton_consulta4" );
        //interfaznueva.boton_consulta4.addActionListener(this);
        //interfaznueva.boton_limpiar.setActionCommand( "boton_limpiar" );
        //interfaznueva.boton_limpiar.addActionListener(this);
        
        //Interactuar con la tabla
        interfazmostrar.tabla.addMouseListener(this);
    }

    //asignamos acciones a los clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        switch ( Accion.valueOf( e.getActionCommand() ) ){
            case boton_agregar:
               //AGREGAR NUEVO EMPLEADO              
                //codigo
                int codigoempleado = 0;
                if (interfazagregar.tf_codigo.getText().length() >= 1 && (interfazagregar.tf_codigo.getText().length()<3 ||interfazagregar.tf_codigo.getText().equals("100"))){
                    codigoempleado = Integer.valueOf(interfazagregar.tf_codigo.getText());
                }
                
                // run
                int run = 0;
                if (interfazagregar.tf_run.getText().length() > 0){
                    run = Integer.valueOf(interfazagregar.tf_run.getText());
                    
                }
                
                // nombre
                if (interfazagregar.tf_nombre.getText().length() > 0){
                 String nombreempleado = interfazagregar.tf_nombre.getText();   
                }
                
                // apellido
                if (interfazagregar.tf_apellido.getText().length() > 0){
                 String apellidoempleado = interfazagregar.tf_nombre.getText();   
                }
                
                
                // celular
                int celular = 0;
                if (interfazagregar.tf_celular.getText().length() == 9){
                    celular = Integer.valueOf(interfazagregar.tf_celular.getText());
                }
                
                // sueldo
                int sueldo = 0;
                if (Integer.valueOf(interfazagregar.tf_sueldo.getText()) >= 120000){
                    sueldo = Integer.valueOf(interfazagregar.tf_sueldo.getText());
                }
                
                // correo
                if (interfazagregar.tf_correo.getText().length() > 0){
                 String correoempleado = interfazagregar.tf_correo.getText();   
                }
                
                // estado civil
                String estado = "";
                String estadocivil = interfazagregar.combo_estado.getSelectedItem().toString();
                if (estadocivil.equals("casado")){
                    estado = "C";
                }
                else
                if (estadocivil.equals("soltero")){
                       estado = "S";
                }
                else
                if (estadocivil.equals("viudo")){
                      estado = "V";
                }
                    
                // Departamento
                
                String departamento = interfazagregar.combo_departamento.getSelectedItem().toString();
                if (departamento.equals("Informática")){
                    departamento = "Informática";
                }
                else
                if (departamento.equals("Redes")){
                    departamento = "Redes";
                }
                else
                if (departamento.equals("Administración")){
                    departamento = "Administración";
                }
                else
                if (departamento.equals("Finanzas")){
                    departamento = "Finanzas";
                }
                else
                if (departamento.equals("Bienestar")){
                    departamento = "Bienestar";
                }    
                    
                // CHEQUEAMOS REGLAS DE NEGOCIO
                 

            break;
            case boton_eliminar:
                if (interfazeliminar.tf_eliminar.getText().length() > 0){
                    //asdf
                   if(this.modelo.eliminar(Integer.valueOf(interfazeliminar.tf_eliminar.getText())) == true) {
                       //eliminado
                       JOptionPane.showMessageDialog(null, "Se ha eliminado el registro", "Empleado Eliminado", JOptionPane.INFORMATION_MESSAGE);
                   } else {
                       JOptionPane.showMessageDialog(null, "No existe un empleado con ese código", "Error", JOptionPane.WARNING_MESSAGE);
                   }
                }
                
            break;
            case boton_buscar:
                if (interfazmostrar.tf_buscar.getText().length() > 0){
                    //aa
                    interfazmostrar.tabla.setModel(this.modelo.buscar(Integer.valueOf(interfazmostrar.tf_buscar.getText())));
                    if (this.modelo.buscar(Integer.valueOf(interfazmostrar.tf_buscar.getText())).getValueAt(0, 0) == null){
                        JOptionPane.showMessageDialog(null, "No existe un empleado con ese código", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    interfazmostrar.tabla.setModel(this.modelo.mostrar());
                }
            break;
            case boton_modificar:
                /* MODIFICAR PELICULA DESDE LA TABLA */
               //codigo
                int modificarcodigo = 0;
                if (interfazmostrar.tf_modificar_codigo.getText().length() >= 1 && (interfazmostrar.tf_modificar_codigo.getText().length()<3 ||interfazmostrar.tf_modificar_codigo.getText().equals("100"))){
                    modificarcodigo = Integer.valueOf(interfazmostrar.tf_modificar_codigo.getText());
                }
                
                // nombre, apellido y correo
                String modificarnombre = interfazmostrar.tf_modificar_nombre.getText();
                
                String modificarapellido = interfazmostrar.tf_modificar_apellido.getText();
                
                String modificarcorreo = interfazmostrar.tf_modificar_correo.getText();
                
                 //run
                int modificarrun = 0;
                if (interfazmostrar.tf_modificar_run.getText().length() > 0){
                    modificarrun = Integer.valueOf(interfazmostrar.tf_modificar_run.getText());
                }
                
                //Modifica estado civil
                String estadocivilm = "";
                String modificarestadocivil = interfazmostrar.combo_modifica_estado.getSelectedItem().toString();
                if (modificarestadocivil.equals("casado")){
                    estadocivilm = "C";
                } else if (modificarestadocivil.equals("soltero")){
                     estadocivilm = "S";
                } else if (modificarestadocivil.equals("viudo")){
                    estadocivilm = "V";
                }
            
                //Modifica departamento
                String departamentom = "";
                String modificadepartamento = interfazmostrar.combo_modifica_departamento.getSelectedItem().toString();
                if (modificadepartamento.equals("Informática")){
                    departamentom = "Informática";
                }
                else
                if (modificadepartamento.equals("Redes")){
                    departamentom = "Redes";
                }
                else
                if (modificadepartamento.equals("Administración")){
                    departamentom = "Administración";
                }
                else
                if (modificadepartamento.equals("Finanzas")){
                    departamentom = "Finanzas";
                }
                else
                if (modificadepartamento.equals("Bienestar")){
                    departamento = "Bienestar";
                } 

                
                
                //si estan llenos todos los campos ejecutamos la query
                /*if (modificarcategoria.equals("Categoría") == false &&
                *    modificarcodigo > 0 &&
                *    modificarnombre.length() > 0 &&
                *    modificarprecio > 0) {
                    /* int codigo, int categoria, String nombre, int precio, String formato4k */
                    // hacer la query
                /**    this.modelo.modificar(modificarcodigo,modificaridcategoria,modificarnombre,modificarprecio,modificarformato);
                *    JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "Modificar Pelìcula", JOptionPane.OK_OPTION);
                *} else {
                *    JOptionPane.showMessageDialog(null, "Por favor llenar todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
                *}
                *interfazmostrar.tabla.setModel(this.modelo.mostrar());*/
              
            break;
            case boton_limpiar1: // consulta 1
                //limpiar
                interfazagregar.tf_codigo.setText(null);
                interfazagregar.tf_run.setText(null);
                interfazagregar.tf_nombre.setText(null);
                interfazagregar.tf_apellido.setText(null);
                interfazagregar.tf_celular.setText(null);
                interfazagregar.tf_sueldo.setText(null);
                interfazagregar.tf_codigo.grabFocus();
                
            break;
            
               
        }  
    }
    
    //asignamos acciones cuando se clickea la tabla
    @Override
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1){
            //Muestro datos de producto a modificar
             int fila = interfazmostrar.tabla.rowAtPoint(e.getPoint());
             if (fila > -1){
                /* aqui poblamos los campos segun el modelo de la tabla */
                
                /*
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = nombre_categoria (Integer.parseInt(res.getString( "id_categoria" )));
                data[i][3] = res.getString( "precio" );
                data[i][4] = res.getString( "formato4k" );
                */
                
                interfazmostrar.tf_modificar_codigo.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 0) ));
                interfazmostrar.tf_modificar_nombre.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 1) ));                
                
                
                /*int selectedindex = 0;
                if (interfazmostrar.tabla.getValueAt(fila, 2).toString().equals("Largometraje")){
                     selectedindex = 1;
                } else if (interfazmostrar.tabla.getValueAt(fila, 2).toString().equals("Infantil")){
                   selectedindex = 2; 
                } else if (interfazmostrar.tabla.getValueAt(fila, 2).toString().equals("Documental")){
                    selectedindex = 3;
                } else if (interfazmostrar.tabla.getValueAt(fila, 2).toString().equals("Musical")){
                    selectedindex = 4;
                }
                interfazmostrar.cb_modificar_categoria.setSelectedIndex(selectedindex);
                interfazmostrar.tf_modificar_precio.setText(String.valueOf(interfazmostrar.tabla.getValueAt(fila, 3) ));
                if (interfazmostrar.tabla.getValueAt(fila, 4).toString().equals("S")){
                    interfazmostrar.radio_modificar_si.setSelected(true);
                    interfazmostrar.radio_modificar_no.setSelected(false);
                } else {
                    interfazmostrar.radio_modificar_no.setSelected(true);
                    interfazmostrar.radio_modificar_si.setSelected(false);
                }
                */
             }
        }
    
    
    }
    
    /* de aqui para abajo no se usa */
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
     
    
}
