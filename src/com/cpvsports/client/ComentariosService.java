package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("comentarios")
public interface ComentariosService extends RemoteService {
	public Integer publicarComentario(String comentario, Integer id_noticia, Integer id_usuario);
	public Integer[] getComentarios(Integer id_noticia);
	public String[] loadComentario(Integer id_comentario);
	public Integer borrarComentario(Integer id_comentario);
	public Integer[] cargarComentarios();
	public String[] loadComentariosOnline(Integer id_comentario);
	public Integer publicarComentarioOnline(String comentario, Integer id_usuario);
}
