/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */
package net.daw.service.implementation;

import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.implementation.AlumnoBean;
import net.daw.bean.implementation.ReplyBean;
import net.daw.bean.implementation.TipousuarioBean;
//import net.daw.bean.implementation.TipousuarioBean;
import net.daw.bean.implementation.UsuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.dao.implementation.AlumnoDao;
import net.daw.dao.implementation.TipousuarioDao;
//import net.daw.dao.implementation.TipousuarioDao;
import net.daw.helper.statics.AppConfigurationHelper;
import static net.daw.helper.statics.AppConfigurationHelper.getSourceConnection;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.JsonMessage;
import net.daw.helper.statics.Log4j;
import net.daw.helper.statics.ParameterCook;
import net.daw.service.publicinterface.TableServiceInterface;
import net.daw.service.publicinterface.ViewServiceInterface;

/**
 *
 * @author a022595832b
 */
public class AlumnoService implements TableServiceInterface, ViewServiceInterface{
    
    protected HttpServletRequest oRequest = null;
    
    public AlumnoService(HttpServletRequest request) {
        oRequest = request;
    }
    
    private Boolean checkpermission(String strMethodName) throws Exception {
        UsuarioBean oUserBean = (UsuarioBean) oRequest.getSession().getAttribute("userBean");
        if (oUserBean != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ReplyBean get() throws Exception {
        if (this.checkpermission("get")) {
            int id = ParameterCook.prepareId(oRequest);
            String data = null;
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                AlumnoDao oAlumnoDao = new AlumnoDao(oConnection);
                AlumnoBean oAlumnoBean = new AlumnoBean(id);
                oAlumnoBean = oAlumnoDao.get(oAlumnoBean, AppConfigurationHelper.getJsonDepth());
                Gson gson = AppConfigurationHelper.getGson();
                data = JsonMessage.getJson("200", AppConfigurationHelper.getGson().toJson(oAlumnoBean));
            } catch (Exception ex) {
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return new ReplyBean(200, "OK");
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyBean remove() throws Exception {
        if (this.checkpermission("remove")) {
            Integer id = ParameterCook.prepareId(oRequest);
            String data = null;
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                oConnection.setAutoCommit(false);
                AlumnoDao oAlumnoDao = new AlumnoDao(oConnection);
                data = JsonMessage.getJson("200", (String) oAlumnoDao.remove(id).toString());
                oConnection.commit();
            } catch (Exception ex) {
                oConnection.rollback();
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return new ReplyBean(200, "OK");
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyBean set() throws Exception {
        if (this.checkpermission("set")) {
            String jason = ParameterCook.prepareJson(oRequest);
            String data = null;
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                oConnection.setAutoCommit(false);
                AlumnoDao oAlumnoDao = new AlumnoDao(oConnection);
                AlumnoBean oAlumnoBean = new AlumnoBean();
                oAlumnoBean = AppConfigurationHelper.getGson().fromJson(jason, oAlumnoBean.getClass());
                if (oAlumnoBean != null) {
                    Integer iResult = oAlumnoDao.set(oAlumnoBean);
                    if (iResult >= 1) {
                        data = JsonMessage.getJson("200", iResult.toString());
                    } else {
                        data = JsonMessage.getJson("500", "Error during registry set");
                    }
                } else {
                    data = JsonMessage.getJson("500", "Error during registry set");
                }
                oConnection.commit();
            } catch (Exception ex) {
                oConnection.rollback();
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return new ReplyBean(200, "OK");
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyBean getall() throws Exception {
        if (this.checkpermission("getall")) {
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
            HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
            String data = null;
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;

            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                AlumnoDao oAlumnoDao = new AlumnoDao(oConnection);
                ArrayList<AlumnoBean> arrBeans = oAlumnoDao.getAll(alFilter, hmOrder, 1);
                data = JsonMessage.getJson("200", AppConfigurationHelper.getGson().toJson(arrBeans));
            } catch (Exception ex) {
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return new ReplyBean(200, "OK");
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("empty-statement")
    public ReplyBean getpage() throws Exception {
        if (this.checkpermission("getpage")) {
            int intRegsPerPag = ParameterCook.prepareRpp(oRequest);;
            int intPage = ParameterCook.preparePage(oRequest);
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
            HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
            String data = null;
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                AlumnoDao oAlumnoDao = new AlumnoDao(oConnection);
                List<AlumnoBean> arrBeans = oAlumnoDao.getPage(intRegsPerPag, intPage, alFilter, hmOrder, AppConfigurationHelper.getJsonDepth());
                data = JsonMessage.getJson("200", AppConfigurationHelper.getGson().toJson(arrBeans));
            } catch (Exception ex) {
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return new ReplyBean(200, "OK");
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReplyBean getcount() throws Exception {
        if (this.checkpermission("getcount")) {
            String data = null;
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                AlumnoDao oAlumnoDao = new AlumnoDao(oConnection);
                data = JsonMessage.getJson("200", Integer.toString(oAlumnoDao.getCount(alFilter)));
            } catch (Exception ex) {
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return new ReplyBean(200, "OK");
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
