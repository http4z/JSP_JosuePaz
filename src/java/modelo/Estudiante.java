/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nacho
 */
public class Estudiante extends Persona {
    
    private String id_estudiante, carne, id_tipo_sangre;
    
     Conexion cn;

    public Estudiante() {
    }

    public Estudiante(String id_estudiante, String carne, String nombres, String apellidos, String direccion, String telefono, String correo_electronico, String id_tipo_sangre, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, correo_electronico, fecha_nacimiento);
        this.id_estudiante = id_estudiante;
        this.carne = carne;
        this.id_tipo_sangre = id_tipo_sangre;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangre(String id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }
    
    @Override
    public int agregar(){
        int retorno = 0;
    try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO estudiantes(carne,nombres,apellidos,direccion,telefono,correo_electronico,id_tipo_sangre,fecha_nacimiento) values(?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,this.getCarne());
            parametro.setString(2,this.getNombres());
            parametro.setString(3,this.getApellidos());
            parametro.setString(4,this.getDireccion());
            parametro.setString(5,this.getTelefono());
            parametro.setString(6,this.getCorreo_electronico());
            parametro.setString(7,this.getId_tipo_sangre());
            parametro.setString(8,this.getFecha_nacimiento());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
    return retorno;
    }

    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
     cn = new Conexion();
     cn.abrir_conexion();
      String query = "SELECT e.idestudiantes as id,e.carne,e.nombres,e.apellidos,e.direccion,e.telefono,e.correo_electronico,s.sangre,s.id_tipo_sangre, e.fecha_nacimiento FROM estudiantes as e inner join tipos_sangre as s on e.id_tipo_sangre = s.id_tipo_sangre;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","carne","nombres","apellidos","direccion","telefono","correo_electronico","sangre","id_tipo_sangre","fecha_nacimiento"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[10];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("carne");
          datos[2] = consulta.getString("nombres");
          datos[3] = consulta.getString("apellidos");
          datos[4] = consulta.getString("direccion");
          datos[5] = consulta.getString("telefono");
          datos[6] = consulta.getString("correo_electronico");
          datos[7] = consulta.getString("sangre");
          datos[8] = consulta.getString("id_tipo_sangre");
          datos[9] = consulta.getString("fecha_nacimiento");
          tabla.addRow(datos);
      
      }
      
     cn.cerrar_conexion();
    }catch(SQLException ex){
     System.out.println(ex.getMessage());
    }
    return tabla;
    }

    @Override
     public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update estudiantes set carne = ?, nombres= ?, apellidos= ?, direccion= ?,telefono= ?, correo_electronico= ?,id_tipo_sangre = ?, fecha_nacimiento= ? where idestudiantes = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,this.getCarne());
            parametro.setString(2,this.getNombres());
            parametro.setString(3,this.getApellidos());
            parametro.setString(4,this.getDireccion());
            parametro.setString(5,this.getTelefono());
            parametro.setString(6,this.getCorreo_electronico());
            parametro.setString(7,this.getId_tipo_sangre());
            parametro.setString(8,this.getFecha_nacimiento());
            parametro.setString(9,this.getId_estudiante());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
     
   @Override
    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from estudiantes  where idestudiantes = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getId_estudiante());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
 
    
}
