package com.cpvsports.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Anchor;

public class Footer {
	
	public void display() {
		FlowPanel footer = Layout.createDivWithId("footer");
		
		//Logo del footer
		FlowPanel footerLogo = Layout.createDiv("grid_3 prefix_9", "footerLogo");
		//Crear titulo
		HTML tituloLogo = new HTML();
		tituloLogo.setHTML("<h1><a class=\"logoLink\" href=\"#\"></a></h1>");
		footerLogo.add(tituloLogo);		
		//Copyright
		HTML copyright = new HTML();
		copyright.setHTML("CPVSports © 2012");
		copyright.getElement().setId("copyright");
		footerLogo.add(copyright);
		
		footer.add(footerLogo);
		RootPanel.get("footerContainer").add(footer);
	}

}
