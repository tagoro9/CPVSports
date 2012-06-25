package com.cpvsports.server;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comentarios_online database table.
 * 
 */
@Entity
@Table(name="comentarios_online")
public class ComentariosOnline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String contenido;

	@Column(name="id_usuario")
	private int idUsuario;

    public ComentariosOnline() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}