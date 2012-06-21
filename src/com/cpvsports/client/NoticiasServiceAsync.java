package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;

public interface NoticiasServiceAsync {
	public void publicar(String[] campos, AsyncCallback<Integer> callback);
	public void cargarNoticia(Integer id_noticia, AsyncCallback<String[]> callback);
	public void cargarBigNoticia(Integer id_noticia, AsyncCallback<String[]> callback);
}
