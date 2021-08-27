package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.example.dao.ReimbursementDaoHibernate;
import com.example.dao.UserDaoHibernate;
import com.example.models.Reimbursement;
import com.example.models.ReimbursementStatus;
import com.example.models.ReimbursementType;
import com.example.models.User;
import com.example.services.ReimbursementServices;
import com.example.services.UserServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReimbursementController{
	private static UserDaoHibernate uDao = new UserDaoHibernate();
	private static ReimbursementDaoHibernate rDao = new ReimbursementDaoHibernate();
	private static ReimbursementServices rServ = new ReimbursementServices(rDao);
	private static UserServices uServ = new UserServices(uDao);
	
	public static void handleReimbs(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("reimb control>");
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		User u = uServ.getUserById(id);
		if(u.getRole().getRole_id() == 1) {
			if(req.getMethod().equals("GET")) {
				List<Reimbursement> reimbs = rServ.getAllReimbursements();
				res.addHeader("Access-Control-Allow-Origin", "*");
				res.setHeader("Access-Control-Allow-Methods", "GET");
				res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
			}else {
				StringBuilder buffer = new StringBuilder();
				BufferedReader reader = req.getReader();
				
				String line;
				while((line = reader.readLine()) != null) {
					buffer.append(line);
					buffer.append(System.lineSeparator());
				}
				String data = buffer.toString();
				System.out.println(data);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode parsedObj = mapper.readTree(data);
				
				int reimb_id = Integer.parseInt(parsedObj.get("r_id").asText());
				Reimbursement r = rServ.getReimbursementById(reimb_id);
				r.setReimbursement_resolve(new Date(System.currentTimeMillis()));
				ReimbursementStatus status = rServ.getStatusById(Integer.parseInt(parsedObj.get("status").asText()));
				r.setStatus_id(status);
				r.setResolved(u);
				rServ.updateReimbursement(r);
				
				ObjectNode ret = mapper.createObjectNode();
				ret.put("message", "successfully updated a reimbursment");
				res.addHeader("Access-Control-Allow-Origin", "*");
				res.setHeader("Access-Control-Allow-Methods", "POST");
				res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
			}
	}else {
		if(req.getMethod().equals("GET")) {
			List<Reimbursement> reimbs = rServ.getReimbursementsByUserId(id);
			//System.out.println(reimbs);
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "GET");
			//System.out.println(new ObjectMapper().writeValueAsString(reimbs));
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
		}else {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			
			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			System.out.println(data);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode parsedObj = mapper.readTree(data);
			
			int t_id = Integer.parseInt(parsedObj.get("type").asText());
			ReimbursementType t = rDao.getTypeById(t_id);
			Date d = new Date(System.currentTimeMillis());
			String desc = parsedObj.get("description").asText();
			double amnt = Double.parseDouble(parsedObj.get("amount").asText());
			rServ.addReimbursement(u, t, d, desc, amnt);
			
			ObjectNode ret = mapper.createObjectNode();
			ret.put("message", "successfully submitted a new reimbursment");
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "POST");
			res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
		}
	}
}
}
