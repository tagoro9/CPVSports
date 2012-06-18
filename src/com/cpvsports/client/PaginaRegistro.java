package com.cpvsports.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class PaginaRegistro implements Pagina {
	public void display() {
		RootPanel.get("contenido").clear();
		FlowPanel contenido = Layout.createDiv("grid_12", "registro");
		FormPanel registro = new FormPanel();
		VerticalPanel contenedorRegistro = new VerticalPanel();
		
		TextBox nombre = new TextBox();
		nombre.getElement().setAttribute("placeholder", "Nombre");
		
		TextBox email = new TextBox();
		email.getElement().setAttribute("placeholder", "Email");
		
		PasswordTextBox password = new PasswordTextBox();
		password.getElement().setAttribute("placeholder", "Contraseña");
		
		PasswordTextBox passwordConf = new PasswordTextBox();
		passwordConf.getElement().setAttribute("placeholder", "Confirmación");
		
		contenedorRegistro.add(nombre);
		contenedorRegistro.add(email);
		contenedorRegistro.add(password);
		contenedorRegistro.add(passwordConf);
		
		registro.add(contenedorRegistro);
		
		HTML titulo = new HTML("<h2>Nuevo redactor</h2>");
		contenido.add(titulo);
		contenido.add(registro);
		RootPanel.get("contenido").add(contenido);
	}
}

