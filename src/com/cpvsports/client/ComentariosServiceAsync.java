package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ComentariosServiceAsync {
	public void publicarComentario(String comentario, Integer id_noticia, Integer id_usuario, AsyncCallback<Integer> callback);
	public void getComentarios(Integer id_noticia,AsyncCallback<Integer[]> callback);
	public void loadComentario(Integer id_comentario, AsyncCallback<String[]> callback);
	public void borrarComentario(Integer id_comentario, AsyncCallback<Integer> callback);
	public void publicarComentarioOnline(String comentario, Integer id_usuario, AsyncCallback<Integer> callback);
	public void cargarComentarios(AsyncCallback<Integer[]> callback);
	public void loadComentariosOnline(Integer id_comentario,AsyncCallback<String[]> callback);
}
