/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.primefaces.model.DefaultScheduleEvent;

/**
 *
 * @author REGISTRO
 */
@Named(value = "calendarioBean")
@ViewScoped
public class CalendarioBean implements Serializable{
    private static final long serialVersionUID = 6527333208194203406L;
    private ScheduleModel objeCale;
    /**
     * Creates a new instance of CalendarioBean
     */
    public CalendarioBean() {
    }

    @PostConstruct
    public void init()
    {
        this.objeCale = new DefaultScheduleModel();
        this.objeCale.addEvent(new DefaultScheduleEvent("Día de pago :)", this.getFecha("28/10/2016 08:00 AM"), this.getFecha("28/10/2016 03:30 PM"), "verde"));
        this.objeCale.addEvent(new DefaultScheduleEvent("Clase POO :)", this.getFecha("27/10/2016 04:00 PM"), this.getFecha("27/10/2016 06:30 PM"), "azul"));
    }
    
    public ScheduleModel getObjeCale() {
        return objeCale;
    }
    
    private Date getFecha(String date) 
    {
        Date fecha = null;
        if (date != null){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                fecha = sdf.parse(date);
            } catch (Exception e) {
                fecha = null;
            }
        }
        return fecha;
    }
    
}
