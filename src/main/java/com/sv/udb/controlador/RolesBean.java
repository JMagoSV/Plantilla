/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.RolesFacadeLocal;
import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Roles;
import com.sv.udb.modelo.Usuarios;
import com.sv.udb.modelo.UsuariosRoles;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mauricio
 */
@Named(value = "rolesBean")
@ViewScoped
public class RolesBean implements Serializable{
    private static final long serialVersionUID = 5074501358281220978L;
    @EJB
    private UsuariosFacadeLocal FCDEUsua;
    @EJB
    private RolesFacadeLocal FCDERole;
    private boolean guardar;
    private boolean showBusc;
    private Usuarios objeUsua;
    private List<Usuarios> listUsua;
    private List<Roles> listRole;
    private List<UsuariosRoles> listUsuaRole;

    public List<Usuarios> getListUsua() {
        return listUsua;
    }

    public List<Roles> getListRole() {
        return listRole;
    }

    public void setListUsuaRole(List<UsuariosRoles> listUsuaRole) {
        this.listUsuaRole = listUsuaRole;
    }

    public List<UsuariosRoles> getListUsuaRole() {
        return listUsuaRole;
    }

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public boolean isShowBusc() {
        return showBusc;
    }
    
    public RolesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void limpForm()
    {
        
        this.objeUsua = new Usuarios();
        this.guardar = true;   
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listUsua = FCDEUsua.findAll();
            this.listRole = FCDERole.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiAlumPara"));
        try
        {
            this.objeUsua = FCDEUsua.find(codi);
            this.listUsuaRole = this.objeUsua.getUsuariosRolesList();
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeUsua.getAcceUsua()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
    
    public void toogBusc()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Cambia al buscador')");
        this.showBusc = !this.showBusc;
    }
}
