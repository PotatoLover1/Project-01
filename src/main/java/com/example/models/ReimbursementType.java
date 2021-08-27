package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Table(name ="reimbursement_type")
public class ReimbursementType {
	
	@Id
	@Column(name="type_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int type_id;
	
	@Column(name="type")
	private String type ;
	
	public ReimbursementType() {
		super();
	}
	public ReimbursementType(String type, int type_id) {
		super();
		this.type = type;
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
}
