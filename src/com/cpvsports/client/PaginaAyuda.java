package com.cpvsports.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class PaginaAyuda implements Pagina {
	public void display() {
		RootPanel.get("contenido").clear();
		HTML contenido = new HTML();
		contenido.setHTML("<div id=\"ayuda\" class=\"grid_12\">"+
		"<h3>Publicar una noticia</h3>" +
		"<p>Para publicar una noticia no tienes más que ir (una vez logueado) a la "+
		"sección publicar en la que has de introducir un título, añadir las etiquetas"+
		"y el contenido de la noticia para finalmente darle al botón de publicar.</p></div>"
		);
		RootPanel.get("contenido").add(contenido);
	}
}
