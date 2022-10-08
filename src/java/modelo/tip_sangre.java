/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nacho
 */
public class tip_sangre {
    
    private Stringid_tip_sangre, sangre;
    Conexion cn;
    private String sangre;

    public tip_sangre() {
    }

    public tip_sangre(String id_tip_sangre, String sangre) {
        this.sangre =id_tip_sangre;
        this.sangre = sangre;
    }

    public String getId_tip_sangre() {
        returnid_tip_sangre;
    }

    public void setId_tip_sangre(Stringid_tip_sangre) {
        this.id_tipid_tip_sangre =id_tip_sangre;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }
         public HashMap drop_sangre(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="SELECT  id_tip_sangre as id,sangre FROM tip_sangre;";
         cn = new Conexion();
         cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("id"),consulta.getString("sangre") );
            }
         cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
    
    
}
