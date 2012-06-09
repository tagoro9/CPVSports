package com.cpvsports.client;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;

public class SideBar {
	
	private FlowPanel loadEventos() {
		FlowPanel eventos = Layout.createDivWithId("eventos");
		//Titulo
		HTML tituloEventos = new HTML();
		tituloEventos.setStyleName("sidebarTitle");
		tituloEventos.setHTML("<h3>Próximos eventos</h3>");
		
		//TODO pasar a una funcion!
		
		//Contenido
		FlowPanel contenido = Layout.createDivWithId("contenidoEventos");
		//Fecha
		HTML fecha = new HTML();
		fecha.setHTML("<span>15 DEC, 2012</span>");
		//Imagen
		Image imagenEvento = new Image("img/eventos.jpg");
		//Titulo
		HTML tituloEvento = new HTML();
		tituloEvento.setHTML("<h4>Lorem Ipsum</h4>");
		//Texto
		HTML textoEvento = new HTML();
		textoEvento.setHTML("<p>"+
                                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor"+
                                "incididunt ut labore et dolore magna aliqua. Ut enim."+                                    
                            "</p>");
		//Comentarios
		HTML comentarios = new HTML();
		comentarios.setHTML("4 comentarios");
		comentarios.setStyleName("comentarios");
		//Leer mas
		Anchor mas = new Anchor("Leer más");
		mas.setStyleName("readMore");
		
		contenido.add(fecha);
		contenido.add(imagenEvento);
		contenido.add(tituloEvento);
		contenido.add(textoEvento);
		contenido.add(comentarios);
		contenido.add(mas);
		
		eventos.add(tituloEventos);
		eventos.add(contenido);
		
		return eventos;
	}
	
	
	public FlowPanel loadMiscelanea() {
		FlowPanel miscelanea = Layout.createDivWithId("miscelanea");
		//Titulo
		HTML tituloMiscelanea = new HTML();
		tituloMiscelanea.setStyleName("sidebarTitle");
		tituloMiscelanea.setHTML("<h3>Miscelánea</h3>");
		
		FlowPanel contenido = Layout.createDivWithId("contenidoMiscelanea");
		
		//TODO Pasar a una funcion!!
		Anchor titulo = new Anchor("Lorem Ipsum miscelanea");
		HTMLPanel contenidoMisc = new HTMLPanel("<p>Consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.</p>");
		Anchor mas = new Anchor("");
		mas.setStyleName("readMore");
		contenidoMisc.add(mas);
		
		contenido.add(titulo);
		contenido.add(contenidoMisc);
		
		miscelanea.add(tituloMiscelanea);
		miscelanea.add(contenido);
		
		return miscelanea;
	}
	
	public FlowPanel construct() {
		FlowPanel sideBar = Layout.createDiv("grid_4", "sideBar");
		
		FlowPanel banner = Layout.createDivWithId("banner");
		Image bannerImagen = new Image("img/sideBanner.jpg");	
		banner.add(bannerImagen);
		
		sideBar.add(banner);
		sideBar.add(loadEventos());
		sideBar.add(loadMiscelanea());
		
		return sideBar;
	}
}
