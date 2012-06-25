package com.cpvsports.server;

import java.sql.Date;
import java.util.List;
import java.text.DateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cpvsports.client.ComentariosService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ComentariosServiceImpl extends RemoteServiceServlet implements
ComentariosService {
	public Integer publicarComentario(String comentario, Integer id_noticia, Integer id_usuario) {
		//Guardar el comentario en la BD
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Comentario c = new Comentario();
		java.util.Date today = new java.util.Date();
		Date fecha = new java.sql.Date(today.getTime());
		c.setContenido(comentario);
		c.setIdNoticia(id_noticia);
		c.setIdUsuario(id_usuario);
		c.setFecha(fecha);
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		return 1;
	}
	
	public Integer[] getComentarios(Integer id_noticia) {
		Integer comentariosIds[] = new Integer[50];
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select c from Comentario c where c.idNoticia = '"+id_noticia.toString()+"' order by c.fecha DESC");
		q.setFirstResult(0);
		q.setMaxResults(49);
		List<Comentario> comentarios = null;
		comentarios = q.getResultList();
		Integer i = 0;
		for (Comentario n : comentarios) {
			comentariosIds[i] = n.getId();
			i++;
		}
		em.close();
		return comentariosIds;
	}
	
	public String[] loadComentario(Integer id_comentario) {
		String datos[] = new String[4];
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		Comentario comentario = em.find(Comentario.class, id_comentario);
		datos[0] = DateFormat.getDateTimeInstance().format(comentario.getFecha());
		datos[1] = comentario.getContenido();
		//Obtener el nombre del usuario;
		Usuario usuario = em.find(Usuario.class, comentario.getIdUsuario());
		datos[2] = usuario.getNombre();
		Integer id = comentario.getIdUsuario(); 
		datos[3] = id.toString();
		em.close();
		return datos;
	}
	
	public Integer borrarComentario(Integer id_comentario) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Comentario comentario = em.find(Comentario.class,id_comentario);
		em.remove(comentario);
		em.getTransaction().commit();
		em.close();
		return comentario.getIdNoticia();
	}
	
	public Integer[] cargarComentarios(){
		Integer comentariosIds[] = new Integer[20];
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select c from ComentariosOnline c order by c.id DESC");
		q.setFirstResult(0);
		q.setMaxResults(19);
		List<ComentariosOnline> comentarios = null;
		comentarios = q.getResultList();
		Integer i = 0;
		for (ComentariosOnline n : comentarios) {
			comentariosIds[i] = n.getId();
			i++;
		}
		em.close();
		return comentariosIds;
	}
	

	public Integer publicarComentarioOnline(String comentario, Integer id_usuario){
		//Guardar el comentario en la BD
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		ComentariosOnline c = new ComentariosOnline();
		c.setContenido(comentario);
		c.setIdUsuario(id_usuario);
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		return 1;
	}
	
	public String[] loadComentariosOnline(Integer id_comentario){
		String datos[] = new String[2];
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		ComentariosOnline comentario = em.find(ComentariosOnline.class, id_comentario);
		datos[0] = comentario.getContenido();
		//Obtener el nombre del usuario;
		Usuario usuario = em.find(Usuario.class, comentario.getIdUsuario());
		if (comentario.getIdUsuario() != 0)
			datos[1] = usuario.getNombre();
		else
			datos[1] = "Anonymous";
		em.close();
		return datos;
	}
}
