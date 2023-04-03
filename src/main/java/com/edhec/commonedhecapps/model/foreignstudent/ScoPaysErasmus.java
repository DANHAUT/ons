package com.edhec.commonedhecapps.model.foreignstudent;


import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the SCO_PAYS_ERASMUS database table.
 * 
 */
@Entity
@Table(schema="SCHOLARSHIP", name="SCO_PAYS_ERASMUS")
@NamedQueries({
	  @NamedQuery(name="ScoPaysErasmus.findAll", query="SELECT b FROM ScoPaysErasmus b"),
	  @NamedQuery(name = "ScoPaysErasmus.findErasmusByLibang", query = "select b FROM ScoPaysErasmus b where b.libang=:libang and b.erasmus='O'"),
	})
public class ScoPaysErasmus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LIBANG")
	private String libang;

	@Column(name="ERASMUS")
	private String erasmus;

	public ScoPaysErasmus() {
	}

	public String getLibang() {
		return libang;
	}

	public void setLibang(String libang) {
		this.libang = libang;
	}

	public String getErasmus() {
		return erasmus;
	}

	public void setErasmus(String erasmus) {
		this.erasmus = erasmus;
	}

}