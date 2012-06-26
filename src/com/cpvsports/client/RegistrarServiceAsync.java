package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RegistrarServiceAsync {
	void registrar(String[] input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	public void getInfo(Integer id_usuario, AsyncCallback<String[]> callback);
	public void actualizar(Integer id_usuario, String[] input, AsyncCallback<String> callback);
	public void isValidEmail(String email, AsyncCallback<Integer> callback);
	public void isValidName(String name, AsyncCallback<Integer> callback);
}
