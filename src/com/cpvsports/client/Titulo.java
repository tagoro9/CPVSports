package com.cpvsports.client;

import com.google.gwt.user.client.Window;

public class Titulo {
	public static void setTitulo(String titulo) {
		String baseTitle = "CPVSports";
		String newTitle;
		if (titulo.length() > 0) {
			newTitle = baseTitle + " | " + titulo;
		}
		else {
			newTitle = baseTitle;
		}
			
		Window.setTitle(newTitle);
	}
}
