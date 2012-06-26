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
		fecha.setHTML("<span>27 JUL, 2012</span>");
		//Imagen
		Image imagenEvento = new Image("img/london.jpg");
		imagenEvento.getElement().setAttribute("alt", "imagen eventos");
		//Titulo
		HTML tituloEvento = new HTML();
		tituloEvento.setHTML("<h4>Juegos olímpicos 2012</h4>");
		//Texto
		HTML textoEvento = new HTML();
		textoEvento.setHTML("<p>"+
                                "El próximo 27 de julio darán comienzo los XXX juegos olímpicos que tendrán lugar"+
                                " en Londres. ¡No te lo pierdas!"+                                    
                            "</p>");
		
		contenido.add(fecha);
		contenido.add(imagenEvento);
		contenido.add(tituloEvento);
		contenido.add(textoEvento);
		
		eventos.add(tituloEventos);
		eventos.add(contenido);
		
		return eventos;
	}
	
	
	public FlowPanel construct() {
		FlowPanel sideBar = Layout.createDiv("grid_4", "sideBar");
		
		FlowPanel banner = Layout.createDivWithId("banner");
		Image bannerImagen = new Image("img/sideBanner.jpg");	
		banner.add(bannerImagen);
		
		sideBar.add(banner);
		sideBar.add(loadEventos());
		
		return sideBar;
	}
}
