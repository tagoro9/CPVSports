package com.cpvsports.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Noticias implements Pagina{		
		
		private FlowPanel main;
		private FlowPanel sideBar;
		private FlowPanel comentarios;
	
		//Construir interfaz
		
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
		
		private FlowPanel loadComentario() {
			FlowPanel comentario = Layout.createDiv("comentario");
			HTML texto = new HTML("Esta noticia es mentira");
			HTML autor = new HTML("Victor Mora");
			comentario.add(texto);
			comentario.add(autor);
			return comentario; 
		}
		
		private void loadComentarios() {
			//Obtener comentarios de la noticias de la bd y cargarlos
			HTML titulo = new HTML("<h3>Comentarios</h3>");
			comentarios.clear();
			comentarios.add(titulo);
			comentarios.add(loadComentario());
			//Formulario para añadir un nuevo comentario
		}
		
		private FlowPanel loadBigNoticia() {
			//BreadCrumbs y titulo
			BreadCrumbs bc = new BreadCrumbs();
			String enlaces[] = new String[2];
			enlaces[0] = "Noticias";
			enlaces[1] = "Titulo de la noticia";
			bc.construct(enlaces);
			Titulo.setTitulo("Titulo de la noticia");
			
			//Portada
			FlowPanel portada = Layout.createDivWithId("portada");
			//Imagen
			Image imagenPortada = new Image("http://lorempixel.com/620/378/sports/");
			//Titular
			FlowPanel titularPortada = Layout.createDivWithId("titularPortada");
			//Crear contenido del titular
			//Fecha
			HTML fecha = new HTML();
			fecha.setStyleName("fecha");
			fecha.setHTML("15 DEC, 2012");
			//Titular
			HTML titular = new HTML();
			titular.setHTML("<h2>Noticia cargada desde el lateral</h2>");
			//Contenido
			HTML titularContenido = new HTML();
			titularContenido.setHTML("<p>" +
					"Esto esta cargado desde un lado" +
					"</p>");
			titularPortada.add(fecha);
			titularPortada.add(titular);
			titularPortada.add(titularContenido);
			
			portada.add(imagenPortada);
			portada.add(titularPortada);	
			return portada;			
		}
		
		private FlowPanel loadNoticia() {
			FlowPanel noticia = Layout.createDiv("noticia1");
			FlowPanel contenidoNoticia = Layout.createDiv("contenidoNoticia grid_4");
			FlowPanel fechaContenedor = Layout.createDiv("fecha1");
			HTML fecha = new HTML();
			fecha.setHTML("15 DEC, 2012");
			fechaContenedor.add(fecha);
			
			
			HTML titulo = new HTML();
			titulo.setHTML("<h6><a>Titular de la noticia</a></h6>");
			titulo.getElement().getStyle().setProperty("cursor", "pointer");
			titulo.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					main.clear();
					main.add(loadBigNoticia());
					main.add(comentarios);
					loadComentarios();
			      }
			});
			HTML contenido = new HTML();
			contenido.setHTML("<p>Este es el contenido de la noicia pequqñito<p>");
			contenidoNoticia.add(fechaContenedor);
			contenidoNoticia.add(titulo);
			contenidoNoticia.add(contenido);
			
			noticia.add(contenidoNoticia);
			
			return noticia;
		}		
		
		private void loadSideBar() {
			FlowPanel noticiasNav = Layout.createDivWithId("noticias-nav");
			HorizontalPanel nav = new HorizontalPanel();
			Anchor titulo = new Anchor("Últimas noticias");
			titulo.setStyleName("active");
			nav.add(titulo);
			noticiasNav.add(nav);
			FlowPanel noticias = Layout.createDivWithId("noticias-1");
			noticias.add(loadNoticia());
			noticias.add(loadNoticia());
			sideBar.add(noticiasNav);
			sideBar.add(noticias);
		}
		
		
			
		public void display() {
					
			main = Layout.createDiv("grid_8", "main");
			sideBar = Layout.createDiv("grid_4", "sideBar");
			comentarios = Layout.createDivWithId("comentarios");
			main.add(loadPortada());
			main.add(comentarios);
			loadSideBar();
			RootPanel.get("contenido").clear();
			RootPanel.get("contenido").add(main);
			RootPanel.get("contenido").add(sideBar);
		}
}
