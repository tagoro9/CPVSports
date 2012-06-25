package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextArea;

public class EnVivo implements Pagina {
	private FlowPanel main;
	private FlowPanel sideBar;
	private final NoticiasServiceAsync servicioNoticias = GWT.create(NoticiasService.class);
	private final LoginServiceAsync servicioLogin = GWT.create(LoginService.class);
	private final ComentariosServiceAsync servicioComentarios = GWT.create(ComentariosService.class);
	private FlowPanel comentarios;
	
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
		comentarios.clear();
		servicioComentarios.cargarComentarios(new AsyncCallback<Integer[]>(){
			public void onFailure(Throwable caught){
				Window.alert("Error al cargar los comentarios");
			}
			public void onSuccess(Integer[] result){
				for (int i=0; i<result.length; i++ ){
					if (result[i] != null)
						loadComentario(result[i]);
				}
			}
		});
		
	}
	
	private void loadComentario(Integer id_comentario){
		final Integer id = id_comentario;
		servicioComentarios.loadComentariosOnline(id_comentario, new AsyncCallback<String[]>(){
			public void onFailure(Throwable caught) {
				Window.alert("Error al cargar el comentario");
			}
			public void onSuccess(String[] result) {
				FlowPanel comentario = Layout.createDiv("comentario");
				HTML separador = new HTML("<hr></hr>");
				HTML texto = new HTML(result[0]);
				HTML autor = new HTML(result[1]);
				autor.setStyleName("autor");
				texto.setStyleName("texto");
				comentario.add(texto);
				comentario.add(autor);				
				comentario.add(separador);
				comentarios.add(comentario);
			}
		}); 
	}
	
	private void loadSideBar(){
		sideBar = Layout.createDiv("grid_4", "sideBar");
		FlowPanel noticiasNav = Layout.createDivWithId("noticias-nav");
		HorizontalPanel nav = new HorizontalPanel();
		Anchor titulo = new Anchor("Comentarios");
		titulo.setStyleName("active");
		nav.add(titulo);
		//Formulario para añadir un nuevo comentario
		FormPanel formComentario = new FormPanel();
		FlowPanel formContainer = new FlowPanel();
		HTML tituloForm = new HTML("<h6>Publicar un comentario</h6>");
		final TextArea comentario = new TextArea();
		Anchor publicar = new Anchor("Enviar");
		publicar.setStyleName("button");
		formComentario.setStyleName("publicarComentario");
		formContainer.add(tituloForm);
		formContainer.add(comentario);
		formContainer.add(publicar);
		formComentario.add(formContainer);
		//comentarios.add(formComentario);
		//Al enviar el formulario
		publicar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				publicarComentario(comentario.getText());
			}
		});
		noticiasNav.add(nav);
		comentarios = Layout.createDivWithId("noticias-1");
		sideBar.add(noticiasNav);
		sideBar.add(formComentario);
		sideBar.add(comentarios);
		RootPanel.get("contenido").add(sideBar);
		loadComentarios();
		
	}
	
	private void publicarComentario(String text){
		Integer id_usuario;
		if (Cookies.getCookie("id_sesion") != "")
			id_usuario = 0;
		else
			id_usuario = Integer.parseInt(Cookies.getCookie("id_sesion"));
		servicioComentarios.publicarComentarioOnline(text,id_usuario, new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error al publicar el comentario Online");
			}
			public void onSuccess(Integer result) {
				if (result == 1) {
					Window.alert("El comentario Online ha sido publicado");
					loadComentarios();
				}
				else 
					Window.alert("Error al publicar el comentario Online");
			}
		});
	}
	
	public void display(Integer id) {
		RootPanel.get("contenido").clear();
		loadPortada();
		loadSideBar();
	}
}
