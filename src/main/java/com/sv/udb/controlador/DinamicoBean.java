/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.utils.DynamicField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mauricio
 */
@Named(value = "dinamicoBean")
@ViewScoped
public class DinamicoBean implements Serializable{

    private static final long serialVersionUID = -5196715359527212081L;
    private List<DynamicField> listCmps;
    private Map<String, Object> mapa;
    /**
     * Creates a new instance of DinamicoBean
     */
    public DinamicoBean() {
    }

    public Map<String, Object> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, Object> mapa) {
        this.mapa = mapa;
    }
    
    @PostConstruct
    public void init()
    {
        this.listCmps = new ArrayList<>();
        this.mapa = new HashMap<>();
        //Agrega un elemento
        this.mapa.put("elem1", "");
        this.listCmps.add(new DynamicField("Label elemento 1", "elem1", null, "TEXT"));
        //Agrega un segundo elemento
        this.mapa.put("elem2", "");
        this.listCmps.add(new DynamicField("Label elemento 2", "elem2", null, "TEXT"));
        //Agrega un tercer elemento
        //Para select
        Map<Object, Object> opciones = new HashMap<>();
        opciones.put(1, "opción 1");
        opciones.put(2, "opción 2");
        opciones.put(3, "opción 3");
        this.mapa.put("elem3", null);
        this.listCmps.add(new DynamicField("Label elemento 3", "elem3", opciones, "SELECT"));
        //Agrega un tercer elemento
        //Para select
        Map<Object, Object> opcionesCB = new HashMap<>();
        opcionesCB.put(1, "opción 1");
        opcionesCB.put(2, "opción 2");
        opcionesCB.put(3, "opción 3");
        opcionesCB.put(4, "opción 4");
        opcionesCB.put(5, "opción 5");
        this.mapa.put("elem4", null);
        this.listCmps.add(new DynamicField("Label elemento 4", "elem4", opcionesCB, "SELECTMANYCHECKBOX"));
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
//            String valor = (String)this.mapa.get("elem4");
            Object[] valor = (Object[])this.mapa.get("elem4");
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos " + valor[0] + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    @SuppressWarnings("cast")
    public void populateForm(ComponentSystemEvent event)
    {
        HtmlForm form = (HtmlForm) event.getComponent();
        form.getChildren().add(this.createUIOutput("<fieldset>"));
        for (DynamicField field : this.listCmps) //Recorre los elementos
        {
            form.getChildren().add(this.createUIOutput("<div class=\"form-group input-group-xs\">"));
            switch (field.getType())
            {
                case "TEXT":
                    //Crea el label
                    form.getChildren().add(this.getUIComponent(field, HtmlOutputLabel.COMPONENT_TYPE));
                    //Crea el input
                    form.getChildren().add(this.getUIComponent(field, HtmlInputText.COMPONENT_TYPE));
                    break;
                case "SELECT":
                    //Crea el label
                    form.getChildren().add(this.getUIComponent(field, HtmlOutputLabel.COMPONENT_TYPE));
                    //Crea el select
                    form.getChildren().add(this.getUIComponent(field, HtmlSelectOneMenu.COMPONENT_TYPE));
                    break;
                case "SELECTMANYCHECKBOX":
                    //Crea el label
                    form.getChildren().add(this.getUIComponent(field, HtmlOutputLabel.COMPONENT_TYPE));
                    //Crea el select
                    form.getChildren().add(this.getUIComponent(field, HtmlSelectManyCheckbox.COMPONENT_TYPE));
                    break;
            }
            form.getChildren().add(this.createUIOutput("</div>"));
        }
        form.getChildren().add(this.createUIOutput("</fieldset>"));
        //Agregar los botones
        UIComponent btonGroup = this.getUIButtons(form);
        if(btonGroup != null)
        {
            form.getChildren().add(btonGroup);
        }
    }

    private ValueExpression createValueExpression(String string, Class<String> aClass) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory()
                .createValueExpression(context.getELContext(), string, aClass);
    }
    
    private UIOutput createUIOutput(String value)
    {
        UIOutput resp = new UIOutput();
        resp.setRendererType("javax.faces.Text");
        resp.setValue(value);
        return resp;
    }
    
    private UIComponent getUIButtons(HtmlForm form)
    {
        UIComponent resp = null;
        for(UIComponent temp : form.getChildren())
        {
            if(temp.getId().equals("btonGroup"))
            {
                resp = temp;
                break;
            }
        }
        return resp;
    }
    
    private UIComponent getUIComponent(DynamicField field, String type)
    {
        UIComponent resp = null;
        Application app = FacesContext.getCurrentInstance().getApplication();
        if(type.equals(HtmlOutputLabel.COMPONENT_TYPE))
        {
            HtmlOutputLabel label = (HtmlOutputLabel)app.createComponent(HtmlOutputLabel.COMPONENT_TYPE);
            label.setFor(field.getFieldKey());
            label.setValueExpression("value", createValueExpression(field.getLabel(), String.class));
            resp = label;
        }
        else if(type.equals(HtmlInputText.COMPONENT_TYPE))
        {
            HtmlInputText input = (HtmlInputText)app.createComponent(HtmlInputText.COMPONENT_TYPE);
            input.setId(field.getFieldKey());
            input.setValueExpression("value", createValueExpression("#{dinamicoBean.mapa['" + field.getFieldKey() + "']}", String.class));
            input.setStyleClass("form-control");
            resp = input;
        }
        else if(type.equals(HtmlSelectOneMenu.COMPONENT_TYPE))
        {
            HtmlSelectOneMenu input = (HtmlSelectOneMenu)app.createComponent(HtmlSelectOneMenu.COMPONENT_TYPE);
            input.setId(field.getFieldKey());
            input.setValueExpression("value", createValueExpression("#{dinamicoBean.mapa['" + field.getFieldKey() + "']}", String.class));
            input.setStyleClass("form-control");
            if(field.getFieldValue() != null)
            {
                UISelectItems objeItems = new UISelectItems();
                List<SelectItem> listItems = new ArrayList<>();
                SelectItem seleItem = new SelectItem();
                seleItem.setValue(null);
                seleItem.setLabel("Seleccione...");
                listItems.add(seleItem);
                for(Object entry : ((HashMap)field.getFieldValue()).entrySet())
                {
                    Entry<Object, Object> item = (Entry<Object, Object>)entry;
                    seleItem = new SelectItem();
                    seleItem.setValue(item.getKey());
                    seleItem.setLabel((String)item.getValue());
                    listItems.add(seleItem);
                }
                objeItems.setValue(listItems.toArray());
                input.getChildren().add(objeItems);
            }
            resp = input;
        }
        else if(type.equals(HtmlSelectManyCheckbox.COMPONENT_TYPE))
        {
            HtmlSelectManyCheckbox input = (HtmlSelectManyCheckbox)app.createComponent(HtmlSelectManyCheckbox.COMPONENT_TYPE);
            input.setId(field.getFieldKey());
            input.setValueExpression("value", createValueExpression("#{dinamicoBean.mapa['" + field.getFieldKey() + "']}", String.class));
            input.setStyleClass("form-control");
            if(field.getFieldValue() != null)
            {
                UISelectItems objeItems = new UISelectItems();
                List<SelectItem> listItems = new ArrayList<>();
//                SelectItem seleItem;
                for(Object entry : ((HashMap)field.getFieldValue()).entrySet())
                {
                    Entry<Object, Object> item = (Entry<Object, Object>)entry;
                    listItems.add(new SelectItem(item.getKey(), (String)item.getValue()));
                }
                objeItems.setValue(listItems);
                input.getChildren().add(objeItems);
            }
            resp = input;
        }
        return resp;
    }
}
