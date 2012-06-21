package com.cpvsports.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

import java.sql.Date;

import com.cpvsports.client.Layout;
import com.cpvsports.client.NoticiasService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class NoticiasServiceImpl extends RemoteServiceServlet implements NoticiasService {
	public Integer publicar(String[] campos) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		String titulo = campos[0];
		String imagen = campos[1];
		String contenido = campos[2];
		Integer categoria = Integer.parseInt(campos[3]);
		Integer visitas = 0;
		java.util.Date today = new java.util.Date();
		Date fecha = new java.sql.Date(today.getTime());
		Integer id_sesion = Integer.parseInt(campos[4]);
		Noticia noticia = new Noticia();
		
		Integer id_usuario = em.find(Sesione.class, id_sesion).getIdUsuario();
		
		noticia.setTitulo(titulo);
		noticia.setFecha(fecha);
		noticia.setContenido(contenido);
		noticia.setIdCategoria(categoria);
		noticia.setVisitas(visitas);
		noticia.setImagen(imagen);
		noticia.setIdUsuario(id_usuario);
		
		em.persist(noticia);
		em.getTransaction().commit();
		em.close();
		
		
		return 1;
	}
	
	public String[] cargarNoticia(Integer id_noticia) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		Noticia noticia = em.find(Noticia.class, id_noticia);
		em.close();
		String[] result = new String[4];
		result[0] = noticia.getTitulo();
		result[1] = noticia.getContenido();
		result[2] = noticia.getFecha().toString();
		result[3] = id_noticia.toString();
		
		return result;
	}
	
	public String[] cargarBigNoticia(Integer id_noticia) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CpvSports");
		EntityManager em = factory.createEntityManager();
		Noticia noticia = em.find(Noticia.class, id_noticia);
		em.close();
		String[] result = new String[5];
		result[0] = noticia.getTitulo();
		result[1] = noticia.getContenido();
		result[2] = noticia.getFecha().toString();
		result[3] = id_noticia.toString();
		result[4] = noticia.getImagen();
		
		return result;
	}
}
