package com.cpvsports.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class PaginaAyuda implements Pagina {
	public void display(Integer id) {
		RootPanel.get("contenido").clear();
		HTML contenido = new HTML();
		contenido.setHTML("<div id=\"ayuda\" class=\"grid_12\">"+
		"<h3>¿Qué es CPVSports?</h3>" +
		"<p>CPVSports es un noticiario deportivo social, en el que cada persona puede publicar sus "+
		"propias noticias.</p>"+
		"<h3>Registro</h3>" +
		"<p>Para registrarse hay que hacer click en la parte superior de la página, en la cabecera"+
		"en el botón regístrate. Se cargará la página de registro. Introduce tus datos y dale a enviar."+
		" ¡Bienvenido a CPVSports!</p>"+		
		"<h3>Inicio de sesión</h3>"+
		"<p>Para entrar al sitio no tienes más que introducir tus datos en el formulario de la cabecera"+
		"y hacer click en entrar. Ya estás logueado en la aplicación</p>"+
		"<h3>Publicar un comentario</h3>" +
		"<p>Para publicar un comentario no hay más que ir a la página de una noticia y desde allí escribir el"+
		" comentario deseado y hacer click en Enviar. El nuevo comentario parecerá entre los demás.</p>"+
		"<h3>Publicar una noticia</h3>" +
		"<p>Para publicar una noticia no tienes más que ir (una vez logueado) a la "+
		"sección publicar en la que has de introducir un título, añadir una imagen, seleccionar la categoría"+
		"e introducir el contenido de la noticia para finalmente darle al botón de publicar.</p></div>"
		);
		RootPanel.get("contenido").add(contenido);
	}
}
