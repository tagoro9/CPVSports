package com.cpvsports.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	Integer[] loguear(String usuario, String password);
	Integer logout(String id_sesion);
	String isLogged(String id_sesion);
}
