package com.cpvsports.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class Header {

	private final LoginServiceAsync servicioLogin = GWT.create(LoginService.class);
	private TextBox email;
	private PasswordTextBox password;
	private HorizontalPanel sesiones;
	private FlowPanel headerMenu;
	
	private void menuClick(String accion) {
		RootPanel.get("contenido").clear();
		BreadCrumbs bc = new BreadCrumbs();
		String enlaces[] = new String[1];
		enlaces[0] = accion;
		bc.construct(enlaces);
		Pagina contenido;
		if (accion == "Contacto") {
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
		contenido.display(0);
	}
	
	public void display(Integer id) {
		//Logo
		FlowPanel headerLogo = Layout.createDiv("grid_7", "headerLogo");
		//Crear titulo
		HTML titulo = new HTML();
		titulo.setHTML("<h1><a class=\"logoLink\" href=\"#\"></a></h1>");
		
		titulo.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Layout.reload();
			}
		});
		
		headerLogo.add(titulo);
		//Menu superior del header
		headerMenu = Layout.createDiv("grid_4","headerMenu");
		//Crear lista con cada elemento del menu
		HorizontalPanel menuSuperior = new HorizontalPanel();
		menuSuperior.addStyleName("headerMenu");
		Anchor contacto = new Anchor("CONTACTO");
		Anchor faq = new Anchor("FAQS");
		Anchor ayuda = new Anchor("Ayuda");
		
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
		
		menuSuperior.add(contacto);
		menuSuperior.add(faq);
		menuSuperior.add(ayuda);
		headerMenu.add(menuSuperior);
		sesiones = new HorizontalPanel();
		if (Cookies.getCookie("id_sesion") == null ) {
			//Formulario de inicio de sesion
			FormPanel loginForm = new FormPanel();
			email = new TextBox();
			password = new PasswordTextBox();
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
			
			sesiones.add(loginForm);
			
			//Loguearse
			submit.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					loguear();
				}
			});
			
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
		}
		else {
			servicioLogin.isLogged(Cookies.getCookie("id_sesion"), new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					Window.alert("Error al comprobar si el usuario esta logueado");
				}
				public void onSuccess(String result) {
					if (result != null) {
						//Cerrar sesion		
						sesiones.setStyleName("cerrarSesion");
						HTML cerrarSesion = new HTML("¡Bienvenido "+result+"!&nbsp;");
						Anchor cerrar = new Anchor("Cerrar sesión.");
						sesiones.add(cerrarSesion);
						sesiones.add(cerrar);
						headerMenu.add(sesiones);
						cerrar.addClickHandler(new ClickHandler(){
							public void onClick(ClickEvent event) {
								cerrarSesion();
							}
						});
					}
					else {
						Layout.reload();
					}
				}
			});			
		}
		
		RootPanel.get("header").clear();
		RootPanel.get("header").add(headerLogo);
		RootPanel.get("header").add(headerMenu);		
	}
	
	private void cerrarSesion() {
		//Borrar cookie y borrar entrada de la base de datos
		servicioLogin.logout(Cookies.getCookie("id_sesion"), new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error al cerrar la sesión");
			}
			public void onSuccess(Integer result) {
				Cookies.removeCookie("id_sesion");
				Cookies.removeCookie("id_usuario");
				//Volver a cargar la pagina
				Layout.reload();
			}
		});
	} 
	
	private void loguear() {
		String usuarioT = email.getText();
		String passwordT = password.getText();
		servicioLogin.loguear(usuarioT, passwordT, new AsyncCallback<Integer[]>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error en el login, vuelva a intentarlo.");
			}
			public void onSuccess(Integer[] result) {
				if (result[0] == 0) {
					Window.alert("El usuario o contraseña no son correctos");
				}
				else {
					Cookies.setCookie("id_sesion", result[0].toString());
					Cookies.setCookie("id_usuario", result[1].toString());
					//TODO cambiar formuario de inicio de sesion por nombre y cerrar sesion
					Layout.reload();
				}
			}
		});		
	}
}
