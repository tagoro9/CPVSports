package com.cpvsports.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class BreadCrumbs {
	public void construct(String[] enlaces) {
		HorizontalPanel panel = new HorizontalPanel();
		HTML separador = new HTML("&nbsp;>&nbsp;");
		Anchor principal = new Anchor("CPVSports");
		principal.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Layout.reload();
			}
		});
		panel.add(principal);
		panel.add(separador);
		for (int i = 0; i < enlaces.length; i++) {
			HTML sep = new HTML("&nbsp;>&nbsp;");
			HTML enlace = new HTML(enlaces[i]);
			panel.add(enlace);
			if (i != enlaces.length -1) {
				panel.add(sep);
			}
		}
		RootPanel.get("breadCrumbs").clear();
		RootPanel.get("breadCrumbs").add(panel);
	}
}
