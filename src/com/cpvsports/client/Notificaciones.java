package com.cpvsports.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Notificaciones {

	public static void success(String message) {
		final FlowPanel contenedor = Layout.createDiv("success grid_12");
		HTML mensaje = new HTML(message);
		contenedor.add(mensaje);
		Anchor cerrar = new Anchor("x");
		contenedor.add(cerrar);
		cerrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				contenedor.removeFromParent();
			}
		});
	    Timer t = new Timer() {
	    	public void run() {
	    		contenedor.removeFromParent();
	    	}
	    };
	    t.schedule(5000);
		RootPanel.get("notificaciones").add(contenedor);
	}
	
	public static void alert(String message) {
		final FlowPanel contenedor = Layout.createDiv("alert grid_12");
		HTML mensaje = new HTML(message);
		contenedor.add(mensaje);
		Anchor cerrar = new Anchor("x");
		contenedor.add(cerrar);
		cerrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				contenedor.removeFromParent();
			}
		});
	    Timer t = new Timer() {
	    	public void run() {
	    		contenedor.removeFromParent();
	    	}
	    };
	    t.schedule(5000);
		RootPanel.get("notificaciones").add(contenedor);		
	}
	
	public static void error(String message) {
		final FlowPanel contenedor = Layout.createDiv("error grid_12");
		HTML mensaje = new HTML(message);
		contenedor.add(mensaje);
		Anchor cerrar = new Anchor("x");
		contenedor.add(cerrar);
		cerrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				contenedor.removeFromParent();
			}
		});
	    Timer t = new Timer() {
	    	public void run() {
	    		contenedor.removeFromParent();
	    	}
	    };
	    t.schedule(5000);
		RootPanel.get("notificaciones").add(contenedor);
	}
	
}

