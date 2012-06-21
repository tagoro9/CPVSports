package com.cpvsports.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("noticias")
public interface NoticiasService extends RemoteService {
	public Integer publicar(String[] campos);
	public String[] cargarNoticia(Integer id_noticia);
	public String[] cargarBigNoticia(Integer id_noticia);
}
