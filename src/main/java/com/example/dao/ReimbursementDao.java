package com.example.dao;

import java.util.List;

import com.example.models.Reimbursement;

public interface ReimbursementDao {
	Reimbursement getReimbursmentById(int id);
	
	List<Reimbursement> getAllReimbursements();
	
	List<Reimbursement> getReimbursementsByUserId(int userId);
	
	
	void createReimbursement(Reimbursement reimb);
	
	void updateReimbursement(Reimbursement reimb);
	
	
	
}
