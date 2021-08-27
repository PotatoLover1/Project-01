package com.example.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.models.Reimbursement;
import com.example.models.ReimbursementStatus;
import com.example.models.ReimbursementType;
import com.example.utils.HibernateUtil;

public class ReimbursementDaoHibernate implements ReimbursementDao {

	@Override
	public List<Reimbursement> getAllReimbursements() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbs = ses.createNativeQuery("select * from reimbursements", Reimbursement.class).list();
		return reimbs;
	}

	@Override
	public Reimbursement getReimbursmentById(int id) {
		Session ses = HibernateUtil.getSession();
		Reimbursement reimbs = (Reimbursement) ses.createQuery("from Reimbursement where id = " + id).uniqueResult();
		return reimbs;
	}

	@Override
	public List<Reimbursement> getReimbursementsByUserId(int userId) {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbs = ses.createNativeQuery("select * from reimbursements where submit_id ="+ userId + " order by status", Reimbursement.class).list();
		return reimbs;
	}


	@Override
	public void createReimbursement(Reimbursement reimb) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction();
		ses.save(reimb);
		tran.commit();
	}

	@Override
	public void updateReimbursement(Reimbursement reimb) {
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction();
		ses.update(reimb);
		tran.commit();
	}
	
	public ReimbursementType getTypeById(int typeId) {
		Session ses = HibernateUtil.getSession();
		ReimbursementType t = (ReimbursementType) ses.createQuery("from ReimbursementType where type_id="+typeId).uniqueResult();
		return t;
	}
	
	public ReimbursementStatus getStatusById(int statusId) {
		Session ses = HibernateUtil.getSession();
		ReimbursementStatus s = (ReimbursementStatus) ses.createQuery("from ReimbursementStatus where status_id = " + statusId).uniqueResult();
		return s;
	}
	

}
