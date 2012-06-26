package com.cpvsports.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("registrar")
public interface RegistrarService extends RemoteService {
	String registrar(String[] input) throws IllegalArgumentException;
	String[] getInfo(Integer id_usuario) throws IllegalArgumentException;
	String actualizar(Integer id_usuario, String[] input) throws IllegalArgumentException;
	Integer isValidEmail(String email) throws IllegalArgumentException;
	Integer isValidName(String name) throws IllegalArgumentException;
}
