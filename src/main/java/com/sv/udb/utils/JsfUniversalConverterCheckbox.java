package com.sv.udb.utils;


import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@FacesConverter("PooConvChck")
public class JsfUniversalConverterCheckbox implements Converter, Serializable {
	/**
	 * Serial de la clase
	 */
	private static final long serialVersionUID = 6447192409380030909L;

	/**
	 * Variable que se encarga de manejar los logs en la aplicacion.
	 */
	private static Log log = LogFactory.getLog(JsfUniversalConverterCheckbox.class);

	/**
	 * Cache del objeto convertido.
	 */
	private static final String OBJECT_CACHE_KEY = "JSF_UNIVERSAL_CONVERTER_OBJECT_CACHE_CHECKBOX";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public final Object getAsObject(final FacesContext fc,
			final UIComponent uic, final String string) {
            System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.err.println(string);
            System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		 short number= 0;
                try {
//                    if (string.equals("Yes")) {
//                        number= 1;
//                    }
                } catch (Exception ex) {
		log.debug("Error al convertir");
                }
                return number;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public final String getAsString(final FacesContext fc,
			final UIComponent uic, final Object o) {
            System.err.println("===============================================");
            System.err.println(o.toString());
            System.err.println("===============================================");
		return o.toString();
	}

}
