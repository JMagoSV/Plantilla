/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jmagosv
 */
@Named(value = "cmSoft")
@ViewScoped
public class TestBean implements Serializable {
    private String ffechadocumento;
    private String ffecharecibido;
    

    /**
     * Creates a new instance of TestBean
     */
    public TestBean() {
    }

    public String getFfechadocumento() {
        return ffechadocumento;
    }

    public void setFfechadocumento(String ffechadocumento) {
        this.ffechadocumento = ffechadocumento;
    }

    public String getFfecharecibido() {
        return ffecharecibido;
    }

    public void setFfecharecibido(String ffecharecibido) {
        this.ffecharecibido = ffecharecibido;
    }
    
    public void guardar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            System.err.println("Datos:");
            System.err.println(this.ffechadocumento);
            System.err.println(this.ffecharecibido);
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
}
