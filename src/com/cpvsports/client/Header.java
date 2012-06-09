package com.cpvsports.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Anchor;

public class Header {
	
	public void display() {
		//Contenedor del header
		FlowPanel headerContainer = Layout.createDiv("container_12", "headerContainer");
		//Header
		FlowPanel header = Layout.createDivWithId("header");
		//Logo
		FlowPanel headerLogo = Layout.createDiv("grid_8", "headerLogo");
		//Crear titulo
		HTML titulo = new HTML();
		titulo.setHTML("<h1><a class=\"logoLink\" href=\"#\"></a></h1>");
		headerLogo.add(titulo);
		//Menu superior del header
		FlowPanel headerMenu = Layout.createDiv("grid_3","headerMenu");
		//Crear lista con cada elemento del menu
		HorizontalPanel menuSuperior = new HorizontalPanel();
		menuSuperior.addStyleName("headerMenu");
		Anchor contacto = new Anchor("CONTACTO");
		Anchor faq = new Anchor("FAQS");
		Anchor ayuda = new Anchor("Ayuda");
		Anchor about = new Anchor ("About");
		menuSuperior.add(contacto);
		menuSuperior.add(faq);
		menuSuperior.add(ayuda);
		menuSuperior.add(about);
		headerMenu.add(menuSuperior);
		
		//Menu principal
		
		FlowPanel headerNav = Layout.createDivWithId("headerNav");
		HorizontalPanel nav = new HorizontalPanel();
		nav.getElement().setId("nav");
		Anchor home = new Anchor("Home");
		home.addStyleName("active");
		Anchor noticias = new Anchor("Noticias");
		Anchor directo = new Anchor ("En vivo");
		
		nav.add(home);
		nav.add(noticias);
		nav.add(directo);
		
		headerNav.add(nav);
		
		
		header.add(headerLogo);
		header.add(headerMenu);
		headerContainer.add(header);
		headerContainer.add(headerNav);
		RootPanel.get("wrapper").add(headerContainer);
		
	}
}
