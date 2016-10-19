/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

/**
 *
 * @author REGISTRO
 */
@Named(value = "webServicesBean")
@ViewScoped
public class WebServicesBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private String nombUsua;

    public String getNombUsua() {
        return nombUsua;
    }

    public void setNombUsua(String nombUsua) {
        this.nombUsua = nombUsua;
    }
    /**
     * Creates a new instance of WebServicesBean
     */
    
    public WebServicesBean() {
    }
    
    public void nuev()
    {
        this.nombUsua = "";
    }
    
    public void consWebServ()
    {
        Client client = ClientBuilder.newClient();
        String url = String.format("http://localhost:8080/WebService/poo/MiServicio/%s", this.nombUsua);
        WebTarget resource = client.target(url);
        Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        Response response = request.get();
        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL)
        {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity());
        }
        else
        {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity());
        }
    }
}
