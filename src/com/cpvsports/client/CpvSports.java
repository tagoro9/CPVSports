package com.cpvsports.client;

import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CpvSports implements EntryPoint {
	public void onModuleLoad() {
		Header header = new Header();
		Footer footer = new Footer();
		Pagina contenido = new Noticias();
		header.display();
		contenido.display();
		footer.display();
	}
}
