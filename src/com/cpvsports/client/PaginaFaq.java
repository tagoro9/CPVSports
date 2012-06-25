package com.cpvsports.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class PaginaFaq implements Pagina {
	public void display(Integer id) {
		RootPanel.get("contenido").clear();	
		HTML contenido = new HTML();
		contenido.setHTML("<div id=\"faq\" class=\"grid_12\">"+
		"<h3>Publiqué mi noticia hace un tiempo ¿por qué no aparece?</h3>" +
		"<p>En CPVSports no hemos implementado todavía un archivo de noticias, por lo que a día de hoy" +
		"sólo se puede acceder a las últimas noticias publicadas.</p>"+
		"<h3>Olvidé mi contraseña ¿qué puedo hacer?</h3>" +
		"<p>Todavía no hay implementado un sistema de recuperación de contraseñas por lo que te recomendamos"+
		"que desde la página ABOUT te pongas en contacto con algún miembro de CPVSports.</p>"+
		"</div>"
		);
		RootPanel.get("contenido").add(contenido);		
	}
}
