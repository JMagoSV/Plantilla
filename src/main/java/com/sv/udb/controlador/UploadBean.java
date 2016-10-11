/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.utils.Archivo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Mauricio
 */
@Named(value = "uploadBean")
@ViewScoped
public class UploadBean implements Serializable {
    private Part file;
    List<Archivo> listNombFile;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public List<Archivo> getListNombFile() {
        return listNombFile;
    }
    
    /**
     * Creates a new instance of UploadBean
     */
    
    public UploadBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.listNombFile = new ArrayList<>();
    }
    
    public void uploFile()
    {
        try
        {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            for(Part item : request.getParts())
            {
                if(item.getName().equals(file.getName()))
                {
                    this.listNombFile.add(new Archivo(item.getSubmittedFileName(), item.getInputStream(), item.getContentType()));
                }
            }
        }
        catch(Exception ex)
        {
            
        }
    }
    
}
