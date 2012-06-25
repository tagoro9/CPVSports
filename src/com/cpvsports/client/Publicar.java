package com.cpvsports.client;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Publicar implements Pagina{
	
	private final NoticiasServiceAsync servicioNoticias = GWT.create(NoticiasService.class);
	
	private TextBox titulo;
	private TextBox imagen;
	private TextArea contenido;
	private ListBox categoria;
	
	public void display(Integer id) {
		FormPanel formulario = new FormPanel();
		FlowPanel contenidoFormulario = new FlowPanel();
		
		HTML tituloForm = new HTML("<h4>Publicar una noticia</h4>");
		
		//Titulo
		titulo = new TextBox();
		Label tituloLabel = new Label("Título");
		//Imagen
		imagen = new TextBox();
		Label imagenLabel = new Label("Imagen");
		//Contenido
		Label contenidoLabel = new Label("Contenido");
		contenido = new TextArea();
		//TODO obtener categorias desde la base de datos
		//Categoria
		Label categoriaLabel = new Label("Categoría");
		categoria = new ListBox();
		categoria.addItem("Fútbol", "3");
		categoria.addItem("Tenis","4");
		categoria.addItem("Motor","5");
		categoria.addItem("Polideportivo","6");
		//Enviar
		Button enviar = new Button("Publicar");
		
		enviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				publicarNoticia();
			}
		});
		
		contenidoFormulario.add(tituloForm);
		contenidoFormulario.add(tituloLabel);
		contenidoFormulario.add(titulo);
		contenidoFormulario.add(imagenLabel);
		contenidoFormulario.add(imagen);
		contenidoFormulario.add(contenidoLabel);
		contenidoFormulario.add(contenido);
		contenidoFormulario.add(categoriaLabel);
		contenidoFormulario.add(categoria);
		contenidoFormulario.add(enviar);
		
		formulario.add(contenidoFormulario);
		formulario.setStyleName("publicar");
		RootPanel.get("contenido").clear();
		RootPanel.get("contenido").add(formulario);
	}
	
	private void publicarNoticia() {
		String[] campos = new String[5];
		campos[0] = titulo.getText();
		campos[1] = imagen.getText();
		campos[2] = contenido.getText();
		campos[3] = categoria.getValue(categoria.getSelectedIndex());
		campos[4] = Cookies.getCookie("id_sesion");
		servicioNoticias.publicar(campos, new AsyncCallback<Integer>(){
			public void onFailure(Throwable caught) {
				Window.alert("Error al publicar la noticia");
			}
			public void onSuccess(Integer result) {
				Window.alert(result.toString());
			}
		});
	}
}
