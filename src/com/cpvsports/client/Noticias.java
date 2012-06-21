package com.cpvsports.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Noticias implements Pagina{		
		
	private final NoticiasServiceAsync servicioNoticias = GWT.create(NoticiasService.class);
	private final LoginServiceAsync servicioLogin = GWT.create(LoginService.class);
	
		private FlowPanel main;
		private FlowPanel sideBar;
		private FlowPanel comentarios;
		private FlowPanel noticias;
	
		//Construir interfaz
		
		private FlowPanel loadPortada() {
			//Portada
			FlowPanel portada = Layout.createDivWithId("portada");
			//Imagen
			Image imagenPortada = new Image("img/portada.jpg");
			//Titular
			FlowPanel titularPortada = Layout.createDivWithId("titularPortada");
			//Crear contenido del titular
			//Fecha
			HTML fecha = new HTML();
			fecha.setStyleName("fecha");
			fecha.setHTML("15 DEC, 2012");
			//Titular
			HTML titular = new HTML();
			titular.setHTML("<h2>Mi titular demasiado largo para caber en una linea quepasara</h2>");
			//Contenido
			HTML titularContenido = new HTML();
			titularContenido.setHTML("<p>" +
					"After checking out the visual, you should understand how to create th" +
	                "e mockup’s grid. Using the widths, match up the class #’s from the list and lets" +
	                "throw some code together. Remember to add the clearing div at the end of each" +
	                "row. Don’t forget to include the stylsheets included in the Grid 960 package." +
					"</p>");
			titularPortada.add(fecha);
			titularPortada.add(titular);
			titularPortada.add(titularContenido);
			
			portada.add(imagenPortada);
			portada.add(titularPortada);	
			return portada;
		}
		
		private FlowPanel loadComentario() {
			FlowPanel comentario = Layout.createDiv("comentario");
			HTML separador = new HTML("<hr></hr>");
			HTML texto = new HTML("Esta noticia es mentira");
			HTML autor = new HTML("Victor Mora");
			HTML fecha = new HTML("23 DIC 2012");
			fecha.setStyleName("fecha");
			autor.setStyleName("autor");
			texto.setStyleName("texto");
			comentario.add(texto);
			comentario.add(fecha);
			comentario.add(autor);
			comentario.add(separador);
			return comentario; 
		}
		
		private void loadComentarios(Integer id_noticia) {
			//Menu de navegacion
			FlowPanel comentariosNavContainer = Layout.createDivWithId("noticias-nav"); 
			HorizontalPanel comentariosNav = new HorizontalPanel();
			//noticiasNav.getElement().setId("noticias-nav");
			//Enlaces
			Anchor titulo = new Anchor("Comentarios");
			titulo.setStyleName("active");
			comentariosNav.add(titulo);
			comentariosNavContainer.add(comentariosNav);
			
			comentarios.clear();
			comentarios.add(comentariosNavContainer);
			comentarios.add(loadComentario());
			comentarios.add(loadComentario());
			
			if (Cookies.getCookie("id_sesion") != null) {
				servicioLogin.isLogged(Cookies.getCookie("id_sesion"), new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						Window.alert("Imposible conectar con el servidor");
					}
					public void onSuccess(String result) {
						if (result != null) { //Si esta logueado se crea el formulario
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
							comentarios.add(formComentario);
							//Al enviar el formulario
							publicar.addClickHandler(new ClickHandler(){
								public void onClick(ClickEvent event) {
									publicarComentario(comentario.getText());
								}
							});
						}
					}
				});
			}			
		}
		
		private void loadBigNoticia(Integer id_noticia) {
			servicioNoticias.cargarBigNoticia(id_noticia, new AsyncCallback<String[]>(){
				public void onFailure(Throwable caught){
					Window.alert("Error al cargar la noticia");
				}
				public void onSuccess(String[] result){
					//BreadCrumbs y titulo
					BreadCrumbs bc = new BreadCrumbs();
					String enlaces[] = new String[2];
					enlaces[0] = "Noticias";
					enlaces[1] = result[0];
					bc.construct(enlaces);
					Titulo.setTitulo(result[0]);
					
					//Portada 
					FlowPanel portada = Layout.createDivWithId("portada");
					//Imagen
					Image imagenPortada = new Image(result[4]);
					//Titular
					FlowPanel titularPortada = Layout.createDivWithId("titularPortada");
					//Crear contenido del titular
					//Fecha
					HTML fecha = new HTML();
					fecha.setStyleName("fecha");
					fecha.setHTML(result[2]);
					//Titular
					HTML titular = new HTML();
					titular.setHTML("<h2>"+result[0]+"</h2>");
					//Contenido
					HTML titularContenido = new HTML();
					titularContenido.setHTML("<p>" +result[1] +
							"</p>");
					titularPortada.add(fecha);
					titularPortada.add(titular);
					titularPortada.add(titularContenido);
					
					portada.add(imagenPortada);
					portada.add(titularPortada);
					main.clear();
					main.add(portada);
					main.add(comentarios);
					loadComentarios(2);
				}
			});		
		}
		
		private void loadNoticia(Integer id_noticia) {
			servicioNoticias.cargarNoticia(id_noticia, new AsyncCallback<String[]>(){
				public void onFailure(Throwable caught) {
					Window.alert("error al cargar la noticia");
				}
				public void onSuccess(String[] result) {
					FlowPanel noticia = Layout.createDiv("noticia1");
					FlowPanel contenidoNoticia = Layout.createDiv("contenidoNoticia grid_4");
					FlowPanel fechaContenedor = Layout.createDiv("fecha1");
					HTML fecha = new HTML();
					fecha.setHTML(result[2]);
					fechaContenedor.add(fecha);
					final Integer id_noticia = Integer.parseInt(result[3]);
					
					
					HTML titulo = new HTML();
					titulo.setHTML("<h6><a>"+result[0]+"</a></h6>");
					titulo.getElement().getStyle().setProperty("cursor", "pointer");
					titulo.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
						//	main.add(loadBigNoticia(id_noticia));
							loadBigNoticia(id_noticia);
							//main.add(comentarios);
							//loadComentarios(id_noticia);
					      }
					});
					HTML contenido = new HTML();
					contenido.setHTML("<p>"+result[1]+"<p>");
					contenidoNoticia.add(fechaContenedor);
					contenidoNoticia.add(titulo);
					contenidoNoticia.add(contenido);
					
					noticia.add(contenidoNoticia);
					
					noticias.add(noticia);

				}
			});
		}		
		
		private void loadSideBar() {
			FlowPanel noticiasNav = Layout.createDivWithId("noticias-nav");
			HorizontalPanel nav = new HorizontalPanel();
			Anchor titulo = new Anchor("Últimas noticias");
			titulo.setStyleName("active");
			nav.add(titulo);
			noticiasNav.add(nav);
			noticias = Layout.createDivWithId("noticias-1");
			loadNoticia(2);
			sideBar.add(noticiasNav);
			sideBar.add(noticias);
		}
		
		private void publicarComentario(String comentario) {
			//TODO Guardar comentario en la BD y al regresar refrescar los comentarios
			Window.alert(comentario);
		}
		
			
		public void display() {
					
			main = Layout.createDiv("grid_8", "main");
			sideBar = Layout.createDiv("grid_4", "sideBar");
			comentarios = Layout.createDivWithId("comentarios");
			main.add(loadPortada());
			main.add(comentarios);
			loadSideBar();
			RootPanel.get("contenido").clear();
			RootPanel.get("contenido").add(main);
			RootPanel.get("contenido").add(sideBar);
		}
}
