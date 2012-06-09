package com.cpvsports.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Anchor;

public class Footer {
	
	public void display() {
		FlowPanel footerContainer = Layout.createDiv("container_12", "footerContainer");
		FlowPanel footer = Layout.createDivWithId("footer");
		
		//Articulos del footer (meterlos en un clase seguramente)
		FlowPanel footerArticulo = Layout.createDiv("footerArticulo grid_2");
		HTML titulo = new HTML();
		titulo.setHTML("<h3>Información</h3>");
		footerArticulo.add(titulo);
		//Listado con los enlaces
		VerticalPanel enlacesFooter = new VerticalPanel();
		Anchor enlace1 = new Anchor("Enlace");
		Anchor enlace2 = new Anchor("Enlace");
		Anchor enlace3 = new Anchor("Enlace");
		Anchor enlace4 = new Anchor("Enlace");
		Anchor enlace5 = new Anchor("Enlace");
		enlacesFooter.add(enlace1);
		enlacesFooter.add(enlace2);
		enlacesFooter.add(enlace3);
		enlacesFooter.add(enlace4);
		enlacesFooter.add(enlace5);
		
		//Logo del footer
		FlowPanel footerLogo = Layout.createDiv("grid_3", "footerLogo");
		//Crear titulo
		HTML tituloLogo = new HTML();
		tituloLogo.setHTML("<h1><a class=\"logoLink\" href=\"#\"></a></h1>");
		footerLogo.add(tituloLogo);		
		//Copyright
		HTML copyright = new HTML();
		copyright.setHTML("CPVSports © 2012");
		copyright.getElement().setId("copyright");
		footerLogo.add(copyright);
		
		footerArticulo.add(enlacesFooter);
		footer.add(footerArticulo);
		footer.add(footerLogo);
		footerContainer.add(footer);
		RootPanel.get("wrapper").add(footerContainer);
	}

}
