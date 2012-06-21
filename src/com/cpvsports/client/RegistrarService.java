package com.cpvsports.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("registrar")
public interface RegistrarService extends RemoteService {
	String registrar(String[] input) throws IllegalArgumentException;
}
