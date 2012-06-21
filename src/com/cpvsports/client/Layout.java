package com.cpvsports.client;

import com.google.gwt.user.client.ui.FlowPanel;

public class Layout {
    public static FlowPanel createDiv(String style) {
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.setStylePrimaryName(style);
        return flowPanel;
    }
    
    public static FlowPanel createDivWithId(String id) {
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.getElement().setId(id);
        return flowPanel;    	
    }

    public static void reload() {
    	Titulo.setTitulo("Home");
    	Header header = new Header();
    	Menu menu = new Menu();
    	BreadCrumbs bc = new BreadCrumbs();
    	Pagina home = new Home();
    	header.display();
    	menu.construct("home");
    	String[] enlaces = new String[1];
    	enlaces[0] = "Home";
    	bc.construct(enlaces);
    	home.display();
    }
    
     public static FlowPanel createDiv(String style,String id) {
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.setStylePrimaryName(style);
        flowPanel.getElement().setId(id);
        return flowPanel;
    }

    public static FlowPanel clear() {
        return createDiv("clear");
    }
}
