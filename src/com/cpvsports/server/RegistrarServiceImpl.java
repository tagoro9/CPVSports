package com.cpvsports.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Date;

import com.cpvsports.shared.FieldVerifier;

import com.cpvsports.client.RegistrarService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RegistrarServiceImpl extends RemoteServiceServlet implements
		RegistrarService {

	//private EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
	
	public String registrar(String[] input) throws IllegalArgumentException {

		String nombre = input[0];
		String email = input[1];
		String password = input[2];
		String password_conf = input[3];
		//Crear conexion a la base de datos
		//Si todo fue bien registrar al usuario
		try {
			EntityManagerFactory factory = Persistence
					.createEntityManagerFactory("CpvSports");
					EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
				Usuario nuevoUsuario = new Usuario();
				nuevoUsuario.setNombre(nombre);
				nuevoUsuario.setPassword(password);
				nuevoUsuario.setEmail(email);
				java.util.Date today = new java.util.Date();
				Date sqlToday = new java.sql.Date(today.getTime());
				nuevoUsuario.setFechaDeRegistro(sqlToday);
				em.persist(nuevoUsuario);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			return("Algo fue mal");
		}
		return "Hola "+input;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}



/*String nombre = input[0];
String email = input[1];
String password = input[2];
String password_conf = input[3];*/
/*
//Obtener usuarios actuales
List<Usuario> usuariosRegistrados = null;
try {
	EntityManager em = conexion.createEntityManager();
	Query q = em.createQuery("select u from Usuario u");
	usuariosRegistrados = q.getResultList();
	em.close();
} catch (Exception e) {
	e.printStackTrace();
}
//Comprobar que el email o el usuario no esta registrado
try {
	for (Usuario a : usuariosRegistrados) {
		if (a.getNombre().toString().equals(nombre))
			throw new IllegalArgumentException("El nombre de usuario ya existe.");
		if (a.getEmail().toString().equals(email))
			throw new IllegalArgumentException("El email ya existe.");
	}
} catch (IllegalArgumentException e) {
	return (e.getMessage());
}

//Comprobar que la contraseña coincide con la confirmacion
try {
	if (password != password_conf)
		throw new IllegalArgumentException("Las contraseñas no coinciden");
}
catch (IllegalArgumentException e) {
	return e.getMessage();
}

//Si todo fue bien registrar al usuario
try {
	EntityManager em = conexion.createEntityManager();
	em.getTransaction().begin();
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setPassword(password);
		nuevoUsuario.setEmail(email);
		em.persist(nuevoUsuario);
	em.getTransaction().commit();
	em.close();
} catch (Exception e) {
	e.printStackTrace();
}

return "hola";*/
