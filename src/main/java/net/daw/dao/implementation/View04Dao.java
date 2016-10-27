/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.View04Bean;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.helper.statics.FilterBeanHelper;

/**
 *
 * @author a022595832b
 */
public class View04Dao implements ViewDaoInterface<View04Bean>{

    @Override
    public int getCount(ArrayList<FilterBeanHelper> alFilter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<View04Bean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<View04Bean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
