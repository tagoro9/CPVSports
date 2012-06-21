package com.cpvsports.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class EnVivo implements Pagina {
	private FlowPanel main;
	private FlowPanel sideBar;
	
	private void loadPortada() {
		main = Layout.createDiv("grid_8", "main");
		//Portada
		FlowPanel portada = Layout.createDivWithId("portada");
		HTMLPanel video = new HTMLPanel("<object type=\"application/x-shockwave-flash\" height=\"360\" width=\"620\" id=\"live_embed_player_flash\" data=\"http://www.justin.tv/widgets/live_embed_player.swf?channel=freddy49z\" bgcolor=\"#000000\"><param name=\"allowFullScreen\" value=\"true\" /><param name=\"allowScriptAccess\" value=\"always\" /><param name=\"allowNetworking\" value=\"all\" /><param name=\"movie\" value=\"http://www.justin.tv/widgets/live_embed_player.swf\" /><param name=\"flashvars\" value=\"hostname=www.justin.tv&channel=freddy49z&auto_play=false&start_volume=25\" /></object>");
		//Titular
		FlowPanel titularPortada = Layout.createDiv("enVivo","titularPortada");
		HTML titular = new HTML();
		titular.setHTML("<h2>Mi titular demasiado largo para caber en una linea quepasara</h2>");
		titularPortada.add(titular);
		//Descripcion
		HTML titularContenido = new HTML();
		titularContenido.setHTML("<p>" +
				"En directo los últimos eventos del mundo del motor!" +
				"</p>");
		titularPortada.add(titularContenido);
		portada.add(video);
		portada.add(titularPortada);
		main.add(portada);
		RootPanel.get("contenido").add(main);
	}
	
	private void loadComentarios() {
		sideBar = Layout.createDiv("grid_4", "sideBar");
		FlowPanel noticiasNav = Layout.createDivWithId("noticias-nav");
		HorizontalPanel nav = new HorizontalPanel();
		Anchor titulo = new Anchor("Comentarios");
		titulo.setStyleName("active");
		nav.add(titulo);
		noticiasNav.add(nav);
		FlowPanel comentarios = Layout.createDivWithId("noticias-1");
		sideBar.add(noticiasNav);
		sideBar.add(comentarios);
		RootPanel.get("contenido").add(sideBar);
	}
	
	public void display() {
		RootPanel.get("contenido").clear();
		loadPortada();
		loadComentarios();
	}
}
