package com.example.services;

import java.sql.Date;
import java.util.List;

import com.example.dao.ReimbursementDaoHibernate;
import com.example.dao.UserDao;
import com.example.models.Reimbursement;
import com.example.models.ReimbursementStatus;
import com.example.models.ReimbursementType;
import com.example.models.User;

public class ReimbursementServices {
	
	private ReimbursementDaoHibernate rDao;
	
	public ReimbursementServices(ReimbursementDaoHibernate r) {
		this.rDao= r;
	}
	
	public void addReimbursement(User submitted_by, ReimbursementType type_id, Date reimbursement_submit, String reimbursment_description,
			double reimbursement_amount) {
		//create new
		Reimbursement r = new Reimbursement(submitted_by, type_id, reimbursement_submit, reimbursment_description, reimbursement_amount); 
		//rdao create
		rDao.createReimbursement(r);
		
	}
	
	public List<Reimbursement> getAllReimbursements(){
		List<Reimbursement> reimbs = rDao.getAllReimbursements();
		return reimbs;
	}
	
	public void updateReimbursement(Reimbursement reimb) {
		rDao.updateReimbursement(reimb);
	}
	
	public Reimbursement getReimbursementById(int id) {
		Reimbursement r = rDao.getReimbursmentById(id);
		return r;
	}
	public ReimbursementType getTypeById(int typeId) {
		ReimbursementType t = rDao.getTypeById(typeId);
		return t;
	}
	public ReimbursementStatus getStatusById(int statusId) {
		ReimbursementStatus s = rDao.getStatusById(statusId);
		return s;
	}
	public List<Reimbursement> getReimbursementsByUserId(int userId){
		List<Reimbursement> reimbs = rDao.getReimbursementsByUserId(userId);
		return reimbs;
	}

}
