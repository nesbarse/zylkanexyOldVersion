/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.TipousuarioDao;

/**
 *
 * @author a022595832b
 */
public class View04Bean implements GenericBean{
    
    @Expose
    String nombre = "";
    
    @Expose
    String apellido1 = "";
    
    @Expose
    String apellido2 = "";
    
    @Expose(serialize = false)
    Integer id_tipousuario;
    
    @Expose(deserialize = false)
    private TipousuarioBean obj_tipousuario = null;

    public TipousuarioBean getObj_tipousuario() {
        return obj_tipousuario;
    }

    public void setObj_tipousuario(TipousuarioBean obj_tipousuario) {
        this.obj_tipousuario = obj_tipousuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Integer getId_tipousuario() {
        return id_tipousuario;
    }

    public void setId_tipousuario(Integer id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "nombre,";
        strColumns += "apellido1,";
        strColumns += "apellido2,";
        strColumns += "id_tipousuario";
        return strColumns;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += nombre + ",";
        strColumns += apellido1 + ",";
        strColumns += apellido2 + ",";
        strColumns += id_tipousuario;
        return strColumns;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "nombre=" + nombre + ",";
        strPairs += "apellido1=" + apellido1 + ",";
        strPairs += "apellido2=" + apellido2 + ",";
        strPairs += "id_tipousuario=" + id_tipousuario;
        return strPairs;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public View04Bean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setNombre(oResultSet.getString("nombre"));
        this.setApellido1(oResultSet.getString("apellido1"));
        this.setApellido2(oResultSet.getString("apellido2"));
        if (expand > 0) {
            TipousuarioBean oTipousuarioBean = new TipousuarioBean();
            TipousuarioDao oTipousuarioDao = new TipousuarioDao(pooledConnection);
            oTipousuarioBean.setId(oResultSet.getInt("id_tipousuariousuario"));
            oTipousuarioBean = oTipousuarioDao.get(oTipousuarioBean, expand - 1);
            this.setObj_tipousuario(oTipousuarioBean);
        } else {
            this.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
        }
        return this;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
