package com.cpvsports.client;

import com.cpvsports.shared.FieldVerifier;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MiCuenta implements Pagina{
	
	private FormPanel formulario;
	private FlowPanel contenedor;
	private TextBox nombre;
	private TextBox email;
	private PasswordTextBox password;
	private PasswordTextBox passwordConf;
	
	private String oldName;
	private String oldEmail;
	
	private final RegistrarServiceAsync servicioRegistro = GWT.create(RegistrarService.class);	
	
	public void display(Integer id) {
		final String datos[] = new String[2];
		servicioRegistro.getInfo(Integer.parseInt(Cookies.getCookie("id_usuario")), new AsyncCallback<String[]>(){
			public void onFailure(Throwable caught) {
				Window.alert("Error al cargar la información el usuario");
			}
			public void onSuccess(String[] result) {
				nombre.getElement().setAttribute("value", result[0]);
				email.getElement().setAttribute("value", result[1]);
				oldName = result[0];
				oldEmail = result[1];
			}
		});
		formulario = new FormPanel();
		contenedor = Layout.createDiv("grid_12", "miCuenta"); 
		RootPanel.get("contenido").clear();
		formulario = new FormPanel();
		formulario.setStyleName("editar");
		FlowPanel contenedorRegistro = new FlowPanel();
		
		Label nombreLabel = new Label("Nombre");
		nombre = new TextBox();
		nombre.getElement().setAttribute("placeholder", "Nombre");

		
		Label emailLabel = new Label("Email");
		email = new TextBox();
		email.getElement().setAttribute("placeholder", "Email");
		
		Label passwordLabel = new Label("Contaseña");
		password = new PasswordTextBox();
		password.getElement().setAttribute("placeholder", "Contraseña");
		
		Label passwordConfLabel = new Label("Confirmación");
		passwordConf = new PasswordTextBox();
		passwordConf.getElement().setAttribute("placeholder", "Confirmación");
		
		Button boton = new Button("Guardar");
		
		contenedorRegistro.add(nombreLabel);
		contenedorRegistro.add(nombre);
		contenedorRegistro.add(emailLabel);
		contenedorRegistro.add(email);
		contenedorRegistro.add(passwordLabel);
		contenedorRegistro.add(password);
		contenedorRegistro.add(passwordConfLabel);
		contenedorRegistro.add(passwordConf);
		contenedorRegistro.add(boton);
		
		formulario.add(contenedorRegistro);
		
		HTML titulo = new HTML("<h4>Editar cuenta</h4>");
		contenedor.add(titulo);
		contenedor.add(formulario);
		RootPanel.get("contenido").add(contenedor);
		
		//Llamar al servicio de registro cuando se hace click en el boton
	    boton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  editarUsuario();
		      }
	    });
	}
	
	private void editarUsuario() {
		//if (!password.getText().equals("") && password.getText().equals(passwordConf.getText())) {
		final String datos[] = new String[4];
		datos[0] = nombre.getText();
		datos[1] = email.getText();
		datos[2] = password.getText();
		datos[3] = passwordConf.getText();
		if (datos[2].equals(datos[3]) && (FieldVerifier.isValidPassword(datos[2]))){
			//Nombre
			servicioRegistro.isValidName(datos[0], new AsyncCallback<Integer>(){
				public void onFailure(Throwable caugth) {
					Window.alert("Error al comprobar el nombre");
				}
				public void onSuccess(Integer result) {
					if (datos[0].equals(oldName) || (result != 0)) {
						//Email
						servicioRegistro.isValidEmail(datos[1], new AsyncCallback<Integer>(){
							public void onFailure(Throwable caught) {
								Window.alert("Error al comprobar el email");
							}
							public void onSuccess(Integer result) {
								if (datos[1].equals(oldEmail) || result != 0) {
									//Comprobar contraseña
									servicioRegistro.actualizar(Integer.parseInt(Cookies.getCookie("id_usuario")), datos, new AsyncCallback<String>(){
										public void onFailure(Throwable caught) {
											Window.alert("Error al actualizar el usuario");
										}
										public void onSuccess(String result) {
											Window.alert(result);
											Header header = new Header();
											header.display(0);
										}
									});
								}
								else {
									Window.alert("El email no es válido");
								}
							}
						});
					}
					else {
						Window.alert("El nombre no es válido");
					}
				}
			});
		}
		else {
			Window.alert("La contraseña no es correcta (como mínimo ha de tener 6 caracteres)");
		}
	}
}
