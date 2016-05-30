/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DemiXsoft
 */
public class Helper {
    
    public String getCurrentTime()
    {
        Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	return sdf.format(cal.getTime());
    }
    
    public String getCurentDate()
    {
        Date d = new Date();
        return d.toString();
    }
    
    public Date toDateFormat(String d) throws ParseException
    {
        Date date = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH).parse(d);
        return date;
    }
    
    public Date toTimeFormat(String d) throws ParseException
    {
        Date date = new SimpleDateFormat("hh:mm:ss", Locale.ENGLISH).parse(d);
        return date;
    }
    
    public String getCurrentUserID()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String name = (String) session.getAttribute("id");
        return name;
    }
    
    public int toInt(String text)
    {
        return Integer.parseInt(text);
    }
    
    public void navigate(String pageName) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pageName); 
    }
    
    public void pop_msg(String message)
    {
        FacesMessage msg = new FacesMessage(message);  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String getParam(String ParamName)
    {
        Map<String, String> parameterMap;
        ExternalContext e = FacesContext.getCurrentInstance().getExternalContext();;
        parameterMap = (Map<String, String>) e.getRequestParameterMap();
        String param = parameterMap.get(ParamName);
        return param;
    }
}
