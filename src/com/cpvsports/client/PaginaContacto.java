package com.cpvsports.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class PaginaContacto implements Pagina {
	public void display(Integer id) {
		RootPanel.get("contenido").clear();
		HTML contenido = new HTML();
		contenido.setHTML("<div id=\"contacto\" class=\"grid_12\">"+
		"<h3>Grupo 3 Usabilidad y accesibilidad</h3>" +
		"<ul class=\"aboutGallery\">"+
		"<li><a href=\"http://twitter.com/cacogr\" title=\"cacogr\">"+
		"<img alt=\"cacogr\" src=\"https://api.twitter.com/1/users/profile_image/cacogr?size=bigger\"></a></li>"+
		"<li><a href=\"http://twitter.com/pedrojbilbao\" title=\"pedrojbilbao\">"+
		"<img alt=\"pedrojbilbao\" src=\"https://api.twitter.com/1/users/profile_image/pedrojbilbao?size=bigger\"></a></li>"+
		"<li><a href=\"http://twitter.com/vivictormora\" title=\"vivictormora\">"+
		"<img alt=\"vivictormora\" src=\"https://api.twitter.com/1/users/profile_image/vivictormora?size=bigger\"></a></li>"+
		"</ul></div>"
		);
		RootPanel.get("contenido").add(contenido);
	}
}
