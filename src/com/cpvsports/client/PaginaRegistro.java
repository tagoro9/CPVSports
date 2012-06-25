package com.cpvsports.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;

public class PaginaRegistro implements Pagina {
	private FormPanel registro;
	private TextBox nombre;
	private TextBox email;
	private PasswordTextBox password;
	private PasswordTextBox passwordConf;
	
	//private final GreetingServiceAsync servicioRegistro = GWT.create(GreetingService.class);
	private final RegistrarServiceAsync servicioRegistro = GWT.create(RegistrarService.class);
	
	//private EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
	
	public void display(Integer id) {
		RootPanel.get("contenido").clear();
		FlowPanel contenido = Layout.createDiv("grid_12", "registro");
		registro = new FormPanel();
		registro.setStyleName("registrar");
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
		
		Anchor boton = new Anchor("Registrar");
		boton.setStyleName("button");
		
		contenedorRegistro.add(nombreLabel);
		contenedorRegistro.add(nombre);
		contenedorRegistro.add(emailLabel);
		contenedorRegistro.add(email);
		contenedorRegistro.add(passwordLabel);
		contenedorRegistro.add(password);
		contenedorRegistro.add(passwordConfLabel);
		contenedorRegistro.add(passwordConf);
		contenedorRegistro.add(boton);
		
		registro.add(contenedorRegistro);
		
		HTML titulo = new HTML("<h2>Nuevo usuario</h2>");
		contenido.add(titulo);
		contenido.add(registro);
		RootPanel.get("contenido").add(contenido);
		
		//Llamar al servicio de registro cuando se hace click en el boton
	    boton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  registrar();
		      }
	    });	
	  /*
	  //TODO Poner esto cuando se vaya a compilar pero antes no!
	  //Evento cuando se envia el formualario
	    registro.addSubmitHandler(new FormPanel.SubmitHandler() {
			
			@Override
			public void onSubmit(SubmitEvent event) {
				Window.alert("hoooola");
				//registrar();				
			}
		});	*/
	}

	
	public void registrar() {
		String input[] = new String[4];
		input[0] = nombre.getText();
		input[1] = email.getText();
		input[2] = password.getText();
		input[3] = passwordConf.getText();
		//TODO VALIDACION DE LOS DATOS AQUI
		//servicioRegistro.greetServer("hoooola",new AsyncCallback<String>() {
		servicioRegistro.registrar(input,new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error en el registro, vuelva a intentarlo.");
			}
			public void onSuccess(String result) {
				Window.alert(result);
				/*nombre.setText("");
				password.setText("");
				passwordConf.setText("");
				email.setText("");*/
			}
		});
	}
}

