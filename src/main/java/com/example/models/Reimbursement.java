package com.example.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.dao.ReimbursementDaoHibernate;
import com.example.dao.UserDaoHibernate;
import com.example.services.ReimbursementServices;
import com.example.services.UserServices;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Table(name ="reimbursements")
public class Reimbursement {
	@Transient
	private UserDaoHibernate uDao = new UserDaoHibernate();
	@Transient
	private UserServices uServ = new UserServices(uDao);
	@Transient
	private ReimbursementDaoHibernate rDao = new ReimbursementDaoHibernate();
	@Transient
	private ReimbursementServices rServ = new ReimbursementServices(rDao);
	@Id
	@Column(name="reimb_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reimb_id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="submit_id")
	private User submited;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="resolve_id")
	private User resolved;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="type_id")
	private ReimbursementType type_id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="status")
	private ReimbursementStatus status_id;
	
	@Column(name="sumbit_date")
	private Date reimbursement_submit;
	
	@Column(name="resolve_date")
	private Date reimbursement_resolve;
	
	@Column(name="reimbursment_description")
	private String reimbursment_description;
	
	@Column(name="amount")
	private double reimbursement_amount;
	public Reimbursement() {
		super();
	}
	//pulling from DB
	public Reimbursement(int reimb_id, int submitted_by, int resolved_by, int type_id, int status_id, Date reimbursement_submit,
			Date reimbursement_resolve, String reimbursment_description, double reimbursement_amount) {
		super();
		this.reimb_id = reimb_id;
		this.submited = uServ.getUserById(submitted_by);
		this.resolved = uServ.getUserById(resolved_by);
		this.type_id = rServ.getTypeById(type_id);
		this.status_id = rServ.getStatusById(status_id);
		this.reimbursement_submit = reimbursement_submit;
		this.reimbursement_resolve = reimbursement_resolve;
		this.reimbursment_description = reimbursment_description;
		this.reimbursement_amount = reimbursement_amount;
	}
	//creating new instance
	public Reimbursement(User submitted_by, ReimbursementType type_id, Date reimbursement_submit, String reimbursment_description,
			double reimbursement_amount) {
		super();
		this.submited = submitted_by;
		this.type_id = type_id;
		this.reimbursement_submit = reimbursement_submit;
		this.reimbursment_description = reimbursment_description;
		this.reimbursement_amount = reimbursement_amount;
		this.status_id = rDao.getStatusById(1);
	}
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public User getSubmited() {
		return submited;
	}
	public void setSubmited(User submited) {
		this.submited = submited;
	}
	public User getResolved() {
		return resolved;
	}
	public void setResolved(User resolved) {
		this.resolved = resolved;
	}
	public ReimbursementType getType_id() {
		return type_id;
	}
	public void setType_id(ReimbursementType type_id) {
		this.type_id = type_id;
	}
	public ReimbursementStatus getStatus_id() {
		return status_id;
	}
	public void setStatus_id(ReimbursementStatus status_id) {
		this.status_id = status_id;
	}
	public Date getReimbursement_submit() {
		return reimbursement_submit;
	}
	public void setReimbursement_submit(Date reimbursement_submit) {
		this.reimbursement_submit = reimbursement_submit;
	}
	public Date getReimbursement_resolve() {
		return reimbursement_resolve;
	}
	public void setReimbursement_resolve(Date reimbursement_resolve) {
		this.reimbursement_resolve = reimbursement_resolve;
	}
	public String getReimbursment_description() {
		return reimbursment_description;
	}
	public void setReimbursment_description(String reimbursment_description) {
		this.reimbursment_description = reimbursment_description;
	}
	public double getReimbursement_amount() {
		return reimbursement_amount;
	}
	public void setReimbursement_amount(double reimbursement_amount) {
		this.reimbursement_amount = reimbursement_amount;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", submited=" + submited.getUsername() + ", resolved=" + resolved.getUsername()
				+ ", type_id=" + type_id.getType()+ ", status_id=" + status_id.getStatus() + ", reimbursement_submit=" + reimbursement_submit
				+ ", reimbursement_resolve=" + reimbursement_resolve + ", reimbursment_description="
				+ reimbursment_description + ", reimbursement_amount=" + reimbursement_amount + "]";
	}
	
	
}
