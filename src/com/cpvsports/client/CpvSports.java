package com.cpvsports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.History;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CpvSports implements EntryPoint {
	
	
	public void reload(String pagina) {
		RootPanel.get("wrapper").clear();
		Header header = new Header();
		/*Footer footer = new Footer();
		Pagina contenido;
		if (pagina == "home" )
			contenido = new Home();
		else if (pagina == "noticias")
			contenido = new Noticias();
		else 
			contenido = new Home();
		contenidoDisplay = contenido.display();
		//contenidoDisplay = ew Noticias();
		RootPanel.get("wrapper").add(header.display(home,noticias,enVivo,futbol,tenis,motor));
		RootPanel.get("wrapper").add(contenidoDisplay);
		RootPanel.get("wrapper").add(footer.display());*/
	}
		
	public void onModuleLoad() {
		Titulo.setTitulo("Home");
		Header header = new Header();
		Menu menu = new Menu();
		Footer footer = new Footer();
		BreadCrumbs bc = new BreadCrumbs();
		Pagina contenido = new Home();
		header.display(0);
		menu.construct("home");
		String enlaces[] = new String[1];
		enlaces[0] = "Home";
		bc.construct(enlaces);
		contenido.display(0);
		footer.display();
	}
}
