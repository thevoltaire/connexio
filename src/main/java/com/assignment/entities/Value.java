package com.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Value")
public class Value {
	
	public Value(Long id, String quote) {
		super();
		this.id = id;
		this.quote = quote;
	}

	/**
	 * 
	 */
	//private static final long serialVersionUID = 3641434139861033263L;
	@Id
	private Long id;
	@Column
	private String quote;

	  public Value() {
	  }

	  public Long getId() {
	    return this.id;
	  }

	  public String getQuote() {
	    return this.quote;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public void setQuote(String quote) {
	    this.quote = quote;
	  }

	  @Override
	  public String toString() {
	    return "Value{" +
	        "id=" + id +
	        ", quote='" + quote + '\'' +
	        '}';
	  }
}
