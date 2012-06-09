package com.cpvsports.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class Noticias implements Pagina {
	
	private Anchor ultimas;
	private Anchor masVistas;
	private Anchor favoritos;
	private FlowPanel listadoNoticias;
	
	//Eventos
	private void ultimasClick() {
		if (getActive() != "ultimas") {
			setActive(ultimas);
		}
	}
	
	private void masVistasClick() {
		if (getActive() != "masVistas") {
			setActive(masVistas);
		}
		
	}
	
	private void favoritosClick() {	
		if (getActive() != "favoritos") {
			setActive(favoritos);
		}
	}
	
	private String getActive() {
		if (ultimas.getStylePrimaryName().contains("active"))
			return "ultimas";
		else if (masVistas.getStylePrimaryName().contains("active"))
			return "masVistas";
		else
			return "favoritos";
	}
	
	private void setActive(Anchor activar) {
		ultimas.setStyleName("");
		masVistas.setStyleName("");
		favoritos.setStyleName("");
		activar.setStyleName("active");
	}
	
	
	
	//Contruis interfaz
	
	private FlowPanel loadPortada() {
		//Portada
		FlowPanel portada = Layout.createDivWithId("portada");
		//Imagen
		Image imagenPortada = new Image("img/portada.jpg");
		//Titular
		FlowPanel titularPortada = Layout.createDivWithId("titularPortada");
		//Crear contenido del titular
		//Fecha
		HTML fecha = new HTML();
		fecha.setStyleName("fecha");
		fecha.setHTML("15 DEC, 2012");
		//Titular
		HTML titular = new HTML();
		titular.setHTML("<h2>Mi titular demasiado largo para caber en una linea quepasara</h2>");
		//Contenido
		HTML titularContenido = new HTML();
		titularContenido.setHTML("<p>" +
				"After checking out the visual, you should understand how to create th" +
                "e mockup’s grid. Using the widths, match up the class #’s from the list and lets" +
                "throw some code together. Remember to add the clearing div at the end of each" +
                "row. Don’t forget to include the stylsheets included in the Grid 960 package." +
				"</p>");
		titularPortada.add(fecha);
		titularPortada.add(titular);
		titularPortada.add(titularContenido);
		
		portada.add(imagenPortada);
		portada.add(titularPortada);	
		return portada;
	}
	
	
	private FlowPanel loadNoticia() {
		FlowPanel noticia = Layout.createDiv("noticia");
		//Imagen
		FlowPanel imagenNoticiaContainer = Layout.createDiv("imagenNoticia grid_2");
		Image imagenNoticia = new Image("img/noticia.jpg");
		imagenNoticiaContainer.add(imagenNoticia);
		//Contenido
		FlowPanel contenidoNoticia = Layout.createDiv("contenidoNoticia grid_5");
		//Fecha
		HTML fecha = new HTML();
		fecha.setStyleName("fecha");
		fecha.setHTML("15 DEC, 2012");
		//Titulo
		HTML titulo = new HTML();
		titulo.setHTML("<h2>Titular de la noticia deportiva</h2>");
		//Texto
		HTML texto = new HTML();
		texto.setHTML("<p>" +
                      "After checking out the visual, you should understand how to create th" +
                      "e mockup’s grid. Using the widths, match up the class #’s from the list and lets" +
                      "throw some code together. Remember to add the clearing div at the end of each" + 
                      "row. Don’t forget to include the stylsheets included in the Grid 960 package." +                                        
                      "After checking out the visual, you should understand how to create th" +
                      "e mockup’s grid. Using the widths, match up the class #’s from the list and lets" +
                      "throw some code together. Remember to add the clearing div at the end of each" +
                      "row. Don’t forget to include the stylsheets included in the Grid 960 package." +                                                                                
                      "</p>");
		contenidoNoticia.add(fecha);
		contenidoNoticia.add(titulo);
		contenidoNoticia.add(texto);
		
		noticia.add(imagenNoticiaContainer);
		noticia.add(contenidoNoticia);
		return noticia;
	}
		
	private FlowPanel loadUltimas() {
		FlowPanel noticias = Layout.createDivWithId("noticias");
		
		//Menu de navegacion
		FlowPanel noticiasNavContainer = Layout.createDivWithId("noticias-nav"); 
		HorizontalPanel noticiasNav = new HorizontalPanel();
		//noticiasNav.getElement().setId("noticias-nav");
		//Enlaces
		ultimas = new Anchor("Últimas noticias");
		ultimas.setStyleName("active");
		masVistas = new Anchor("Las más vistas");
		favoritos = new Anchor("Favoritas");
		
		//Eventos al hacer click en el menu
	    ultimas.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        ultimasClick();
	      }
	    });		
	    
	    masVistas.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        masVistasClick();
		      }
	    });
	    
	    favoritos.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        favoritosClick();
		      }
	    });		
		
		
		noticiasNav.add(ultimas);
		noticiasNav.add(masVistas);
		noticiasNav.add(favoritos);
		
		noticiasNavContainer.add(noticiasNav);
		
		noticias.add(noticiasNavContainer);
		
		listadoNoticias = Layout.createDiv("listaNoticias", "noticias-1");
		
		listadoNoticias.add(loadNoticia());
		
		noticias.add(listadoNoticias);
		
		
		return noticias;
	}
	
	public void display() {
		FlowPanel contenido = Layout.createDiv("container_12", "contenido");
		FlowPanel main = Layout.createDiv("grid_8", "main");
				
		main.add(loadPortada());
		main.add(loadUltimas());
		
		contenido.add(main);
		//SideBar
		
		SideBar sidebar = new SideBar();
		contenido.add(sidebar.construct());		
		RootPanel.get("wrapper").add(contenido);
	}
}
