package com.cpvsports.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class Header {

	private void menuClick(String accion) {
		RootPanel.get("contenido").clear();
		BreadCrumbs bc = new BreadCrumbs();
		String enlaces[] = new String[1];
		enlaces[0] = accion;
		bc.construct(enlaces);
		Pagina contenido;
		if (accion == "About") {
			contenido = new PaginaAbout();
			Titulo.setTitulo("About");
		}
		else if (accion == "Contacto") {
			contenido = new PaginaContacto();
			Titulo.setTitulo("Contacto");
		}
		else if (accion == "Faq") {
			contenido = new PaginaFaq();
			Titulo.setTitulo("FAQ");
		}
		else if (accion == "Ayuda"){
			contenido = new PaginaAyuda();
			Titulo.setTitulo("Ayuda");
		}
		else {
			contenido = new PaginaRegistro();
			Titulo.setTitulo("Registro");
		}
		Menu menu = new Menu();
		menu.construct("none");
		contenido.display();
	}
	
	public void display() {
		//Logo
		FlowPanel headerLogo = Layout.createDiv("grid_7", "headerLogo");
		//Crear titulo
		HTML titulo = new HTML();
		titulo.setHTML("<h1><a class=\"logoLink\" href=\"#\"></a></h1>");
		headerLogo.add(titulo);
		//Menu superior del header
		FlowPanel headerMenu = Layout.createDiv("grid_4","headerMenu");
		//Crear lista con cada elemento del menu
		HorizontalPanel menuSuperior = new HorizontalPanel();
		menuSuperior.addStyleName("headerMenu");
		Anchor contacto = new Anchor("CONTACTO");
		Anchor faq = new Anchor("FAQS");
		Anchor ayuda = new Anchor("Ayuda");
		Anchor about = new Anchor ("About");
		
	    contacto.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        menuClick("Contacto");
		      }
	    });
	    
	    faq.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        menuClick("Faq");
		      }
	    });	
	    
	    ayuda.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        menuClick("Ayuda");
		      }
	    });	
	    
	    about.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        menuClick("About");
		      }
	    });	
		
		menuSuperior.add(contacto);
		menuSuperior.add(faq);
		menuSuperior.add(ayuda);
		menuSuperior.add(about);
		headerMenu.add(menuSuperior);
		
		//Formulario de inicio de sesion
		FormPanel loginForm = new FormPanel();
		TextBox email = new TextBox();
		PasswordTextBox password = new PasswordTextBox();
		Anchor submit = new Anchor("Entrar");
		submit.setStyleName("button");
		
		email.getElement().setAttribute("placeholder", "Email");
		password.getElement().setAttribute("placeholder", "Contraseña");
		
		loginForm.getElement().setId("loginForm");
		
		HorizontalPanel formPanel = new HorizontalPanel();
		HorizontalPanel registro = new HorizontalPanel();
		
		formPanel.add(email);
		formPanel.add(password);
		formPanel.add(submit);
		
		loginForm.add(formPanel);		
		
		Anchor registroEnlace = new Anchor("¡Regístrate!");
		HTML registroTexto = new HTML("¿Todavía no tienes cuenta?&nbsp;");
		
		registroEnlace.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
			        menuClick("Registro");
			      }
		    });	
		
		registro.add(registroTexto);
		registro.add(registroEnlace);
		registro.setStyleName("registrarse");
		headerMenu.add(loginForm);
		headerMenu.add(registro);
		
		RootPanel.get("header").add(headerLogo);
		RootPanel.get("header").add(headerMenu);		
	}
}
