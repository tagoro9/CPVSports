package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class Home implements Pagina {
	
	private final NoticiasServiceAsync servicioNoticias = GWT.create(NoticiasService.class);
	private final LoginServiceAsync servicioLogin = GWT.create(LoginService.class);
	private final ComentariosServiceAsync servicioComentarios = GWT.create(ComentariosService.class);
	
	private FlowPanel main;
	
	private Anchor ultimas;
	private Anchor masVistas;
	private FlowPanel listadoNoticias;
	
	//Eventos
	private void ultimasClick() {
		if (getActive() != "ultimas") {
			setActive(ultimas);
			ultimasNoticias();
		}
	}
	
	private void masVistasClick() {
		if (getActive() != "masVistas") {
			setActive(masVistas);
			masVistas();
		}
		
	}
	
	private String getActive() {
		if (ultimas.getStylePrimaryName().contains("active"))
			return "ultimas";
		else
			return "masVistas";
	}
	
	private void setActive(Anchor activar) {
		ultimas.setStyleName("");
		masVistas.setStyleName("");
		activar.setStyleName("active");
	}
	
	
	
	//Construir interfaz
	
	private void loadPortada() {
		servicioNoticias.ultimasNoticias(new AsyncCallback<Integer[]>() {
			public void onFailure(Throwable caught) {
				Notificaciones.error("Error al cargar la portada");
			}
			public void onSuccess(Integer[] result) {
				if (result[0] != null) {
					final Integer id = result[0];
					servicioNoticias.cargarBigNoticia(id, new AsyncCallback<String[]>(){
						public void onFailure(Throwable caught){
							Notificaciones.error("Error al cargar la noticia");
						}
						public void onSuccess(String[] result){
							
							
							//Portada
							FlowPanel portada = Layout.createDivWithId("portada");
							//Imagen
							Image imagenPortada = new Image(result[4]);
							imagenPortada.getElement().setAttribute("alt", "imagen portada");
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
							titular.setStyleName("principal");
							final String tituloNoticia = result[0];
							titular.addClickHandler(new ClickHandler(){
								public void onClick(ClickEvent event) {
							    	Titulo.setTitulo(tituloNoticia);
							    	Header header = new Header();
							    	Menu menu = new Menu();
							    	BreadCrumbs bc = new BreadCrumbs();
							    	Pagina noticias = new Noticias();
							    	header.display(id);
							    	menu.construct("noticias");
							    	String[] enlaces = new String[2];
							    	enlaces[0] = "Noticias";
							    	enlaces[1] = tituloNoticia;
							    	bc.construct(enlaces);
							    	noticias.display(id);
								}
							});
							//Contenido
							HTML titularContenido = new HTML();
							titularContenido.setHTML("<p>" + result[1].substring(0, 200) +"...</p>");
							titularPortada.add(fecha);
							titularPortada.add(titular);
							titularPortada.add(titularContenido);
							
							portada.add(imagenPortada);
							portada.add(titularPortada);							
							main.add(portada);
							loadUltimas();
							//Aumentar las visitas de la noticia
							servicioNoticias.aumentarVisitas(id, new AsyncCallback<Integer>(){
								public void onFailure(Throwable caught) {
									Notificaciones.error("Error al aumentar las visitas de la noticia");
								}
								public void onSuccess(Integer result) {
								}
							});
						}
					});							
				}
				else
					Notificaciones.alert("No hay noticias");
			}
		});
	}
	
	
	private void loadNoticia(Integer id_noticia) {
		final Integer id = id_noticia;
		servicioNoticias.cargarBigNoticia(id_noticia, new AsyncCallback<String[]> () {
			public void onFailure (Throwable caught) {
				Notificaciones.error("Fallo al cargar noticia");
			}
			
			public void onSuccess (String[] result) {
				FlowPanel noticia = Layout.createDiv("noticia");
				//Imagen
				FlowPanel imagenNoticiaContainer = Layout.createDiv("imagenNoticia grid_2");
				Image imagenNoticia = new Image(result[4]);
				imagenNoticia.getElement().setAttribute("alt", "imagen noticia");
				imagenNoticiaContainer.add(imagenNoticia);
				//Contenido
				FlowPanel contenidoNoticia = Layout.createDiv("contenidoNoticia grid_5");
				//Fecha
				HTML fecha = new HTML();
				fecha.setStyleName("fecha");
				fecha.setHTML(result[2]);
				final String tituloNoticia = result[0];
				//Titulo
				HTML titulo = new HTML();		
				titulo.setHTML("<h2>"+ result[0] +"</h2>");
				titulo.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event) {
				    	Titulo.setTitulo("Noticias");
				    	Header header = new Header();
				    	Menu menu = new Menu();
				    	BreadCrumbs bc = new BreadCrumbs();
				    	Pagina noticias = new Noticias();
				    	header.display(id);
				    	menu.construct("noticias");
				    	String[] enlaces = new String[2];
				    	enlaces[0] = "Noticias";
				    	enlaces[1] = tituloNoticia;
				    	bc.construct(enlaces);
				    	noticias.display(id);
					}
				});
				//Texto
				HTML texto = new HTML();
				texto.setHTML("<p>"+ result[1].substring(0, 100) +"...</p>");
				contenidoNoticia.add(fecha);
				contenidoNoticia.add(titulo);
				contenidoNoticia.add(texto);
				
				noticia.add(imagenNoticiaContainer);
				noticia.add(contenidoNoticia);
				listadoNoticias.add(noticia);
			}
		});
	}
		
	private void loadUltimas() {
		FlowPanel noticias = Layout.createDivWithId("noticias");
		
		//Menu de navegacion
		FlowPanel noticiasNavContainer = Layout.createDivWithId("noticias-nav"); 
		HorizontalPanel noticiasNav = new HorizontalPanel();
		//noticiasNav.getElement().setId("noticias-nav");
		//Enlaces
		ultimas = new Anchor("Últimas noticias");
		ultimas.setStyleName("active");
		masVistas = new Anchor("Las más vistas");
		
		//Eventos al hacer click en el menu
	    ultimas.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        ultimasClick();
	      }
	    });		
	    
	    masVistas.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        masVistasClick();
		      }
	    });
		
		
		noticiasNav.add(ultimas);
		noticiasNav.add(masVistas);
		
		noticiasNavContainer.add(noticiasNav);
		
		noticias.add(noticiasNavContainer);
		
		listadoNoticias = Layout.createDiv("listaNoticias", "noticias-1");
		
		noticias.add(listadoNoticias);
		
		ultimasNoticias();
		
		main.add(noticias);
	}
	
	public void ultimasNoticias () {
		listadoNoticias.clear();
		servicioNoticias.ultimasNoticias(new AsyncCallback<Integer[]> (){
			public void onFailure (Throwable caught) {
				Notificaciones.error("Fallo al cargar ultimas noticias");
			}
			
			public void onSuccess (Integer[] result) {
				for (int i = 0; i < result.length; i++) {
					if (result[i] != null) {
						loadNoticia(result[i]);
					}
				}
			}
		});
	}
	
	public void masVistas () {
		listadoNoticias.clear();
		servicioNoticias.noticiasMasVistas(new AsyncCallback<Integer[]> (){
			public void onFailure (Throwable caught) {
				Notificaciones.error("Fallo al cargar ultimas noticias");
			}
			
			public void onSuccess (Integer[] result) {
				for (int i = 0; i < result.length; i++) {
					if (result[i] != null) {
						loadNoticia(result[i]);
					}
				}
			}
		});
	}
	
	public void display(Integer id) {
		RootPanel.get("contenido").clear();
		main = Layout.createDiv("grid_8", "main");
		loadPortada();
		SideBar sidebar = new SideBar();
		RootPanel.get("contenido").add(main);
		
		RootPanel.get("contenido").add(sidebar.construct());
		sidebar.construct();
	}
}
