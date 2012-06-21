package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void loguear(String usuario, String password, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
	void logout(String id_sesion,AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
	void isLogged(String id_sesion, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
