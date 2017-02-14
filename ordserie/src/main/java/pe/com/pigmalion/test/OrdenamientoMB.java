/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pigmalion.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author root
 */

@ManagedBean
@ViewScoped
public class OrdenamientoMB implements Serializable{
    private String mensaje = "";    
    private String valor = "";    
    private List<String> listado = new ArrayList<String>();
    private List<String> resultado = new ArrayList<String>();
    
    public void asignarValor(ActionEvent e){
        if(validar(valor)){
            listado.add(valor);
        }
        valor = "";
    }
    
    public void ordenarAsc(ActionEvent e){
        Set<String> linkHashSet = new LinkedHashSet<String>();
        List<Integer> listaOrdenado = new ArrayList<Integer>();
        List<String> res = new ArrayList<String>();
        linkHashSet.addAll(listado);
        for (String string : linkHashSet) {
            listaOrdenado.add(Integer.valueOf(string));
        }
        Collections.sort(listaOrdenado);
        for (Integer elemento : listaOrdenado) {
            res.add(elemento.toString());
        }
        
        resultado.clear();
        resultado.addAll(res);
    }
    
    public boolean validar(String dato){
        if(StringUtils.isBlank(dato)){            
            FacesContext.getCurrentInstance().addMessage("idmsgs",new FacesMessage(FacesMessage.SEVERITY_ERROR,"El valor no debe ser vacío","El valor no debe ser vacío"));
            return false;
        }
        
        if(StringUtils.isEmpty(dato)){            
            FacesContext.getCurrentInstance().addMessage("idmsgs",new FacesMessage(FacesMessage.SEVERITY_ERROR,"El valor no debe ser nulo","El valor no debe ser nulo"));
            return false;
        }
        
        if(!StringUtils.isNumeric(dato)){            
            FacesContext.getCurrentInstance().addMessage("idmsgs",new FacesMessage(FacesMessage.SEVERITY_ERROR,"El valor no es entero","El valor no es entero"));
            return false;
        }
        
        if(listado.size()>0){            
            if(listado.contains(dato)){                
                FacesContext.getCurrentInstance().addMessage("idmsgs",new FacesMessage(FacesMessage.SEVERITY_ERROR,"El valor ingresado no debe repetirse.","El valor ingresado no debe repetirse."));
                return false;
            }
        }
        return true;
    }
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<String> getListado() {
        return listado;
    }

    public void setListado(List<String> listado) {
        this.listado = listado;
    }

    public List<String> getResultado() {
        return resultado;
    }

    public void setResultado(List<String> resultado) {
        this.resultado = resultado;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
