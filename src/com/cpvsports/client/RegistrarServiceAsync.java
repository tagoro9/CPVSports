package com.cpvsports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RegistrarServiceAsync {
	void registrar(String[] input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
