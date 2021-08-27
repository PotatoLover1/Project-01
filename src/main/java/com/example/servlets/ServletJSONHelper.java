package com.example.servlets;

import java.io.IOException;
import com.example.controllers.LoginController;
import com.example.controllers.ReimbursementController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletJSONHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		System.out.println("in servletJSONhelper with uri: "+ req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/Ers1Project/api/login":
			LoginController.login(req,res);
			break;
			
		case "/Ers1Project/api/Reimbursement":
			ReimbursementController.handleReimbs(req, res);
			break;
		}
	}
}
