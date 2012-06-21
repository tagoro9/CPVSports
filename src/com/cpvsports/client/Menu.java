package com.cpvsports.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class Menu {
	
	private Anchor home;
	private Anchor noticias;
	private Anchor enVivo;
	private Anchor miCuenta;
	private Anchor publicar;
	
	private HorizontalPanel nav;
	
	private Anchor tenis;
	private Anchor futbol;
	private Anchor motor;	
	
	
	private String getActive() {
		if (home.getStylePrimaryName().contains("active"))
			return "home";
		else if (noticias.getStylePrimaryName().contains("active"))
			return "noticias";
		else if (enVivo.getStylePrimaryName().contains("active"))
			return "enVivo";
		else if (miCuenta.getStylePrimaryName().contains("active"))
			return "miCuenta";
		else
			return "publicar";
	}
	
	private void setActive(Anchor activar) {
		home.setStyleName("");
		noticias.setStyleName("");
		enVivo.setStyleName("");
		miCuenta.setStyleName("");
		publicar.setStyleName("");
		activar.setStyleName("active");
	}		
	
	private void homeClick() {
		if (getActive() != "home") {
			setActive(home);
			BreadCrumbs bc = new BreadCrumbs();
			String enlaces[] = new String[1];
			enlaces[0] = "Home";
			bc.construct(enlaces);
			Titulo.setTitulo("Home");
			Pagina home = new Home();
			home.display();
		}
	}
	
	private void noticiasClick() {
		if (getActive() != "noticias") {
			setActive(noticias);
			BreadCrumbs bc = new BreadCrumbs();
			String enlaces[] = new String[1];
			enlaces[0] = "Noticias";
			bc.construct(enlaces);
			Titulo.setTitulo("Noticias");
			Pagina noticias = new Noticias();
			noticias.display();
		}
	}

	private void enVivoClick() {
		if (getActive() != "enVivo") {
			setActive(enVivo);
			BreadCrumbs bc = new BreadCrumbs();
			String enlaces[] = new String[1];
			enlaces[0] = "En vivo";
			Pagina enVivo = new EnVivo();
			bc.construct(enlaces);
			Titulo.setTitulo("En vivo");
			enVivo.display();
		}
	}	
	
	private void miCuentaClick() {
		if (getActive() != "miCuenta") {
			setActive(miCuenta);
			BreadCrumbs bc = new BreadCrumbs();
			String enlaces[] = new String[1];
			enlaces[0] = "Mi cuenta";
			bc.construct(enlaces);
			Titulo.setTitulo("Mi cuenta");
		}
	}	
	
	private void publicarClick() {
		if (getActive() != "publicar") {
			setActive(publicar);
			BreadCrumbs bc = new BreadCrumbs();
			String enlaces[] = new String[1];
			enlaces[0] = "Publicar";
			bc.construct(enlaces);
			Titulo.setTitulo("Publicar");
			Pagina contenido = new Publicar();
			contenido.display();
		}
	}		
	
	public void construct(String activo) {
		//Menu principal
				nav = new HorizontalPanel();
				nav.getElement().setId("nav");
				
				home = new Anchor("Home");
				noticias = new Anchor("Noticias");
				enVivo = new Anchor ("En vivo");
				miCuenta = new Anchor("Mi cuenta");
				publicar = new Anchor("Publicar");
				
				//Determinar elemento activo
				if (activo == "home")
					home.setStyleName("active");
				else if (activo == "noticias")
					noticias.setStyleName("active");
				else if (activo == "enVivo")
					enVivo.setStyleName("active");
				else if (activo == "miCuenta")
					miCuenta.setStyleName("active");
				else if (activo == "publicar")
					publicar.setStyleName("active");
				
			    home.addClickHandler(new ClickHandler() {
				      public void onClick(ClickEvent event) {
				        homeClick();
				      }
			    });
			    
			    noticias.addClickHandler(new ClickHandler() {
				      public void onClick(ClickEvent event) {
				        noticiasClick();
				      }
			    });	
			    
			    enVivo.addClickHandler(new ClickHandler() {
				      public void onClick(ClickEvent event) {
				        enVivoClick();
				      }
			    });	
			    
			    miCuenta.addClickHandler(new ClickHandler() {
				      public void onClick(ClickEvent event) {
				        miCuentaClick();
				      }
			    });	
			    
			    publicar.addClickHandler(new ClickHandler() {
				      public void onClick(ClickEvent event) {
				        publicarClick();
				      }
			    });	
				
				nav.add(home);
				nav.add(noticias);
				nav.add(enVivo);
				if (Cookies.getCookie("id_sesion") != null) {
					nav.add(miCuenta);
					nav.add(publicar);
				}
				RootPanel.get("headerNav").clear();
				RootPanel.get("headerNav").add(nav);
	}
}
