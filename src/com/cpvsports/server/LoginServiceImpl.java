package com.cpvsports.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cpvsports.client.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
LoginService {
	public Integer loguear(String usuario, String password) {
		Usuario user = Usuario.getUsuarioByName(usuario);
		if (user.getPassword().equals(password)) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
				Sesione sesion = new Sesione();
				sesion.setIdUsuario(user.getId());			
			em.persist(sesion);
			em.getTransaction().commit();
			Integer sesionId = sesion.getId();
			em.close();
			return sesionId;
		}
		return 0;
	}
	public Integer logout(String id_sesion) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		Sesione sesion = em.find(Sesione.class, Integer.parseInt(id_sesion));
		em.getTransaction().begin();
		em.remove(sesion);
		em.getTransaction().commit();
		em.close();
		return 1;
	}
	public String isLogged(String id_sesion) {
		//Obtener el nombre del usuario que esta logueado. Si no existe esa sesion en la bd devolver null
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		Sesione sesion = em.find(Sesione.class, Integer.parseInt(id_sesion));
		Integer id_usuario = sesion.getIdUsuario();
		Usuario usuario = em.find(Usuario.class, id_usuario);
		em.close();
		return usuario.getNombre();
	}
}
